package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> exampleList = new ArrayList<>();

        for (long i = 1; i < 6; i++) {
            exampleList.add(new ProductDto(
                    i, "Product " + i, "Description " + i, new BigDecimal(10*i), 2L));
        }
        return ResponseEntity.ok(exampleList);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(new ProductDto(
                1L, "Product "+id, "Example description", new BigDecimal(150), 3L));
    }


    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto product) {
        // It will return <Void>, <ProductDto> is only for test with Postman
        return ResponseEntity.ok(product);
    }


    @PutMapping()
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product) {
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        // It will return Void, String is only for test with Postman
        return new ResponseEntity<>("Product " + id + " deleted", HttpStatus.OK);
    }

}
