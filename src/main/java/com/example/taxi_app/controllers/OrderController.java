package com.example.taxi_app.controllers;

import com.example.taxi_app.models.Order;
import com.example.taxi_app.models.User;
import com.example.taxi_app.services.OrderService;
import com.example.taxi_app.services.UserService;
import com.example.taxi_app.services.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    private OrderService orderService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Order> getOrder(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        int role = (Integer) request.getAttribute("role");

        if (role == Role.DRIVER.ordinal() && email != null) {
            User driver = userService.getByEmail(email);
            Order order = orderService.getOrder(driver);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(HttpServletRequest request, @RequestBody Order order) {
        String email = (String) request.getAttribute("email");

        if (email != null) {
            User user = userService.getByEmail(email);
            Order createdOrder = orderService.createOrder(order, user);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
