package com.dians.navigation.placesservice.service;

import com.dians.navigation.placesservice.model.FastFood;
import com.dians.navigation.placesservice.model.Pub;
import org.springframework.data.domain.Page;

public interface AdminService {

    Page<FastFood> findFastFoodPaginated(Integer page, Integer size);

    Page<Pub> findPubsPaginated(Integer page, Integer size);

    void deleteFastFoodById(Long id);

    void deletePubById(Long id);

    void saveFastFood(Long id, String name, Double lat, Double lon);

    void savePub(Long id, String name, Double lat, Double lon);
}