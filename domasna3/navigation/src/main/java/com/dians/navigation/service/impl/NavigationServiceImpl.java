package com.dians.navigation.service.impl;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.repository.FastFoodRepository;
import com.dians.navigation.repository.PubRepository;
import com.dians.navigation.service.NavigationService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NavigationServiceImpl implements NavigationService {

    private final FastFoodRepository fastFoodRepository;
    private final PubRepository pubRepository;

    public NavigationServiceImpl(FastFoodRepository fastFoodRepository,
                                 PubRepository pubRepository) {
        this.fastFoodRepository = fastFoodRepository;
        this.pubRepository = pubRepository;
    }

    @Override
    public List<FastFood> findAllFastFoods() {
        return fastFoodRepository.findAll();
    }

    @Override
    public List<Pub> findAllPubs() {
        return pubRepository.findAll();
    }

    @Override
    public List<FastFood> findAllFastFoodsByName(String name) {
        return fastFoodRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Pub> findAllPubsByName(String name) {
        return pubRepository.findAllByNameContaining(name);
    }
}