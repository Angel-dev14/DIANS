package com.dians.prototype.repository;

import com.dians.prototype.model.FastFood;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FastFoodRepository extends JpaRepository<FastFood, Long> {

    List<FastFood> findAllByNameContaining(String name);

}
