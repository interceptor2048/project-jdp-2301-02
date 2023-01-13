package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.SessionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
@RequestMapping("/Session")
@CrossOrigin("*")
public class SessionController {

    @PostMapping(value = "{userId}")
    public ResponseEntity<SessionDto> createSession(@PathVariable long userId) {
        SessionDto sessionDto = new SessionDto(true,2345L);
        return ResponseEntity.ok(sessionDto);
    }

    @PutMapping(value = "genUserKey/{userId}")
    public ResponseEntity<String>genUserKey(@PathVariable long userId) {
        Random random = new Random();
        int userKey = random.nextInt(90000) + 10000;
        return new ResponseEntity<>("UserKey for: " + userId + " is: " + userKey, HttpStatus.OK);
    }
}


