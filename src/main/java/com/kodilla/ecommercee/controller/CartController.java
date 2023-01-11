package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
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
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<>("New cart was created for userid: " + cartDto.getUserId(), HttpStatus.OK);
    }
    
    @GetMapping("/products")
    public ResponseEntity<List<CartItemDto>> getCart(@RequestParam long cartId) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        cartItemDtos.add(new CartItemDto(
                1L,"product1","description1", new BigDecimal(100),4L));
        cartItemDtos.add(new CartItemDto(
                2L,"product2","description2", new BigDecimal(200),3L));
        return ResponseEntity.ok(cartItemDtos);
    }

    @PutMapping("/add/{cartId}")
    public ResponseEntity<String> addProductToCart(@PathVariable long cartId, @RequestParam long productId) {
        // below should be executed in DbService
        // Cart cart = findById(cartId)
        // cart.getProductList.add(findProductById(productId);
        // above is just rought representation of execution.
        return new ResponseEntity<>("Product: " + productId + " was added to cart: " + cartId, HttpStatus.OK);
    }

    @PutMapping("/remove")
    public ResponseEntity<String> removeProductFromCart(@RequestParam long cartId, @RequestParam long productId) {
        // below should be executed in DbService
        // Cart cart = findById(cartId)
        // cart.getProductList.remove(findProductById(productId);
        // above is just rought representation of execution.
        return new ResponseEntity<>("Product: " + productId + " was removed from cart: " + cartId, HttpStatus.OK);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDto> createOrder(@RequestParam long cartId) {
        // below should be executed in DbService.
        // Cart cart = findCartById(cartId)
        // List<Product> orderProductList = new ArrayList<>();
        // orderProductList.addAll(cart.getCartProductList())
        // Order order = new Order(orderId, userId, orderDate, orderProductList)
        // cart.getProductList.add(findProduct(productId);
        // above is just rought representation of execution.
        OrderDto orderDto = new OrderDto(cartId,2L, LocalDate.now());
        return ResponseEntity.ok(orderDto);
    }
}

