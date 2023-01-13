package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.SessionRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("User Entity Test Suite")
public class UserEntityTestSuite {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Test
    @DisplayName("Test creating user and saving it to database, retrieved user should return the same ID as created one")
    void testCreateOnlyUser() {
        //Given
        User user = new User(1L, "User Name", UserStatus.LOGGED_IN, "223k");
//        Session session = new Session(33L, user, 56755, 50L);
//        List<Session> sessionList = new ArrayList<>();
//        sessionList.add(session);
//        user.setSessionIdList(sessionList);
//        List<Product> productList = null;
//        Cart cart = new Cart(22L, user, productList);
//        user.setCart(cart);
//        Order order = new Order(55L, LocalDate.now(), user, productList);
//        List<Order> orderList = new ArrayList<>();
//        orderList.add(order);
//        user.setOrderIdList(orderList);

        userRepository.save(user);
        //When
        Optional<User> optionalUser = userRepository.findById(1L);
        User userNullable = new User();
        userNullable.setUserId(200L);
        User retrievedUser = optionalUser.orElse(userNullable);
        //Then
        assertEquals(user.getUserId(), retrievedUser.getUserId());
        System.out.println(user.getUserId() + " & " + retrievedUser.getUserId());
    }
    @Test
    @DisplayName("Test creating user with other connected records and saving it to database, retrieved user should return the same ID as created one")
    void testCreateUser() {
        //Given
        User user = new User(1L, "User Name", UserStatus.LOGGED_IN, "223k");
        Session session = new Session(33L, 56755, 50L);
        user.getSessionIdList().add(session);
        session.setUserId(user);
//        Session session = new Session(33L, user, 56755, 50L);
//        List<Session> sessionList = new ArrayList<>();
//        sessionList.add(session);
//        user.setSessionIdList(sessionList);
//        List<Product> productList = null;
//        Cart cart = new Cart(22L, user, productList);
//        user.setCart(cart);
//        Order order = new Order(55L, LocalDate.now(), user, productList);
//        List<Order> orderList = new ArrayList<>();
//        orderList.add(order);
//        user.setOrderIdList(orderList);
        userRepository.save(user);
        //When
        User retrievedUser = userRepository.findById(1L).orElseGet(null);
//        Optional<User> optionalUser = userRepository.findById(1L);
//        User userNullable = new User();
//        userNullable.setUserId(200L);
//        User retrievedUser = optionalUser.orElse(userNullable);

//        Optional<Session> optionalSession = sessionRepository.findById(33L);
//        Session sessionNullable = new Session();
//        sessionNullable.setSessionId(200L);
//        Session retrievedSession = optionalSession.orElse(sessionNullable);

        //Then
        assertEquals(user.getUserId(), retrievedUser.getUserId());
//        System.out.println(user.getUserId() + " & " + retrievedUser.getUserId());
//        System.out.println(retrievedUser.getUserStatus());
//        System.out.println(retrievedUser.getSessionIdList());
//        System.out.println(retrievedUser.getOrderIdList());
//        System.out.println(retrievedUser.getPassword());
//        System.out.println(retrievedUser.getUsername());
//        System.out.println(retrievedUser.getCart());
//        System.out.println(retrievedSession.getUserId());
        assertEquals(1, retrievedUser.getSessionIdList().size());

        //System.out.println(retrievedUser.getSessionIdList().get(0).getUserKey() + "-userKey");
    }
}
