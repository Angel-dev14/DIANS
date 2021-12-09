package com.dians.navigation.repository;

import com.dians.navigation.model.FastFood;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastFoodRepository extends JpaRepository<FastFood, Long> {

    List<FastFood> findAllByNameContaining(String name);

}
