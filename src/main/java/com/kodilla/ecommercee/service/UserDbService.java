package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserStatus;
import com.kodilla.ecommercee.domain.dto.UserAddDto;
import com.kodilla.ecommercee.exception.EmptyFieldException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDbService {
    private final UserRepository userRepository;

    private final  UserMapper userMapper;

    public void saveUser(UserAddDto userAddDto) throws EmptyFieldException {
        if (userAddDto.getUsername() != null && userAddDto.getPassword() != null) {
           // User user = userMapper.mapToUser(userAddDto);
            userRepository.save(userMapper.mapToUser(userAddDto));

        } else {
            throw new EmptyFieldException();
        }
    }

    public void setBlocked(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setUserStatus(UserStatus.BLOCKED);
        userRepository.save(user);

    }
}
