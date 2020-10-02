package com.example.taxi_app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@DynamicUpdate
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private float cost;

    @ManyToOne
    @JoinColumn(name = "start_id", nullable = false)
    private Location start;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Location destination;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    public Order(Location start, Location destination, float cost, User customer) {
        super();
        this.start = start;
        this.destination = destination;
        this.cost = cost;
        this.customer = customer;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }
}
