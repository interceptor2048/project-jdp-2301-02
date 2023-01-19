package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {

    private UserRepository userRepository;


    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(cart.getUser().getId(), cart.getProducts());
    }

    public Cart mapToCart(CartDto cartDto) {
        User user = userRepository.findById(cartDto.getUserId()).get();
        return new Cart(user, cartDto.getProducts());
    }
}