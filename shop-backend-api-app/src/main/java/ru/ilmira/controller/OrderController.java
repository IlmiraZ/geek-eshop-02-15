package ru.ilmira.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ilmira.controller.dto.OrderDto;
import ru.ilmira.service.OrderService;


import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void createOrder(Authentication auth) {
        orderService.createOrder(auth.getName());
    }

    @GetMapping("/all")
    public List<OrderDto> findAll(Authentication auth) {
        return orderService.findOrdersByUsername(auth.getName());
    }
}
