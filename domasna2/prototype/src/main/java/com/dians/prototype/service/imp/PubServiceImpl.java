package com.dians.prototype.service.imp;

import com.dians.prototype.model.Pub;
import com.dians.prototype.repository.PubRepository;
import com.dians.prototype.service.PubService;
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
