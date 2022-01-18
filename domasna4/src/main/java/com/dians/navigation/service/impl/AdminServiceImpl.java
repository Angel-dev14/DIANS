package com.dians.navigation.service.impl;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.repository.FastFoodRepository;
import com.dians.navigation.repository.PubRepository;
import com.dians.navigation.service.AdminService;
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
    public Page<FastFood> findAllFastFoodsInPage(Integer page) {
        Integer pageNr = null;
        Integer max = fastFoodRepository.findAll(PageRequest.of(1, 3)).getTotalPages();
        if (max == 0) {
            return null;
        }
        if (page >= max) {
            pageNr = max - 1;
        } else if (page < 0) {
            pageNr = 0;
        } else {
            pageNr = page;
        }
        return fastFoodRepository.findAll(PageRequest.of(pageNr, 3));
    }

    @Override
    public Page<Pub> findAllPubsInPage(Integer page) {
        Integer pageNr = null;
        Integer max = pubRepository.findAll(PageRequest.of(1, 3)).getTotalPages();
        if (max == 0) {
            return null;
        }
        if (page >= max) {
            pageNr = max - 1;
        } else if (page < 0) {
            pageNr = 0;
        } else {
            pageNr = page;
        }

        return pubRepository.findAll(PageRequest.of(pageNr, 3));
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