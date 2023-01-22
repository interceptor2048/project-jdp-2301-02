package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.CartNotFoundWhileCreatingOrderException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartDbService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private OrderProductRepository orderProductRepository;
    private OrderService orderService;

    public void createCart(Long UserId) throws UserNotFoundException {
        User user = userRepository.findById(UserId).orElseThrow(UserNotFoundException::new);
        Cart cart = new Cart(user);
        cartRepository.save(cart);
    }

    public Cart getCart(Long cartId) throws CartNotFoundException {
        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Cart addProductToCart(Long cartId, Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product productToAdd = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        cart.getProducts().add(productToAdd);
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long cartId, Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product productToRemove = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        List<Product> products = cart.getProducts();
        if (products.contains(productToRemove)) {
            products.remove(productToRemove);
        } else {
            throw new ProductNotFoundException();
        }
        return cartRepository.save(cart);
    }

    public Order createOrder(Long cartId) throws CartNotFoundWhileCreatingOrderException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundWhileCreatingOrderException::new);
        User user = cart.getUser();
        List<Product> products = cart.getProducts();
        Order newOrder = new Order(LocalDate.now(), user);
        Set<OrderProduct> set = orderService.fromListToSet(products, newOrder);
        newOrder.setOrderProductSet(set);
        orderProductRepository.saveAll(set);
        return orderRepository.save(newOrder);
    }
}
