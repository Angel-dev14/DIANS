package com.dians.navigation.service.imp;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.repository.FastFoodRepository;
import com.dians.navigation.service.FastFoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FastFoodServiceImpl implements FastFoodService {

    private final FastFoodRepository fastFoodRepository;

    public FastFoodServiceImpl(FastFoodRepository fastFoodRepository) {
        this.fastFoodRepository = fastFoodRepository;
    }

    @Override
    public List<FastFood> findAllFastFoods() {
        return fastFoodRepository.findAll();
    }

    @Override
    public List<FastFood> findAllFastFoodsByName(String name) {
        return fastFoodRepository.findAllByNameContaining(name);
    }
}
