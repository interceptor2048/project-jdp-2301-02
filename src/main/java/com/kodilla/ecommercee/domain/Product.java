package com.kodilla.ecommercee.domain;

import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @NonNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToMany
    private List<Order> orderList;
}
