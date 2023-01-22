package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "ORDER_PRODUCTS")
public class OrderProduct {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private BigDecimal price;

    private Long qty=0L;

    public OrderProduct(Order order, Product product, BigDecimal price, Long qty) {
        this.order = order;
        this.product = product;
        this.price = price;
        this.qty = qty;
    }
}
