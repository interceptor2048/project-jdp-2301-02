package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.CartNotFoundWhileCreatingOrderException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartDbService cartDbService;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException {
        cartDbService.createCart(cartDto.getUserId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable long cartId) throws CartNotFoundException {
        Cart cart = cartDbService.getCart(cartId);
        CartDto cartDto = cartMapper.mapToCartDto(cart);
        return ResponseEntity.ok(cartDto);
    }

    @PutMapping("/add/{cartId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart updatedCart = cartDbService.addProductToCart(cartId, productId);
        CartDto cartDto = cartMapper.mapToCartDto(updatedCart);
        return ResponseEntity.ok(cartDto);
    }

    @PutMapping("/remove")
    public ResponseEntity<CartDto> removeProductFromCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart updatedCart = cartDbService.removeProductFromCart(cartId, productId);
        CartDto cartDto = cartMapper.mapToCartDto(updatedCart);
        return ResponseEntity.ok(cartDto);
     }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDto> createOrder(@RequestParam long cartId) throws CartNotFoundWhileCreatingOrderException {
        Order newOrder = cartDbService.createOrder(cartId);
        OrderDto orderDto = orderMapper.mapToOrderDto(newOrder);
        return ResponseEntity.ok(orderDto);
    }
}