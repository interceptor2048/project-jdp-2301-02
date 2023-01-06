package com.kodilla.ecommercee.CartController.CartDomainDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartItemDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long groupId;
}
