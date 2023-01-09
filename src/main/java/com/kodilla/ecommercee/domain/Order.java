package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "ORDERS")
public class Order {
    @Id
    @NonNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @CreatedDate
    @Column(name = "DATE")
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "orderList")
    private List<Product> productList;
}
