package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.OrderProduct;
import com.kodilla.ecommercee.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private LocalDate orderDate;
    private List<ProductDto> productList;
}
