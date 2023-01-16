package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @NonNull
    @Column(name = "ID", unique = true)
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User user;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> products;
}
