package com.dians.navigation.service.imp;

import com.dians.navigation.model.Pub;
import com.dians.navigation.repository.PubRepository;
import com.dians.navigation.service.PubService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PubServiceImpl implements PubService {

    private final PubRepository pubRepository;

    public PubServiceImpl(PubRepository pubRepository) {
        this.pubRepository = pubRepository;
    }

    @Override
    public List<Pub> findAllPubs() {
        return pubRepository.findAll();
    }

    @Override
    public List<Pub> findAllPubsByName(String name) {
        return pubRepository.findAllByNameContaining(name);
    }
}
