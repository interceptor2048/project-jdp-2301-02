package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.*;
import com.kodilla.ecommercee.exception.*;
import com.kodilla.ecommercee.mapper.*;
import com.kodilla.ecommercee.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductDbService productDbService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productMapper.mapToProductDtoList(productDbService.getAllProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) throws RecordNotExistsException {
        return ResponseEntity.ok(productMapper.mapToProductDto(productDbService.getProduct(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addNewProduct(@RequestBody ProductSaveDto product)
            throws Exception {
        productDbService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateProduct(@RequestBody ProductUpdateDto productUpdateDto)
            throws RecordExistsException, EmptyFieldException {
        productDbService.updateProduct(productUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws RecordNotExistsException {
        productDbService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}