package com.dians.navigation.repository;

import com.dians.navigation.model.Pub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PubRepository extends JpaRepository<Pub, Long> {

    List<Pub> findAllByNameContaining(String name);
}
