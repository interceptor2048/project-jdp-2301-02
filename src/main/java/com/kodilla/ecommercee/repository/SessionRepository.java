package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
}
