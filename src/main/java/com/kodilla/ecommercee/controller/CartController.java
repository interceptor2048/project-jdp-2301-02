package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/carts")
public class CartController {
    
    @PostMapping(value = "/createCart/{userId}")
    public ResponseEntity<Void> createCart(@PathVariable Long userId) {
        List<CartItemDto> cart = new ArrayList<>();
        //post to repository//
        return ResponseEntity.ok().build();
    }
    
    @GetMapping(value = "/{userId}/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long userId, @PathVariable Long cartId) {
        //get from repository//
        //map to Dto//
        CartDto currentCard = new CartDto(userId, cartId, new ArrayList<>());
        return ResponseEntity.ok(currentCard);
    }

    @PostMapping(value = "{cartId}")
    public ResponseEntity<Void> addCartItem(@PathVariable Long cartId, @RequestBody CartItemDto cartItemDto) {
        //post item into Cart//
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deleteItem/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long cartItemId,@RequestParam Long cartId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{cartId}/order")
    public ResponseEntity<CartItemDto> createOrder(@PathVariable Long cartId) {
        //Method will return OrderDto when it is ready//
        //Get current cart from repository//
        //Map current cart to OrderDto//
        return ResponseEntity.ok().build();
    }
}

