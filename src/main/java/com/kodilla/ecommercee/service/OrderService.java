package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exception.CartNotFoundWhileCreatingOrderException;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.OrderWithGivenUserNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderProductRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductMapper productMapper;



    public List<Order> getALlOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public void createOrder(Long cartId) throws CartNotFoundWhileCreatingOrderException {
        if (cartRepository.findById(cartId).isPresent()) {
            Cart cart = cartRepository.findById(cartId).get();
            Order order = new Order(LocalDate.now(), cart.getUser());
            orderRepository.save(order);
            Set<OrderProduct> orderProductSet = fromListToSet(cart.getProducts(), order);
            order.setOrderProductSet(orderProductSet);

            orderProductRepository.saveAll(orderProductSet);
            orderRepository.save(order);
        } else {
            throw new CartNotFoundWhileCreatingOrderException();
        }
    }

    public Order updateOrder(OrderDto orderDto) throws OrderNotFoundException, OrderWithGivenUserNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(orderDto.getId());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getUser().getId() == orderDto.getUserId()){
                order.setOrderDate(orderDto.getOrderDate());
                List<Product>list = productMapper.mapToProductList(orderDto.getProductList());
                Set<OrderProduct>set = fromListToSet(list, order);
                order.setOrderProductSet(set);
                orderProductRepository.saveAll(set);
                return orderRepository.save(order);
            } else {
                throw new OrderWithGivenUserNotFoundException();
            }
        } else {
            throw new OrderNotFoundException();
        }
    }

    public void deleteOrder(Long orderId) throws OrderNotFoundException {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new OrderNotFoundException();
        }
    }



    public Set<OrderProduct> fromListToSet(List<Product> list, Order order){
        Set<OrderProduct> set = new HashSet<>();
        Map<Product, Long> counts =
                list.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        for (Map.Entry<Product, Long> entry : counts.entrySet()){
            set.add(new OrderProduct(order, entry.getKey(), entry.getKey().getPrice(), entry.getValue()));
        }
        return set;
    }

    public List<Product> fromSetToList(Set<OrderProduct> set){
        List<Product> list = new ArrayList<>();
        for(OrderProduct op : set){
            for(Long l = 1L; l<=op.getQty(); l++){
                list.add(op.getProduct());
            }
        }
        return list;
    }


}
