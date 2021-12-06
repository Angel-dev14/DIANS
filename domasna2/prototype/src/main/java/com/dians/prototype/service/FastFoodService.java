package com.dians.prototype.service;

import com.dians.prototype.model.FastFood;

import java.util.List;

public interface FastFoodService {
    List<FastFood> findAllFastFoods();
    List<FastFood> findAllFastFoodsByName(String name);
}
