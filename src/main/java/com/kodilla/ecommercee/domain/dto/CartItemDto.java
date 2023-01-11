package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
