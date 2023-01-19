package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void testGroupSave() {
        //Given
        Group group = new Group();
        //When
        groupRepository.save(group);
        Long id = group.getId();
        Optional<Group> testGroup = groupRepository.findById(id);
        //Then
        assertTrue(testGroup.isPresent());
        assertNotNull(id);
    }
    @Test
    public void testGroupProducts() {
        //Given
        Group group = new Group();
        Product product1 = new Product("Shimano", "Rods", new BigDecimal("940.00"), group);
        Product product2 = new Product("Shimano", "Reals", new BigDecimal("1490.00"), group);
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        //When
        groupRepository.save(group);
        Long groupId = group.getId();
        //Then
        assertNotNull(groupId);
        assertNotNull(product1.getId());
        assertNotNull(product2.getId());
    }
    @Test
    public void testUpdateGroup() {
        //Given
        Group group = new Group();
        Product product1 = new Product("Shimano", "Rods", new BigDecimal("940.00"), group);
        group.getProductList().add(product1);
        groupRepository.save(group);
        Long groupId = group.getId();
        //When
        Product product2 = new Product("Shimano", "Reals", new BigDecimal("1490.00"), group);
        group.getProductList().add(product2);
        //Then
        assertEquals(2, group.getProductList().size());
    }
    @Test
    public void testFindGroupById() {
        //Given
        Group group1 = new Group();
        Group group2 = new Group();
        groupRepository.save(group1);
        groupRepository.save(group2);
        Long group1Id = group1.getId();
        Long group2Id = group2.getId();
        //When
        List<Group> groupList = (List<Group>) groupRepository.findAll();
        //Then
        assertEquals(2, groupList.size());
    }
    @Test
    public void testGroupDeleteProducts() {
        //Given
        Group group = new Group();
        Product product1 = new Product("Shimano", "Rods", new BigDecimal("920.00"), group);
        Product product2 = new Product("Shimano", "Reals", new BigDecimal("1490.00"), group);
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        //When
        groupRepository.save(group);
        Long groupId = group.getId();
        Long product1Id = product1.getId();
        Long product2Id = product2.getId();
        groupRepository.deleteById(groupId);
        //Then
        assertFalse(groupRepository.existsById(groupId));
        assertFalse(productRepository.existsById(product1Id));
        assertFalse(productRepository.existsById(product2Id));
    }
}
