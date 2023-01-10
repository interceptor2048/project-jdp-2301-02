package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/carts")
public class CartController {

    @PostMapping("/{userId}/{cartId}")
    public ResponseEntity<CartDto> createCart(@PathVariable Long userId, @PathVariable Long cartId) {
        CartDto newCartDto = new CartDto(cartId, userId);
        return ResponseEntity.ok(newCartDto);
    }
    
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItemDto>> getCart(@PathVariable Long cartId) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        cartItemDtos.add(new CartItemDto(
                1L,"product1","description1", new BigDecimal(100),4L));
        cartItemDtos.add(new CartItemDto(
                2L,"product2","description2", new BigDecimal(200),3L));
        return ResponseEntity.ok(cartItemDtos);
    }

    @PostMapping("/add/{cartId}/{productId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long cartId, @RequestParam Long productId) {
        // below should be executed in DbService
        // Cart cart = findById(cartId)
        // cart.getProductList.add(findProductById(productId);
        // above is just rought representation of execution.
        return new ResponseEntity<>("Product: " + productId + " was added to Cart " + cartId, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/{cartItemId}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        return new ResponseEntity<>("Item: " + cartItemId + " was removed from cart: " + cartId, HttpStatus.OK);
    }

    @PostMapping("/createOrder/{cartId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long cartId) {
        // below should be executed in DbService.
        // Cart cart = findCartById(cartId)
        // List<Product> orderProductList = new ArrayList<>();
        // orderProductList.addAll(cart.getCartProductList())
        // Order order = new Order(orderId, userId, orderDate, orderProductList)
        // cart.getProductList.add(findProduct(productId);
        // above is just rought representation of execution.
        OrderDto orderDto = new OrderDto(cartId, 2L, LocalDate.now());
        return ResponseEntity.ok(orderDto);
    }
}

