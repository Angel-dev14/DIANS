package com.dians.navigation.controller;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.service.NavigationService;
import com.dians.navigation.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final SearchService searchService;
    private final NavigationService navigationService;

    public MainController(SearchService searchService, NavigationService navigationService) {
        this.searchService = searchService;
        this.navigationService = navigationService;
    }

    @GetMapping("")
    public String getIndexPage(Model model){
        List<String> pubs = searchService.findAllPubNames();
        List<String> fastFoods = searchService.findAllFastFoodNames();
        model.addAttribute("pubs", pubs);
        model.addAttribute("fastFoods", fastFoods);

        List<Pub> pubObs = navigationService.findAllPubs();
        List<FastFood> fastFoodObs = navigationService.findAllFastFoods();
        model.addAttribute("pubObs", pubObs);
        model.addAttribute("fastFoodObs", fastFoodObs);

        return "index";
    }

    @GetMapping("/search")
    public String searchBar(@RequestParam String search, Model model) {

        model.addAttribute("searched", search);
        List<String> pubs = new ArrayList<>();
        List<String> fastFoods = new ArrayList<>();

        List<Pub> pubObs = new ArrayList<>();
        List<FastFood> fastFoodObs = new ArrayList<>();

        if (!searchService.findAllPubNamesFromPlaceName(search).isEmpty()) {
            pubs = searchService.findAllPubNamesFromPlaceName(search);
            pubObs = navigationService.findAllPubsByName(search);
        }

        if (!searchService.findAllFastFoodNamesFromPlaceName(search).isEmpty()) {
            fastFoods = searchService.findAllFastFoodNamesFromPlaceName(search);
            fastFoodObs = navigationService.findAllFastFoodsByName(search);
        }

        model.addAttribute("pubs", pubs);
        model.addAttribute("fastFoods", fastFoods);

        model.addAttribute("pubObs", pubObs);
        model.addAttribute("fastFoodObs", fastFoodObs);

        return "index";
    }
}
