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
@Table(name = "USERS")
public class User {
    @Id
    @NotNull
    @GeneratedValue
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
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Session> sessionList = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Order> orderIdList = new ArrayList<>();

    public User(long userId,String username, UserStatus userStatus, String password) {
        this.userId = userId;
        this.username = username;
        this.userStatus = userStatus;
        this.password = password;
    }

}