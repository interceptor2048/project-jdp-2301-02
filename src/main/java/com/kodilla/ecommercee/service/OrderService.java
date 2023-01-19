package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exception.CartNotFoundWhileCreatingOrderException;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.OrderWithGivenUserNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;


    public List<Order> getALlOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public void createOrder(Long cartId) throws CartNotFoundWhileCreatingOrderException {
        if (cartRepository.findById(cartId).isPresent()) {
            Cart cart = cartRepository.findById(cartId).get();
            orderRepository.save(new Order(LocalDate.now(), cart.getUser(), cart.getProducts()));
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
                order.setProductList(orderDto.getProductList());
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

}
