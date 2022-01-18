package com.dians.navigation.service;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;

import java.util.List;
import java.util.Optional;

public interface NavigationService {

    List<FastFood> findAllFastFoods();

    List<Pub> findAllPubs();

    List<FastFood> findAllFastFoodsByName(String name);

    List<Pub> findAllPubsByName(String name);

    Optional<FastFood> findFastFoodById(Long id);

    Optional<Pub> findPubById(Long id);
}
