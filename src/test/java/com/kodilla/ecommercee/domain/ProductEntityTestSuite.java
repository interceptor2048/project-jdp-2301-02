package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
public class ProductEntityTestSuite {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetAll() {
        //Given
        Group group = new Group("pet food", "food for pets");
        Product product1 = new Product("whiskas", "delicious cat food", new BigDecimal(12.99), group);
        Product product2 = new Product("purina", "healthy cat food", new BigDecimal(14.99), group);
        Product product3 = new Product("royal canin", "natural cat food", new BigDecimal(19.99), group);
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        group.getProductList().add(product3);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        //When
        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        products.forEach(productList::add);

        //Then
        assertEquals(3, productList.size());
    }

    @Test
    public void testGetOne() {
        //Given
        Group group = new Group("pet food", "food for pets");
        Product product1 = new Product("whiskas", "delicious cat food", new BigDecimal(12.99), group);
        Product product2 = new Product("purina", "healthy cat food", new BigDecimal(14.99), group);
        Product product3 = new Product("royal canin", "natural cat food", new BigDecimal(19.99), group);
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        group.getProductList().add(product3);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        Long id = product1.getId();

        //When
        Optional<Product> product= productRepository.findById(id);

        //Then
        assertEquals("whiskas", product.orElse(new Product()).getName());
    }

    @Test
    public void testUpdate() {
        //Given
        Group group = new Group("pet food", "food for pets");
        Product product1 = new Product("whiskas", "delicious cat food", new BigDecimal(12.99), group);
        Product product2 = new Product("purina", "healthy cat food", new BigDecimal(14.99), group);
        Product product3 = new Product("royal canin", "natural cat food", new BigDecimal(19.99), group);
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        group.getProductList().add(product3);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        Long id = product1.getId();

        //When
        product1.setDescription("cat food");
        productRepository.save(product1);
        Optional<Product> product= productRepository.findById(id);

        //Then
        assertEquals("cat food", product.orElse(new Product()).getDescription());
    }

    @Test
    public void testDelete() {
        //Given
        Group group = new Group("pet food", "food for pets");
        Product product1 = new Product("whiskas", "delicious cat food", new BigDecimal(12.99), group);
        Product product2 = new Product("purina", "healthy cat food", new BigDecimal(14.99), group);
        Product product3 = new Product("royal canin", "natural cat food", new BigDecimal(19.99), group);
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        group.getProductList().add(product3);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        Long id = product1.getId();

        //When
        productRepository.deleteById(id);
        Optional<Product> product= productRepository.findById(id);

        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        products.forEach(productList::add);

        //Then
        assertFalse(product.isPresent());
    }
}
