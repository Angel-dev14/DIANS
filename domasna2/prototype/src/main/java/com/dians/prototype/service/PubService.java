package com.dians.prototype.service;

import com.dians.prototype.model.Pub;

import java.util.List;

public interface PubService {
    List<Pub> findAllPubs();
    List<Pub> findAllPubsByName(String name);
}
