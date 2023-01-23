package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.SessionDto;
import com.kodilla.ecommercee.domain.dto.UserAddDto;
import com.kodilla.ecommercee.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
@CrossOrigin("*")
public class SessionController {

    private final SessionService sessionService;


    @PostMapping("/{userId}")
    public ResponseEntity<SessionDto> createSession( @PathVariable long userId, @RequestBody UserAddDto userAddDto) throws Exception {
        return ResponseEntity.ok(sessionService.updateUserRandomKey(userId, userAddDto));
    }
}


