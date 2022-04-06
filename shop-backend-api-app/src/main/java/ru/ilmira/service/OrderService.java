package ru.ilmira.service;

import ru.ilmira.controller.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findOrdersByUsername(String username);

    void createOrder(String username);
}
