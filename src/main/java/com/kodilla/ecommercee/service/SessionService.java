package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Session;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.SessionDto;
import com.kodilla.ecommercee.domain.dto.UserAddDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.SessionRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public SessionDto updateUserRandomKey(final Long id, UserAddDto userAddDto) throws Exception {
        Random random = new Random();
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (user.getUsername().equals(userAddDto.getUsername())&&user.getPassword().equals(userAddDto.getPassword())){
            Session session = new Session();
            session.setUser(user);
            int userKey = random.nextInt(99999999);
            session.setUserKey(userKey);
            session.setSessionStart(LocalDateTime.now());
            session.setSessionEnds(LocalDateTime.now().plusHours(1));
            user.getSessionIdList().add(session);
            sessionRepository.save(session);
            userRepository.save(user);
            return new SessionDto(true, userKey);
        } else {
            throw new Exception("User not found, or bad username or password");
        }

    }
}
