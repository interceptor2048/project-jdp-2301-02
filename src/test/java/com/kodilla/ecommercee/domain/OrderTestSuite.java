package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.exception.CartNotFoundWhileCreatingOrderException;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.repository.*;
import com.kodilla.ecommercee.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private  OrderProductRepository orderProductRepository;
    @Autowired
    private OrderService orderService;

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
        Order order1 = new Order( LocalDate.of(2023, 1, 10), user1);

        Map<Product, Long> counts = productToOrder.stream().
                collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        Set<OrderProduct> orderProductSet = new HashSet<>();
        for (Map.Entry<Product, Long> entry : counts.entrySet()){
            OrderProduct op=new OrderProduct(order1, entry.getKey(), entry.getKey().getPrice(), entry.getValue());
            orderProductSet.add(op);
            orderProductRepository.save(op);
        }
        order1.setOrderProductSet(orderProductSet);
        user1.getOrderIdList().add(order1);
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
        Order order1 = new Order( LocalDate.of(2023, 1, 10), user1);
        user1.getOrderIdList().add(order1);
        Set<OrderProduct>productOrder = orderService.fromListToSet(productToOrder, order1);
        orderProductRepository.saveAll(productOrder);
        order1.setOrderProductSet(productOrder);

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
        Set<OrderProduct> set = orderService.fromListToSet(orderFromDb.getUser().getCart().getProducts(), orderFromDb);
        orderProductRepository.saveAll(set);
        orderFromDb.setOrderProductSet(set);
        orderRepository.save(orderFromDb);
        book.getCartList().add(orderFromDb.getUser().getCart());
        cartRepository.save(orderFromDb.getUser().getCart());

        //When
        orderRepository.save(orderFromDb);
        List<Order> updatedOrderList = orderRepository.findAll();
        Order updatedOrder = updatedOrderList.get(0);
        OrderProduct op1 = updatedOrder.getOrderProductSet().stream().filter(i->i.getProduct().equals(book)).findFirst().get();

        //Then
        assertEquals(3, updatedOrder.getUser().getCart().getProducts().size());
        assertEquals(3, updatedOrder.getOrderProductSet().size());
        assertEquals(1, op1.getQty());
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



    @Transactional
    @Test
    void testCreateFromOrderService() throws CartNotFoundWhileCreatingOrderException {

        //Given
        User user = new User("Adam009", UserStatus.NOT_LOGGED_IN, "abc");
        userRepository.save(user);
        Cart cart = new Cart(user);
        cart.getProducts().add(new Product("ded", "ec", new BigDecimal(100), new Group("ede", "inne")));
        cartRepository.save(cart);

        // When
        orderService.createOrder(cart.getId());
        List<Order> o = orderService.getALlOrders();

        //Then
        assertEquals(1, o.size());
    }



    @Transactional
    @Test
    void testRemoveFromOrderService() throws CartNotFoundWhileCreatingOrderException, OrderNotFoundException {

        //Given
        User user = new User("Adam009", UserStatus.NOT_LOGGED_IN, "abc");
        userRepository.save(user);
        Cart cart = new Cart(user);
        cart.getProducts().add(new Product("Product1", "esc", new BigDecimal(100), new Group("Group1", "Other")));
        cartRepository.save(cart);
        orderService.createOrder(cart.getId());
        List<Order> o = orderService.getALlOrders();
        Order order1 = orderService.getALlOrders().get(0);

        // When
        orderService.deleteOrder(order1.getId());
        List<Order> oAfterDelete = orderService.getALlOrders();

        //Then
        assertEquals(1, o.size());
        assertEquals(0, oAfterDelete.size());
    }






}
