package com.dians.navigation.placesservice.repository;

import com.dians.navigation.placesservice.model.FastFood;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastFoodRepository extends JpaRepository<FastFood, Long> {

    List<FastFood> findAllByNameContainingIgnoreCase(String name);

    Page<FastFood> findAll(Pageable pageable);

    void deleteByName(String name);

    void deleteById(Long id);
}
