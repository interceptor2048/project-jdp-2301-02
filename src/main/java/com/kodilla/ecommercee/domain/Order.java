package com.kodilla.ecommercee.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @CreatedDate
    @Column(name = "DATE")
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany (cascade = CascadeType.ALL, mappedBy = "orderList")
    private List<Product> productList = new ArrayList<>();

    public Order(LocalDate orderDate, User user, List<Product> productList) {
        this.orderDate = orderDate;
        this.user = user;
        this.productList = productList;
    }
}