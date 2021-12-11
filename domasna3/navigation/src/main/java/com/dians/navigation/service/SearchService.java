package com.dians.navigation.service;

import java.util.List;

public interface SearchService {

    List<String> findAllFastFoodNames();

    List<String> findAllFastFoodNamesFromPlaceName(String name);

    List<String> findAllPubNames();

    List<String> findAllPubNamesFromPlaceName(String name);
}
