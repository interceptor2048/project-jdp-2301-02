package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID",unique = true)
    private long userId;

    @NotNull
    @Column(name = "USER_NAME")
    private String username;

    @NotNull
    @Column(name = "STATUS")
    private UserStatus userStatus;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(
            targetEntity = Session.class,
            mappedBy = "sessionId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Session> sessionIdList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "id",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orderIdList = new ArrayList<>();

}