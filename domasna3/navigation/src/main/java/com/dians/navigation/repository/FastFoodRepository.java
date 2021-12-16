package com.dians.navigation.repository;

import com.dians.navigation.model.FastFood;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FastFoodRepository extends JpaRepository<FastFood, Long> {

    @Query("SELECT name FROM FastFood ")
    List<String> findAllFastFoodNames();

    @Query("SELECT name FROM FastFood  WHERE name LIKE %?1% ")
    List<String> findAllFastFoodNamesFromPlaceName(String place);

    List<FastFood> findAllByNameContaining(String name);

    Page<FastFood> findAll(Pageable pageable);

    void deleteByName(String name);

    void deleteById(Long id);
}
