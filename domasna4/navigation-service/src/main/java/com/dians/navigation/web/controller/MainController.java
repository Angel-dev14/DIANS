package com.dians.navigation.web.controller;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.web.helper.RequestHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    public MainController() {
    }

    @GetMapping
    public String getIndexPage(Model model) {
        List<FastFood> fastFoods;
        List<Pub> pubs;

        fastFoods = Arrays.asList(
            Objects.requireNonNull(RequestHelper.sendGetRequestForFastFoods(
                RequestHelper.createRequestUrl("/find/fastFoods", Collections.emptyMap())).getBody()));

        pubs = Arrays.asList(
            Objects.requireNonNull(RequestHelper.sendGetRequestForPubs(
                RequestHelper.createRequestUrl("/find/pubs", Collections.emptyMap())).getBody()));

        model.addAttribute("fastFoodObs", fastFoods);
        model.addAttribute("pubObs", pubs);

        return "index";
    }

    @GetMapping("/search")
    public String searchBar(@RequestParam String search, Model model) {

        model.addAttribute("searched", search);
        List<Pub> pubs;
        List<FastFood> fastFoods;
        Map<String, String> queries = new HashMap<>();

        queries.put("searchTerm", search);
        pubs = Arrays.asList(Objects.requireNonNull(RequestHelper.sendGetRequestForPubs(
            RequestHelper.createRequestUrl("/search/pubs", queries)
        ).getBody()));

        fastFoods = Arrays.asList(Objects.requireNonNull(RequestHelper.sendGetRequestForFastFoods(
            RequestHelper.createRequestUrl("/search/fastFoods", queries)
        ).getBody()));

        model.addAttribute("fastFoodObs", fastFoods);
        model.addAttribute("pubObs", pubs);

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
