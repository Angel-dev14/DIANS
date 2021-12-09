package com.dians.navigation.service;

import com.dians.navigation.model.Pub;

import java.util.List;

public interface PubService {
    List<Pub> findAllPubs();
    List<Pub> findAllPubsByName(String name);
}
