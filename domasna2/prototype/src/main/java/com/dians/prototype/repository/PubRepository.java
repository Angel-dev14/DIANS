package com.dians.prototype.repository;

import com.dians.prototype.model.Pub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PubRepository extends JpaRepository<Pub, Long> {
    List<Pub> findAllByNameContaining(String name);
}
