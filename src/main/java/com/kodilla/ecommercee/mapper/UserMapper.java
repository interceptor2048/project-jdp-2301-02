package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserStatus;
import com.kodilla.ecommercee.domain.dto.UserAddDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User mapToUser(UserAddDto userAddDto) {
        return new User(userAddDto.getUsername(), UserStatus.NOT_LOGGED_IN, userAddDto.getPassword());
    }
}
