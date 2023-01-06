package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderRequestDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> sampleList = new ArrayList<>();
        sampleList.add(new OrderDto(1L, 2L, LocalDate.of(2022, 5, 10)));
        return ResponseEntity.ok(sampleList);
    }

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        OrderDto sampleOrderDto = new OrderDto(1L,2L,LocalDate.of(2022,5,10));
        return ResponseEntity.ok(sampleOrderDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderDto sampleOrderDto = new OrderDto(1L,2L,LocalDate.of(2022,5,10));
        return ResponseEntity.ok(sampleOrderDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderDto sampleOrderDto = new OrderDto(1L,2L,LocalDate.of(2022,5,10));
        return ResponseEntity.ok(sampleOrderDto);
    }

    @DeleteMapping(path = "/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok().build();
    }
}
