package com.dians.navigation.placesservice.service.impl;

import com.dians.navigation.placesservice.model.FastFood;
import com.dians.navigation.placesservice.model.Pub;
import com.dians.navigation.placesservice.repository.FastFoodRepository;
import com.dians.navigation.placesservice.repository.PubRepository;
import com.dians.navigation.placesservice.service.AdminService;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final FastFoodRepository fastFoodRepository;
    private final PubRepository pubRepository;

    public AdminServiceImpl(FastFoodRepository fastFoodRepository,
                            PubRepository pubRepository) {
        this.fastFoodRepository = fastFoodRepository;
        this.pubRepository = pubRepository;
    }

    @Override
    public Page<FastFood> findFastFoodPaginated(Integer page, Integer size) {
        return fastFoodRepository.findAll(PageRequest.of(page - 1, size));
    }

    @Override
    public Page<Pub> findPubsPaginated(Integer page, Integer size) {
        return pubRepository.findAll(PageRequest.of(page - 1, size));
    }

    @Override
    @Transactional
    public void deleteFastFoodById(Long id) {
        fastFoodRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deletePubById(Long id) {
        pubRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveFastFood(Long id, String name, Double lat, Double lon) {
        FastFood fastFood;
        if (id != null && fastFoodRepository.findById(id).isPresent()) {
            fastFood = fastFoodRepository.findById(id).get();
            fastFood.setName(name);
            fastFood.setLat(lat);
            fastFood.setLat(lon);
        } else {
            fastFood = new FastFood(lon, lat, name);
        }
        fastFoodRepository.save(fastFood);
    }

    @Override
    @Transactional
    public void savePub(Long id, String name, Double lat, Double lon) {
        Pub pub;
        if (id != null && pubRepository.findById(id).isPresent()) {
            pub = pubRepository.findById(id).get();
            pub.setName(name);
            pub.setLat(lat);
            pub.setLat(lon);
        } else {
            pub = new Pub(lon, lat, name);
        }
        pubRepository.save(pub);
    }
}