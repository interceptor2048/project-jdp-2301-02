package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@DisplayName(" Cart Entity Test Suite")
public class CartEntityTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createCartAndSaveToDb() {
        //Given
        Product product1 = new Product(33L,"Product1", "description1", new BigDecimal(344),  new Group(),false);
        Product product2 = new Product(34L,"Product2", "description2", new BigDecimal(837465),new Group(),false);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        User user = new User();
        userRepository.save(user);
        Cart cart = new Cart(1L, user, products);
        cartRepository.save(cart);

        //When
        int numberOfCarts = (int) cartRepository.count();

        //Then
        assertEquals(1, numberOfCarts);

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    void getProductFromCart() {
        //Given
        Product product1 = new Product(33L,"Product1", "description1", new BigDecimal(344),  new Group(),false);
        Product product2 = new Product(34L,"Product2", "description2", new BigDecimal(837465),new Group(),false);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        User user = new User();
        userRepository.save(user);
        Cart cart = new Cart(1L, user, products);
        cartRepository.save(cart);

        //When
        List<Cart> CartsFromDb = (List<Cart>) cartRepository.findAll();
        Cart cartFromDb = CartsFromDb.get(0);
        Product productFromCartFromDb = cartFromDb.getProducts().get(1);
        String name = productFromCartFromDb.getName();

        //Then
        assertEquals("Product2", name);

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    void addProductToCart() {
        //Given
        Product product1 = new Product(33L,"Product1", "description1", new BigDecimal(344),  new Group(),false);
        Product product2 = new Product(34L,"Product2", "description2", new BigDecimal(837465),new Group(), false);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        User user = new User();
        userRepository.save(user);
        Cart cart = new Cart(1L, user, products);
        cartRepository.save(cart);

        //When
        List<Cart> CartsFromDb = (List<Cart>) cartRepository.findAll();
        Cart cartFromDb = CartsFromDb.get(0);
        cartFromDb.getProducts().add(product2);
        cartRepository.save(cartFromDb);
        List<Cart> NewCartsFromDb = (List<Cart>) cartRepository.findAll();
        Cart newCartFromDb = NewCartsFromDb.get(0);
        String product2Description = newCartFromDb.getProducts().get(1).getDescription();

        //Then
        assertEquals("description2", product2Description);

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    void deleteProductFromCart() {
        //Given
        Product product1 = new Product(33L,"Product1", "description1", new BigDecimal(344),  new Group(), false);
        Product product2 = new Product(34L,"Product2", "description2", new BigDecimal(837465),new Group(), false);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        User user = new User();
        userRepository.save(user);
        Cart cart = new Cart(1L, user, products);
        cartRepository.save(cart);

        //When
        List<Cart> cartsFromDb = (List<Cart>) cartRepository.findAll();
        Cart cartFromDb = cartsFromDb.get(0);
        cartFromDb.getProducts().remove(1);
        cartRepository.save(cartFromDb);
        List<Cart> newCartsFromDb = (List<Cart>) cartRepository.findAll();
        Cart newCartFromDb = newCartsFromDb.get(0);
        int numberOfProducts = newCartFromDb.getProducts().size();

        //Then
        assertEquals(1, numberOfProducts);

        //CleanUp
        cartRepository.deleteAll();
    }
}