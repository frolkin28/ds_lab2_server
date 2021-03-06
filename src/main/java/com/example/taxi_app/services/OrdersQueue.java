package com.example.taxi_app.services;

import com.example.taxi_app.models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;

@Service
public final class OrdersQueue {
    private final static ArrayDeque<Order> orders_queue = new ArrayDeque<>();

    public void push(Order order) {
        orders_queue.addLast(order);
    }

    public Order pop() {
        return orders_queue.getFirst();
    }
}