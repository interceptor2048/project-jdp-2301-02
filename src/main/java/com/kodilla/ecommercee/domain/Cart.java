package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "CARTS")
public class Cart {

    @Id
    @NonNull
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User user;

    @ManyToMany (cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> products;
}