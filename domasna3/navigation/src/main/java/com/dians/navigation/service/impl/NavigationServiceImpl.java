package com.dians.navigation.service.impl;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.repository.FastFoodRepository;
import com.dians.navigation.repository.PubRepository;
import com.dians.navigation.service.NavigationService;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Override
    public void deleteFastFoodById(Long id) {
        fastFoodRepository.deleteById(id);
    }

    @Override
    public void deletePubById(Long id) {
        pubRepository.deleteById(id);
    }

    @Override
    public void deleteFastFoodByName(String name) {
        fastFoodRepository.deleteByName(name);
    }

    @Override
    public void deletePubByName(String name) {
        pubRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public void saveFastFood(String name, Double lat, Double lon) {
        fastFoodRepository.deleteByName(name);
        fastFoodRepository.save(new FastFood(lon, lat, name));
    }

    @Override
    @Transactional
    public void savePub(String name, Double lat, Double lon) {
        pubRepository.deleteByName(name);
        pubRepository.save(new Pub(lon, lat, name));
    }

    @Override
    public Optional<FastFood> findFastFoodById(Long id) {
        return fastFoodRepository.findById(id);
    }

    @Override
    public Optional<Pub> findPubById(Long id) {
        return pubRepository.findById(id);
    }

    @Override
    public Page<FastFood> findAllFastFoodsInPage(Integer page) {
        Integer pageNr = null;
        Integer max = fastFoodRepository.findAll(PageRequest.of(1,3)).getTotalPages();
        if(max == 0)
            return null;
        if(page >= max){
            pageNr = max - 1;
        } else if (page < 0) {
            pageNr = 0;
        } else {
            pageNr = page;
        }
        return fastFoodRepository.findAll(PageRequest.of(pageNr,3));
    }

    @Override
    public Page<Pub> findAllPubsInPage(Integer page) {
        Integer pageNr = null;
        Integer max = pubRepository.findAll(PageRequest.of(1,3)).getTotalPages();
        if(max == 0)
            return null;
        if(page >= max){
            pageNr = max - 1;
        } else if (page < 0) {
            pageNr = 0;
        } else {
            pageNr = page;
        }

        return pubRepository.findAll(PageRequest.of(pageNr, 3));
    }

}
