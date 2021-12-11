package com.dians.navigation.service.imp;

import com.dians.navigation.repository.FastFoodRepository;
import com.dians.navigation.repository.PubRepository;
import com.dians.navigation.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final FastFoodRepository fastFoodRepository;
    private final PubRepository pubRepository;

    public SearchServiceImpl(FastFoodRepository fastFoodRepository, PubRepository pubRepository) {
        this.fastFoodRepository = fastFoodRepository;
        this.pubRepository = pubRepository;
    }


    @Override
    public List<String> findAllFastFoodNames() {

        return fastFoodRepository.findAllFastFoodNames();
    }

    @Override
    public List<String> findAllFastFoodNamesFromPlaceName(String name) {
        return fastFoodRepository.findAllFastFoodNamesFromPlaceName(name);
    }

    @Override
    public List<String> findAllPubNames() {
        return pubRepository.findAllPubNames();
    }

    @Override
    public List<String> findAllPubNamesFromPlaceName(String name) {
        return pubRepository.findAllPubNamesFromPlaceName(name);
    }
}
