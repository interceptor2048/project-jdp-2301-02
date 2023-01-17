package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CartRepository cartRepository;

    private void prepareOrder(){
        User user1 = new User( "nowak997", UserStatus.NOT_LOGGED_IN, "pass");
        Cart cart1 = new Cart( user1);
        user1.setCart(cart1);
        userRepository.save(user1);
        Group group1 = new Group( "RTV", "Desc");
        Product laptop = new Product( "Dell", "Dell Inspiron 7610", new BigDecimal(5600), group1);
        Product phone = new Product( "Samsung", "Samsung A52", new BigDecimal(990), group1);
        group1.getProductList().add(laptop);
        group1.getProductList().add(phone);
        groupRepository.save(group1);
        user1.getCart().getProducts().add(laptop);
        user1.getCart().getProducts().add(phone);
        phone.getCartList().add(cart1);
        laptop.getCartList().add(cart1);
        cartRepository.save(cart1);
        List<Product> productsFromSavedCart = cartRepository.findById(user1.getCart().getId()).get().getProducts();
        List<Product> productToOrder = new ArrayList<>(productsFromSavedCart);
        Order order1 = new Order( LocalDate.of(2023, 1, 10), user1, productToOrder);
        user1.getOrderIdList().add(order1);
        laptop.getOrderList().add(order1);
        phone.getOrderList().add(order1);
        orderRepository.save(order1);
    }

    @Test
    @Transactional
    public void testCreateFullOrder() {

        //Given
        User user1 = new User( "nowak997", UserStatus.NOT_LOGGED_IN, "pass");
        Cart cart1 = new Cart( user1);
        user1.setCart(cart1);
        userRepository.save(user1);
        Group group1 = new Group( "RTV", "Desc");
        Product laptop = new Product( "Dell", "Dell Inspiron 7610", new BigDecimal(5600), group1);
        Product phone = new Product( "Samsung", "Samsung A52", new BigDecimal(990), group1);
        group1.getProductList().add(laptop);
        group1.getProductList().add(phone);
        groupRepository.save(group1);
        user1.getCart().getProducts().add(laptop);
        user1.getCart().getProducts().add(phone);
        phone.getCartList().add(cart1);
        laptop.getCartList().add(cart1);
        cartRepository.save(cart1);
        List<Product> productsFromSavedCart = cartRepository.findById(user1.getCart().getId()).get().getProducts();
        List<Product> productToOrder = new ArrayList<>(productsFromSavedCart);
        Order order1 = new Order( LocalDate.of(2023, 1, 10), user1, productToOrder);
        user1.getOrderIdList().add(order1);
        laptop.getOrderList().add(order1);
        phone.getOrderList().add(order1);

        //When
        orderRepository.save(order1);
        int result = orderRepository.findAll().size();
        List<Order> orderList = orderRepository.findAll();
        //Then
        assertEquals(1, result);
        assertEquals("nowak997", orderList.get(0).getUser().getUsername());
        assertEquals(2, orderList.get(0).getUser().getCart().getProducts().size());

        //CleanUp
        try {
            productRepository.deleteAll();
            cartRepository.deleteAll();
            userRepository.deleteAll();
            groupRepository.deleteAll();
            orderRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("Error...");
        }
    }

    @Test
    @Transactional
    public void testGetAllOrdersFromRepo() {
        //Given
        prepareOrder();//prepare one order, for one user and with two products, then save order to database

        //When
        List<Order> orderList = orderRepository.findAll();

        //Then
        assertEquals(1, orderList.size());
        assertEquals(LocalDate.of(2023, 1, 10), orderList.get(0).getOrderDate() );

        //CleanUp
        try {
            productRepository.deleteAll();
            cartRepository.deleteAll();
            userRepository.deleteAll();
            groupRepository.deleteAll();
            orderRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("Error...");
        }
    }

    @Test
    @Transactional
    public void testUpdateOrder() {
        //Given
        prepareOrder();//prepare one order, for one user and with two products, then save order to database

        List<Order> orderList = orderRepository.findAll();
        Order orderFromDb = orderList.get(0);
        Group group2 = new Group( "Books", "Desc");
        Product book = new Product( "Road to Java Junior", "Tom Burton, 2019", new BigDecimal(50), group2);
        group2.getProductList().add(book);
        groupRepository.save(group2);
        orderFromDb.getUser().getCart().getProducts().add(book);
        orderFromDb.getProductList().add(book);
        book.getCartList().add(orderFromDb.getUser().getCart());
        cartRepository.save(orderFromDb.getUser().getCart());

        //When
        orderRepository.save(orderFromDb);
        List<Order> updatedOrderList = orderRepository.findAll();
        Order updatedOrder = updatedOrderList.get(0);

        //Then
        assertEquals(3, updatedOrder.getUser().getCart().getProducts().size());
        assertEquals(3, updatedOrder.getUser().getOrderIdList().get(0).getProductList().size());
        assertEquals("nowak997", updatedOrderList.get(0).getUser().getUsername());

        //CleanUp
        try {
            productRepository.deleteAll();
            cartRepository.deleteAll();
            userRepository.deleteAll();
            groupRepository.deleteAll();
            orderRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("Error...");
        }
    }

    @Test
    @Transactional
    public void testDeleteOrder(){
        //Given
        prepareOrder();//prepare one order, for one user and with two products, then save order to database

        List<Order> ordersFromRepository = orderRepository.findAll();
        Order orderFromBase = ordersFromRepository.get(0);

        //When
        orderFromBase.getUser().getOrderIdList().remove(orderFromBase);
        userRepository.save(orderFromBase.getUser());
        orderFromBase.getProductList().forEach(product -> product.getOrderList().remove(orderFromBase));
        orderRepository.deleteById(orderFromBase.getId());
        List<Order> ordersFromRepositoryAfterDeletingOne = orderRepository.findAll();

        //Then
        assertNotEquals(ordersFromRepository.size(), ordersFromRepositoryAfterDeletingOne.size());
        assertEquals(0, ordersFromRepositoryAfterDeletingOne.size());

        //CleanUp
        try {
            productRepository.deleteAll();
            cartRepository.deleteAll();
            userRepository.deleteAll();
            groupRepository.deleteAll();
            orderRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("Error...");
        }
    }

    @Test
    public void testSaveUserWithOrders() {
        //Given
        Order order1 = new Order();
        Order order2 = new Order();
        User user = new User();
        user.getOrderIdList().add(order1);
        user.getOrderIdList().add(order2);

        //When
        userRepository.save(user);
        Long userId = user.getId();
        Long order1Id = order1.getId();
        Long order2Id = order2.getId();

        //Then
        assertNotEquals(null, userId);
        assertNotEquals(null, order1Id);
        assertNotEquals(null, order2Id);
        assertEquals(2, orderRepository.count());

        // CleanUp
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }
}
