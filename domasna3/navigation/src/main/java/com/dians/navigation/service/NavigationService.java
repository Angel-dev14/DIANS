package com.dians.navigation.service;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;

import java.util.List;

public interface NavigationService {

    public List<FastFood> findAllFastFoods();

    public List<Pub> findAllPubs();

    public List<FastFood> findAllFastFoodsByName(String name);

    public List<Pub> findAllPubsByName(String name);
}
