package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "ORDERS")
public class Order {

    @Id
    @NonNull
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



}
