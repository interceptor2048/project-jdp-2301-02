package com.kodilla.ecommercee.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;


    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL
    )
    private Set<OrderProduct> orderProductSet = new HashSet<>();


    public Order(LocalDate orderDate, User user) {
        this.orderDate = orderDate;
        this.user = user;
    }

    public Order(Long id, LocalDate orderDate, User user) {
        this.id = id;
        this.orderDate = orderDate;
        this.user = user;
    }


}