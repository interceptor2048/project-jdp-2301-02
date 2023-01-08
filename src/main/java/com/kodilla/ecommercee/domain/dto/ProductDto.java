package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long groupId;
}