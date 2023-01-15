package com.kodilla.ecommercee.domain;

import jdk.internal.icu.text.UnicodeSet;
import lombok.*;
import javax.persistence.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUCT_GROUPS")
public class Group {

    @Id
    @NonNull
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(targetEntity = Product.class, mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> productList;
    
}

