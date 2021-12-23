package com.dians.navigation.web.controller;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.service.NavigationService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/navigation")
public class NavigationController {

    private final NavigationService navigationService;

    public NavigationController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @GetMapping("/fastFoods")
    public ResponseEntity<List<FastFood>> getAllFastFoods() {
        return ResponseEntity.ok().body(navigationService.findAllFastFoods());
    }

    @GetMapping("/pubs")
    public ResponseEntity<List<Pub>> getAllPubs() {
        return ResponseEntity.ok().body(navigationService.findAllPubs());
    }
}
