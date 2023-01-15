package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Transactional
class GroupTestSuite {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    ProductRepository productRepository;


    private Group shirt;
    private Group shorts;
    private List<Product> allProducts;


    @BeforeEach
    public void init() {
        groupRepository.deleteAll();
    }

    @Test
    public void testCreateNewGroup() {
        //Given
        shirt = new Group();
        shorts = new Group();
        shirt = groupRepository.save(shirt);
        shorts = groupRepository.save(shorts);
        Group newGroup = new Group();
        //When
        groupRepository.save(newGroup);
        //Then
        List<Group> allGroups = (List<Group>) groupRepository.findAll();
        assertEquals(3, allGroups.size());
        //cleanup
        deleteAll();
    }

    @Test
    public void testFindGroupById() {
        //When
        shirt = new Group();
        groupRepository.save(shirt);
        Group newGroup = groupRepository.findById(shirt.getId()).get();
        //Then
        assertNotNull(newGroup);
        assertEquals(shirt.getId(), newGroup.getId());
        //clean up
        deleteAll();
    }

    @Test
    public void testUpdateGroup() {
        //Given
        shorts = new Group();
        groupRepository.save(shorts);
        String newName = "group of clothes";
        Group group1 = groupRepository.findById(shorts.getId()).get();
        assertNotNull(group1);
        //When
        group1.setName(newName);
        group1 = groupRepository.findById(shorts.getId()).get();
        //Then
        assertEquals(newName, group1.getName());
        //clean up
        deleteAll();
    }
    @Test
    public void testDeleteGroup() {
        //Given
        shirt = new Group();
        shorts = new Group();
        shirt = groupRepository.save(shirt);
        shorts = groupRepository.save(shorts);
        List<Group> allGroups = (List<Group>) groupRepository.findAll();
        assertEquals(2, allGroups.size());
        //When
        groupRepository.deleteById(shirt.getId());
        allGroups = (List<Group>) groupRepository.findAll();
        //Then
        assertEquals(1, allGroups.size());
        //clean up
        deleteAll();
    }

    private void deleteAll() {
        groupRepository.deleteAll();
    }
}