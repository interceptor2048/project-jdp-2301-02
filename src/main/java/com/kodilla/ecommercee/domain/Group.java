package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "GROUPS")
public class Group {

    @Id
    @NonNull
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
