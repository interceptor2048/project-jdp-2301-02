package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("User Entity Test Suite")
public class UserEntityTestSuite {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Creating user and saving it to database, retrieved user should return the same username as created one")
    void testCreateOnlyUser() {
        //Given
        User user = new User(555L, "User Name", UserStatus.LOGGED_IN, "223k");
        userRepository.save(user);
        //When
        List<User> userList = userRepository.findAll();
        User retrievedUser = userList.get(0);
        //Then
        assertEquals("User Name", retrievedUser.getUsername());
    }

    @Test
    @DisplayName("Creating user with other connected records and saving it to database, retrieved user should have connections to records")
    void testCreateUserWithOtherRecords() {
        //Given
        User user = new User(444L, "User 22", UserStatus.LOGGED_IN, "223k");

        Session session = new Session(33L, user, 56755, 50L);
        user.getSessionIdList().add(session);

        Cart cart = new Cart(22L, user, new ArrayList<>());
        user.setCart(cart);

        Order order = new Order(55L, LocalDate.now(), user, new ArrayList<>());
        user.getOrderIdList().add(order);

        userRepository.save(user);
        //When
        List<User> userList = userRepository.findAll();
        User retrievedUser = userList.get(0);
        //Then
        assertEquals("User 22", retrievedUser.getUsername());
        assertEquals(1, retrievedUser.getSessionIdList().size());
        assertEquals(1, retrievedUser.getOrderIdList().size());
        assertEquals(retrievedUser.getId(), retrievedUser.getCart().getUser().getId());
    }

    @Test
    @DisplayName("Reading all data from created user")
    void testReadDataFromUser(){
        //Given
        User user = new User(444L, "User 55", UserStatus.LOGGED_IN, "223k");

        Session session = new Session(33L, user, 56755, 50L);
        user.getSessionIdList().add(session);

        Cart cart = new Cart(22L, user, new ArrayList<>());
        user.setCart(cart);

        Order order = new Order(55L, LocalDate.of(2022, 2, 20), user, new ArrayList<>());
        user.getOrderIdList().add(order);

        userRepository.save(user);
        //When
        List<User> userList = userRepository.findAll();
        User retrievedUser = userList.get(0);

        //Then
        assertEquals("User 55", retrievedUser.getUsername());
        assertEquals(56755, retrievedUser.getSessionIdList().get(0).getUserKey());
        assertEquals(LocalDate.of(2022, 2, 20), retrievedUser.getOrderIdList().get(0).getOrderDate());
        assertEquals(new ArrayList<>(), retrievedUser.getCart().getProducts());
        assertEquals(retrievedUser.getId(), retrievedUser.getCart().getUser().getId());
    }

    @Test
    @DisplayName("Updating record user should result in getting updated values")
    void testUpdateUser() {
        //Given
        User user = new User(444L, "User 77", UserStatus.LOGGED_IN, "223k");

        Session session = new Session(33L, user, 56755, 50L);
        user.getSessionIdList().add(session);

        Cart cart = new Cart(22L, user, new ArrayList<>());
        user.setCart(cart);

        Order order = new Order(55L, LocalDate.now(), user, new ArrayList<>());
        user.getOrderIdList().add(order);

        userRepository.save(user);
        //When
        List<User> userList = userRepository.findAll();
        User retrievedUser = userList.get(0);
        retrievedUser.setUserStatus(UserStatus.NOT_LOGGED_IN);
        retrievedUser.setPassword("555g");
        retrievedUser.setUsername("Userro");
        userRepository.save(retrievedUser);
        List<User> newUserList = userRepository.findAll();
        User newRetrievedUser = newUserList.get(0);
        //Then
        assertEquals("Userro", newRetrievedUser.getUsername());
        assertEquals(UserStatus.NOT_LOGGED_IN, newRetrievedUser.getUserStatus());
        assertEquals("555g", newRetrievedUser.getPassword());
    }

    @Test
    @DisplayName("Deleting user record should result in getting list of user size zero")
    void testDeleteUser() {
        //Given
        User user = new User(444L, "User 77", UserStatus.LOGGED_IN, "223k");

        Session session = new Session(33L, user, 56755, 50L);
        user.getSessionIdList().add(session);

        Cart cart = new Cart(22L, user, new ArrayList<>());
        user.setCart(cart);

        Order order = new Order(55L, LocalDate.now(), user, new ArrayList<>());
        user.getOrderIdList().add(order);

        userRepository.save(user);
        List<User> userList = userRepository.findAll();
        User retrievedUser = userList.get(0);
        //When
        userRepository.deleteById(retrievedUser.getId());
        List<User> retrievedUserList = userRepository.findAll();
        //Then
        assertEquals(0, retrievedUserList.size());
    }
}
