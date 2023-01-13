package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.SessionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Session")
@CrossOrigin("*")
public class SessionController {

    @PostMapping(value = "{userId}")
    public ResponseEntity<SessionDto> createSession(@PathVariable long userId) {
        SessionDto sessionDto = new SessionDto(true,2345L);
        return ResponseEntity.ok(sessionDto);
    }
}


