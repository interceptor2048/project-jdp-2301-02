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
@RequestMapping(value = "/carts")
public class CartController {
    
    @PostMapping
    public ResponseEntity<Void> createCart() {
        List<CartItemDto> cart = new ArrayList<>();
        //post to repository//
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(Long cartId) {
        //get from repository//
        //map to Dto//
        CartDto currentCard = new CartDto(cartId, new ArrayList<>());
        return ResponseEntity.ok(currentCard);
    }

    @PostMapping(value = "/addItem")
    public ResponseEntity<Void> addCartItem(@RequestBody CartItemDto cartItemDto) {
        //post item into Cart//
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long cartItemId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/order")
    public ResponseEntity<CartItemDto> createOrder() {
        //Method will return OrderDto when it is ready//
        //Get current cart from repository//
        //Map current cart to OrderDto//
        return ResponseEntity.ok().build();
    }
}

