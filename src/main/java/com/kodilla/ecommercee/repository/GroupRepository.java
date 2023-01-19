package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    @Override
    List<Group> findAll();

    @Override
    Group save(Group group);

    @Override
    Optional<Group> findById(Long id);

    @Override
    void deleteById(Long id);
}