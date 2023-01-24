package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderProduct;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.OrderDto;

import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {


    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public OrderDto mapToOrderDto(Order order) {
        List<Product> listOfProducts = new ArrayList<>();
        for(OrderProduct op : order.getOrderProductSet()){
            for(Long l = 1L; l<=op.getQty(); l++){
                listOfProducts.add(op.getProduct());
            }
        }
        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                order.getOrderDate(),
                productMapper.mapToProductDtoList(listOfProducts)
        );
    }


    public Order mapToOrder(OrderDto orderDto) throws UserNotFoundException {
        List<Product> list = productMapper.mapToProductList(orderDto.getProductList());
        Map<Product, Long> counts =
                list.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Set<OrderProduct> orderProductSet = new HashSet<>();
        Order order = new Order(orderDto.getId(), orderDto.getOrderDate(), user);
        for (Map.Entry<Product, Long> entry : counts.entrySet()){
            orderProductSet.add(new OrderProduct(order, entry.getKey(), entry.getKey().getPrice(), entry.getValue()));
        }
        order.setOrderProductSet(orderProductSet);
        return order;
    }


    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }


//    private List<Order> mapToOrderList(List<OrderDto> orderDtoList) {
//        return orderDtoList.stream()
//                .map(this::mapToOrder)
//                .collect(Collectors.toList());
//    }
}
