package com.dians.navigation.controller;


import com.dians.navigation.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("")
    public String getIndexPage(Model model) {
        List<String> pubs = searchService.findAllPubNames();
        List<String> fastFoods = searchService.findAllFastFoodNames();
        model.addAttribute("pubs", pubs);
        model.addAttribute("fastFoods", fastFoods);
        return "index";
    }

    @GetMapping("/search")
    public String searchBar(@RequestParam(required = true) String search, Model model) {

        model.addAttribute("searched", search);
        List<String> pubs = new ArrayList<>();
        List<String> fastFoods = new ArrayList<>();

        if (!searchService.findAllPubNamesFromPlaceName(search).isEmpty()) {
            pubs = searchService.findAllPubNamesFromPlaceName(search);
        }

        if (!searchService.findAllFastFoodNamesFromPlaceName(search).isEmpty()) {
            fastFoods = searchService.findAllFastFoodNamesFromPlaceName(search);
        }

        model.addAttribute("pubs", pubs);
        model.addAttribute("fastFoods", fastFoods);

        return "index";
    }

    @GetMapping("/fastFood")
    public String getOnlyFastFood(Model model) {

        model.addAttribute("fastFoods", searchService.findAllFastFoodNames());

        return "index";
    }

    @GetMapping("/pubs")
    public String getOnlyPubs(Model model) {

        model.addAttribute("pubs", searchService.findAllPubNames());

        return "index";
    }
}
