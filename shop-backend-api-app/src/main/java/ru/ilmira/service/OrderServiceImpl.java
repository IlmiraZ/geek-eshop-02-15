package ru.ilmira.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.ilmira.controller.dto.OrderDto;
import ru.ilmira.controller.dto.OrderLineItemDto;
import ru.ilmira.persist.model.Order;
import ru.ilmira.persist.model.OrderLineItem;
import ru.ilmira.persist.model.Product;
import ru.ilmira.persist.model.User;
import ru.ilmira.persist.repository.OrderRepository;
import ru.ilmira.persist.repository.ProductRepository;
import ru.ilmira.persist.repository.UserRepository;
import ru.ilmira.service.dto.OrderStatus;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    private final CartService cartService;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final SimpMessagingTemplate template;

    private final RabbitTemplate rabbitTemplate;



    public OrderServiceImpl(OrderRepository orderRepository,
                            CartService cartService,
                            UserRepository userRepository,
                            ProductRepository productRepository,
                            SimpMessagingTemplate template,
                            RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.template = template;
        this.rabbitTemplate = rabbitTemplate;
    }



    public List<OrderDto> findOrdersByUsername(String username) {
        return orderRepository.findAllByUsername(username).stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getOrderDate(),
                        order.getStatus().name(),
                        order.getUser().getUsername(),
                        order.getOrderLineItems().stream()
                                .map(li -> new OrderLineItemDto(
                                        li.getId(),
                                        li.getOrder().getId(),
                                        li.getProduct().getId(),
                                        li.getProduct().getName(),
                                        li.getPrice(),
                                        li.getQty(),
                                        li.getColor(),
                                        li.getMaterial()
                                )).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }


    @Transactional
    public void createOrder(String username) {
        if (cartService.getLineItems().isEmpty()) {
            logger.info("Can't create order for empty Cart");
            return;
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderRepository.save(new Order(
                null,
                LocalDateTime.now(),
                Order.OrderStatus.CREATED,
                user
        ));

        List<OrderLineItem> orderLineItems = cartService.getLineItems()
                .stream()
                .map(li -> new OrderLineItem(
                        null,
                        order,
                        findProductById(li.getProductId()),
                        li.getProductDto().getPrice(),
                        li.getQty(),
                        li.getColor(),
                        li.getMaterial()
                ))
                .collect(Collectors.toList());
        order.setOrderLineItems(orderLineItems);
        orderRepository.save(order);
        cartService.clear();
        rabbitTemplate.convertAndSend("order.exchange", "new_order",
                new OrderStatus(order.getId(), order.getStatus().toString()));
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No product with id"));
    }

    @RabbitListener(queues = "processed.order.queue")
    public void receiver(OrderStatus orderStatus) {
        logger.info("New order status received id = {}, status = {}",
                orderStatus.getOrderId(), orderStatus.getStatus());

        template.convertAndSend("/order_out/order", orderStatus);
    }
}
