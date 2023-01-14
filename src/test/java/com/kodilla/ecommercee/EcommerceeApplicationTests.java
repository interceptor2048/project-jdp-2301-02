package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.SessionRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EcommerceeApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Test
    @DisplayName("Test creating user and saving it to database, retrieved user should return the same ID as created one")
    public void testCreateOnlyUser() {
        //Given
        User user = new User(1L,"User Name", UserStatus.LOGGED_IN, "223k");

        userRepository.save(user);
        long userId = user.getUserId();

        //When
        User retrievedUser = userRepository.getReferenceById(userId);

        //Then
        assertEquals(1, retrievedUser.getUserId());
    }

    @Test
    @Transactional
    @DisplayName("Test creating user with other connected records and saving it to database, retrieved user should return the same ID as created one")
    public void testCreateUser() {
        //Given
        User user = new User(1L,"User Name", UserStatus.LOGGED_IN, "223k");
        Session session = new Session(33L, 56755, 50L);
        user.getSessionList().add(session);
        session.setUser(user);
        userRepository.save(user);
        Long userId = user.getUserId();

        //When
        User retrievedUser = userRepository.getReferenceById(userId);

        //Then
        assertEquals(1, retrievedUser.getUserId());
        assertEquals(1, retrievedUser.getSessionList().size());
    }

}

