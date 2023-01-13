package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserAddDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAddDto> addUser(@RequestBody UserAddDto userAddDto) {
        return ResponseEntity.ok(userAddDto);
    }

    @PutMapping(value = "block/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable long userId) {
        return new ResponseEntity<>("Block user: " + userId , HttpStatus.OK);
    }

}
