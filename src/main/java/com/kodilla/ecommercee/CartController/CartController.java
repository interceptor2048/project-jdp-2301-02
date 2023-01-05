package com.kodilla.ecommercee.CartController;

import com.kodilla.ecommercee.CartController.CartDomainDto.CartDto;
import com.kodilla.ecommercee.CartController.CartDomainDto.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/carts")
@RequiredArgsConstructor
public class CartController {

    @GetMapping("/cart")
    public List<CartItemDto> getCart() {
        CartItemDto cartItem1 = new CartItemDto(1L, "Kurtka", "Kurtka elegancka jesienna", 750, 2);
        CartItemDto cartItem2 = new CartItemDto(2L, "Kurtka", "Kurtka sportowa jesienna", 850, 2);
        CartItemDto cartItem3 = new CartItemDto(3L, "Kurtka", "Kurtka zimowa narciarska", 1200, 5);
        List<CartItemDto> cart = new ArrayList<>();
        cart.add(cartItem1);
        cart.add(cartItem2);
        cart.add(cartItem3);

        return cart;
    }

    @PostMapping
    public ResponseEntity<Void> addCartItem(@RequestBody CartItemDto cartItemDto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = {"cartItemId"})
    public ResponseEntity<Void> removeCartItem(@PathVariable Long cartItemId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/order")
    public ResponseEntity<CartDto> createOrder() {
        //I don't know what to put in here. Need ideas ;)//
        return ResponseEntity.ok().build();
    }
}
