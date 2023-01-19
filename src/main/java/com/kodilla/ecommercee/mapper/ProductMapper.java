package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public Product mapToProduct(final ProductSaveDto productSaveDto){
        Product product = new Product();
        product.setName(productSaveDto.getName());
        product.setDescription(productSaveDto.getDescription());
        product.setPrice(productSaveDto.getPrice());
        product.setQty((productSaveDto.getQty()));
        return product;
    }
    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup() != null ? String.valueOf(product.getGroup().getId()) : null
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}