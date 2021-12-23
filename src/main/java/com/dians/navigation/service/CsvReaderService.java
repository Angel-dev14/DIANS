package com.dians.navigation.service;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.repository.FastFoodRepository;
import com.dians.navigation.repository.PubRepository;
import com.dians.navigation.service.utils.CsvUtils;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvReaderService {

    private final FastFoodRepository fastFoodRepository;
    private final PubRepository pubRepository;

    public CsvReaderService(FastFoodRepository repository, PubRepository pubRepository) {
        this.fastFoodRepository = repository;
        this.pubRepository = pubRepository;
    }

    public void readFile(MultipartFile file) throws IOException {
        if (file.getOriginalFilename().equals("fast_foods.csv")) {
            fastFoodRepository.saveAll(CsvUtils.read(FastFood.class, file.getInputStream()));
        } else {
            pubRepository.saveAll(CsvUtils.read(Pub.class, file.getInputStream()));
        }
    }
}
