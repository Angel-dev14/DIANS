package com.dians.navigation.controller;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.service.FastFoodService;
import com.dians.navigation.service.PubService;
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

    public MainController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("")
    public String getIndexPage(Model model){
        List<String> pubs = searchService.findAllPubs();
        List<String> fastFoods = searchService.findAllFastFoods();
        model.addAttribute("pubs", pubs);
        model.addAttribute("fastFoods", fastFoods);
        return "index";
    }

    @PostMapping("/search")
    public String searchBar(@RequestParam(required = false) String filter, Model model){
        List<String> pubs = new ArrayList<>();
        List<String> fastFoods = new ArrayList<>();

        if(!searchService.findAllPubsByName(filter).isEmpty()){
            pubs = searchService.findAllPubsByName(filter);
        }

        if(!searchService.findAllFastFoodsByName(filter).isEmpty()){
            fastFoods = searchService.findAllFastFoodsByName(filter);
        }

        model.addAttribute("pubs", pubs);
        model.addAttribute("fastFoods", fastFoods);

        return "index";
    }

//    @GetMapping("/search/{filter}")
//    public String searchBar(@PathVariable(required = true) String filter, Model model){
//        List<String> pubs = new ArrayList<>();
//        List<String> fastFoods = new ArrayList<>();
//
//        if(!searchService.findAllPubsByName(filter).isEmpty() && searchService.findAllPubsByName(filter) !=null){
//            pubs = searchService.findAllPubsByName(filter);
//        }
//
//        if(!searchService.findAllFastFoodsByName(filter).isEmpty()){
//            fastFoods = searchService.findAllFastFoodsByName(filter);
//        }
//
//        model.addAttribute("pubs", pubs);
//        model.addAttribute("fastFoods", fastFoods);
//
//        return "index";
//    }
}
