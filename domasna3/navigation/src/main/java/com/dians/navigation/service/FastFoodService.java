package com.dians.navigation.service;

import com.dians.navigation.model.FastFood;

import java.util.List;

public interface FastFoodService {
    List<FastFood> findAllFastFoods();
    List<FastFood> findAllFastFoodsByName(String name);
}
