package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
       userDto = new UserDto(3L, "user3", 1,45454);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(value = "block/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable long userId) {

        return new ResponseEntity<>("Block user: " + userId , HttpStatus.OK);
    }

}
