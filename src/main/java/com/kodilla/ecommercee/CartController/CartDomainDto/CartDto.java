package com.kodilla.ecommercee.CartController.CartDomainDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartDto {
    private List<CartItemDto> cart;

}
