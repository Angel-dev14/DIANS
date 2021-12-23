package com.dians.navigation.web.controller;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.service.NavigationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final NavigationService navigationService;

    public MainController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @GetMapping("")
    public String getIndexPage(Model model) {

        List<Pub> pubObs = navigationService.findAllPubs();
        List<FastFood> fastFoodObs = navigationService.findAllFastFoods();
        model.addAttribute("pubObs", pubObs);
        model.addAttribute("fastFoodObs", fastFoodObs);

        return "index";
    }

    @GetMapping("/search")
    public String searchBar(@RequestParam String search, Model model) {

        model.addAttribute("searched", search);

        List<Pub> pubObs = new ArrayList<>();
        List<FastFood> fastFoodObs = new ArrayList<>();

        if (!navigationService.findAllPubsByName(search).isEmpty()) {
            pubObs = navigationService.findAllPubsByName(search);
        }

        if (!navigationService.findAllFastFoodsByName(search).isEmpty()) {
            fastFoodObs = navigationService.findAllFastFoodsByName(search);
        }

        model.addAttribute("pubObs", pubObs);
        model.addAttribute("fastFoodObs", fastFoodObs);

        return "index";
    }

    @GetMapping("/aboutUs")
    public String getAboutUsPage() {
        return "aboutUs";
    }

    @GetMapping("/project")
    public String getProjectPage() {
        return "project";
    }
}
