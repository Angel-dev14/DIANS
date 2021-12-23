package com.dians.navigation.service;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface NavigationService {

    List<FastFood> findAllFastFoods();

    List<Pub> findAllPubs();

    List<FastFood> findAllFastFoodsByName(String name);

    List<Pub> findAllPubsByName(String name);

    void deleteFastFoodById(Long id);

    void deletePubById(Long id);

    void deleteFastFoodByName(String name);

    void deletePubByName(String name);

    void saveFastFood(String name, Double lat, Double lon);

    void savePub(String name, Double lat, Double lon);

    Optional<FastFood> findFastFoodById(Long id);

    Optional<Pub> findPubById(Long id);

    Page<FastFood> findAllFastFoodsInPage(Integer page);

    Page<Pub> findAllPubsInPage(Integer page);
}
