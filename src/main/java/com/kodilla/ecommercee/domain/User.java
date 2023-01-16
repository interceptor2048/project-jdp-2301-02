package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID",unique = true)
    private long id;

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
            fetch = FetchType.LAZY
    )
    private List<Session> sessionIdList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orderIdList = new ArrayList<>();

    public User(long id, String username, UserStatus userStatus, String password) {
        this.id = id;
        this.username = username;
        this.userStatus = userStatus;
        this.password = password;
    }
}