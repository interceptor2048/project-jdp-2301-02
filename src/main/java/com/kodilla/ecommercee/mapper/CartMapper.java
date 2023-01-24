package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final UserRepository userRepository;
    private final ProductMapper productMapper;


    public CartDto mapToCartDto(Cart cart) {
        List<Product>list=cart.getProducts();
        productMapper.mapToProductDtoList(list);
        return new CartDto(cart.getUser().getId(), productMapper.mapToProductDtoList(list));
    }

    public Cart mapToCart(CartDto cartDto) throws UserNotFoundException {
        User user = userRepository.findById(cartDto.getUserId()).orElseThrow(UserNotFoundException::new);
        List<Product>list=productMapper.mapToProductList(cartDto.getProducts());
        return new Cart(user, list);
    }
}