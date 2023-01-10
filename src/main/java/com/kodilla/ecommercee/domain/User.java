package com.kodilla.ecommercee.domain;

import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "USERS")
public class User {
    @Id
    @NonNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;
}
