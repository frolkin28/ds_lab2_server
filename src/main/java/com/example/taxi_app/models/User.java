package com.example.taxi_app.models;

import com.example.taxi_app.services.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private Car car;

//    @JsonIgnore
//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
//    private List<Order> madeOrders;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
//    private List<Order> processedOrders;
}
