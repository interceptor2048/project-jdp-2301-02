package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserAddDto;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private UserDbService userDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserAddDto userAddDto) throws Exception {
        userDbService.saveUser(userAddDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "block/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable long userId) throws Exception {
        userDbService.setBlocked(userId);
        return new ResponseEntity<>("Block user: " + userId , HttpStatus.OK);
    }

}
