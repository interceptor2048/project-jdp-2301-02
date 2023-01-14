package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

}
