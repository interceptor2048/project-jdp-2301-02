package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @GetMapping
    public ResponseEntity<List<UserDto>> getUserList() {
        List<UserDto> userList = new ArrayList<>();
        userList.add(new UserDto(1L,"user1",1,15234));
        userList.add(new UserDto(2L,"user2",0,45678));

        return ResponseEntity.ok(userList);
    }
    @GetMapping(value = "{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(new UserDto(userId, "newUser",1,25814));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
       userDto = new UserDto(3L, "user3", 1,45454);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        return new ResponseEntity<>("User: " + userId + " was deleted", HttpStatus.OK);
    }


}
