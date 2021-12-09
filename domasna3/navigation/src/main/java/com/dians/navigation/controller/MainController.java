package com.dians.navigation.controller;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.service.FastFoodService;
import com.dians.navigation.service.PubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final FastFoodService fastFoodService;
    private final PubService pubService;

    public MainController(FastFoodService fastFoodService, PubService pubService) {
        this.fastFoodService = fastFoodService;
        this.pubService = pubService;
    }

    @GetMapping("")
    public String getIndexPage(Model model){
        List<Pub> pubs = pubService.findAllPubs();
        List<FastFood> fastFoods = fastFoodService.findAllFastFoods();
        model.addAttribute("pubs", pubs);
        model.addAttribute("fastFoods", fastFoods);
        return "index";
    }

    @PostMapping("/search")
    public String searchBar(@RequestParam(required = true) String filter, Model model){
        List<Pub> pubs = new ArrayList<>();
        List<FastFood> fastFoods = new ArrayList<>();

        if(!pubService.findAllPubsByName(filter).isEmpty()){
            pubs = pubService.findAllPubsByName(filter);
        }

        if(!fastFoodService.findAllFastFoodsByName(filter).isEmpty()){
            fastFoods = fastFoodService.findAllFastFoodsByName(filter);
        }

        model.addAttribute("pubs", pubs);
        model.addAttribute("fastFoods", fastFoods);

        return "index";
    }
}
