package ru.ilmira.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ilmira.aspect.TrackTime;
import ru.ilmira.controller.dto.AddLineItemDto;
import ru.ilmira.controller.dto.AllCartDto;
import ru.ilmira.controller.dto.ProductDto;
import ru.ilmira.service.CartService;
import ru.ilmira.service.ProductService;
import ru.ilmira.service.dto.LineItem;

import java.util.List;

@RequestMapping("/v1/cart")
@RestController
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @TrackTime
    @PostMapping(produces = "application/json", consumes = "application/json")
    public List<LineItem> addToCart(@RequestBody AddLineItemDto addLineItemDto) {
        logger.info("New LineItem. ProductId = {}, qty = {}", addLineItemDto.getProductId(), addLineItemDto.getQty());

        ProductDto productDto = productService.findById(addLineItemDto.getProductId())
                .orElseThrow(RuntimeException::new);
        cartService.addProductQty(productDto, addLineItemDto.getColor(), addLineItemDto.getMaterial(), addLineItemDto.getQty());
        return cartService.getLineItems();
    }

    @DeleteMapping(consumes = "application/json")
    public void deleteLineItem(@RequestBody LineItem lineItem) {
        cartService.removeProduct(lineItem.getProductDto(), lineItem.getColor(), lineItem.getMaterial());
    }


    @GetMapping("/all")
    public AllCartDto findAll() {
        return new AllCartDto(cartService.getLineItems(), cartService.getSubTotal());
    }
}
