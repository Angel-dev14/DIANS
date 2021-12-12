package com.dians.navigation.service;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;

import java.util.List;

public interface NavigationService {

    List<FastFood> findAllFastFoods();

    List<Pub> findAllPubs();

    List<FastFood> findAllFastFoodsByName(String name);

    List<Pub> findAllPubsByName(String name);
}
