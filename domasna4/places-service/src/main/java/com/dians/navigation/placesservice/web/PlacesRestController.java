package com.dians.navigation.placesservice.web;

import com.dians.navigation.placesservice.model.FastFood;
import com.dians.navigation.placesservice.model.Pub;
import com.dians.navigation.placesservice.service.NavigationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/places")
public class PlacesRestController {

    private final NavigationService navigationService;

    public PlacesRestController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @GetMapping("/find/fastFoods")
    public List<FastFood> findAllFastFoods() {
        return navigationService.findAllFastFoods();
    }

    @GetMapping("/find/pubs")
    public List<Pub> findAllPubs() {
        return navigationService.findAllPubs();
    }

    @GetMapping("/search/fastFoods")
    public List<FastFood> searchFastFoods(@RequestParam String searchTerm) {
        navigationService.findAllFastFoods();
        List<FastFood> fastFoods = new ArrayList<>();

        if (!navigationService.findAllFastFoodsByName(searchTerm).isEmpty()) {
            fastFoods = navigationService.findAllFastFoodsByName(searchTerm);
        }
        return fastFoods;
    }

    @GetMapping("/search/pubs")
    public List<Pub> searchPubs(@RequestParam String searchTerm) {
        List<Pub> pubs = new ArrayList<>();

        if (!navigationService.findAllPubsByName(searchTerm).isEmpty()) {
            pubs = navigationService.findAllPubsByName(searchTerm);
        }

        return pubs;
    }

    @PostMapping("/find/fastFoods/{id}")
    public FastFood getFastFoodById(@PathVariable Long id) {
        Optional<FastFood> fastFood = navigationService.findFastFoodById(id);
        return fastFood.orElse(null);
    }

    @PostMapping("/find/pubs/{id}")
    public Pub getPubById(@PathVariable Long id) {
        Optional<Pub> pub = navigationService.findPubById(id);
        return pub.orElse(null);
    }

    @DeleteMapping("/delete/fastFoods/{id}")
    public void deleteFastFood(@PathVariable Long id) {
        navigationService.deleteFastFoodById(id);
    }

    @DeleteMapping("/delete/pubs/{id}")
    public void deletePub(@PathVariable Long id) {
        navigationService.deletePubById(id);
    }
}
