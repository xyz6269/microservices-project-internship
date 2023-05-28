package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("ROLE_CUSTOMER")
    @PostMapping("/place-order")
    public String placeOrder(@RequestBody OrderDTO dto) {
        orderService.placeOrder(dto);
        return "Order placed successfully";
    }


    @PostMapping("/get-orders")
    public List<Order> getALlOrders() {
        return orderService.getAllOrders();
    }
}
