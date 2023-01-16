package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @NonNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User user;

    @ManyToMany (cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> products = new ArrayList<>();

    public Cart( User user) {
        this.user = user;}

}