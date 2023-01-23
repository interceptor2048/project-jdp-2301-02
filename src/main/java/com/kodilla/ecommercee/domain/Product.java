package com.kodilla.ecommercee.domain;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION", length = 1024)
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "QTY")
    private Long qty;

    @Column(name = "OBSOLETE")
    private boolean obsolete = false;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PRODUCT_CART",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "ID")}
    )
    private List<Cart> cartList = new ArrayList<>();


    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private Set<OrderProduct> orderProductSet = new HashSet<>();



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH},  fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    public Product(String name, String description, BigDecimal price, Group group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }

    public Product(Long id, String name, String description, BigDecimal price, Group group, boolean obsolete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.obsolete = obsolete;
        this.group = group;
    }

}