package com.example.taxi_app.services;

import com.example.taxi_app.models.Order;
import com.example.taxi_app.models.User;
import com.example.taxi_app.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CostService costService;
    private OrdersQueue ordersQueue;

    @Autowired
    public OrderService(OrderRepository orderRepository, CostService costService, OrdersQueue ordersQueue) {
        this.orderRepository = orderRepository;
        this.costService = costService;
        this.ordersQueue = ordersQueue;
    }

    @Transactional
    public Order getOrder(User driver) {
        Order order = ordersQueue.pop();
        order.setDriver(driver);
        return orderRepository.save(order);
    }

    public Order createOrder(Order order, User user) {
        float cost = costService.calculate(order.getStart(), order.getDestination());
        order.setCost(cost);
        order.setCustomer(user);
        ordersQueue.push(order);
        return orderRepository.save(order);
    }
}
