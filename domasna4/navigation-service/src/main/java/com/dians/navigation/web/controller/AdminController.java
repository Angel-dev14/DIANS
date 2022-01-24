package com.dians.navigation.web.controller;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.web.helper.RequestHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RestTemplate restTemplate;

    private final String adminRequestUrl = "http://places-service/api/private/admin";

    public AdminController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //main page
    @GetMapping
    public String adminPage(Model model) {
        return paging(1, 1, model);
    }

    //mapping with paging
    @GetMapping("/page")
    public String paging(@RequestParam Integer fPageNo,
                         @RequestParam Integer pPageNo,
                         Model model) {
        List<Pub> pubs;
        List<FastFood> fastFoods;

        ResponseEntity<Pub[]> pubsResponse =
            restTemplate.getForEntity(String.format("%s/paginated/pubs?pageNumber=%s", adminRequestUrl, pPageNo),
                Pub[].class);
        Integer pubsTotalPages = Integer.valueOf(pubsResponse.getHeaders().get("totalPages").get(0));
        Integer pubsTotalItems = Integer.valueOf(pubsResponse.getHeaders().get("totalItems").get(0));
        pubs = Arrays.asList(Objects.requireNonNull(pubsResponse.getBody()));

        ResponseEntity<FastFood[]> fastFoodsResponse =
            restTemplate.getForEntity(
                String.format("%s/paginated/fastFoods?pageNumber=%s", adminRequestUrl, fPageNo),
                FastFood[].class);
        Integer fastFoodTotalPages = Integer.valueOf(fastFoodsResponse.getHeaders().get("totalPages").get(0));
        Integer fastFoodTotalItems = Integer.valueOf(fastFoodsResponse.getHeaders().get("totalItems").get(0));
        fastFoods = Arrays.asList(Objects.requireNonNull(fastFoodsResponse.getBody()));

        model.addAttribute("fCurrentPage", fPageNo);
        model.addAttribute("pCurrentPage", pPageNo);

        model.addAttribute("fTotalPages", fastFoodTotalPages);
        model.addAttribute("pTotalPages", pubsTotalPages);

        model.addAttribute("fTotalItems", fastFoodTotalItems);
        model.addAttribute("pTotalItems", pubsTotalItems);

        model.addAttribute("fastFoods", fastFoods);
        model.addAttribute("pubs", pubs);
        return "admin";
    }

    @GetMapping("/delete-fast-food/{id}")
    public String deleteFastFood(@PathVariable Long id) {
        RequestHelper.sendDeleteRequest(
            RequestHelper.createRequestUrl(String.format("/delete/fastFoods/%s", id), Collections.emptyMap())
        );
        return "redirect:/admin";
    }

    @GetMapping("/delete-pub/{id}")
    public String deletePub(@PathVariable Long id) {
        RequestHelper.sendDeleteRequest(
            RequestHelper.createRequestUrl(String.format("/delete/pubs/%s", id), Collections.emptyMap())
        );
        return "redirect:/admin";
    }

    @GetMapping("/add-fast-food")
    public String addFastFood(Model model) {
        model.addAttribute("type", "fastfood");
        return "adminDetail";
    }

    @GetMapping("/add-pub")
    public String addPub(Model model) {
        model.addAttribute("type", "pub");
        return "adminDetail";
    }

    @GetMapping("/edit-fast-food/{id}")
    public String editFastFood(@PathVariable Long id, Model model) {
        model.addAttribute("type", "fastfood");
        FastFood fastFood;
        fastFood = (FastFood) RequestHelper.sendPostRequestForPlace(
            RequestHelper.createRequestUrl(String.format("/find/fastFoods/%d", id), Collections.emptyMap()),
            FastFood.class
        ).getBody();
        model.addAttribute("place", fastFood);
        return "adminDetail";
    }

    @GetMapping("/edit-pub/{id}")
    public String editPub(@PathVariable Long id, Model model) {
        model.addAttribute("type", "pub");
        Pub pub;
        pub = (Pub) RequestHelper.sendPostRequestForPlace(
            RequestHelper.createRequestUrl(String.format("/find/pubs/%d", id), Collections.emptyMap()),
            Pub.class
        ).getBody();
        model.addAttribute("place", pub);
        return "adminDetail";
    }

    @PostMapping("/add/fastfood")
    public String saveFastFood(
        @RequestParam(required = false) Long placeId,
        @RequestParam String name,
        @RequestParam Double lat,
        @RequestParam Double lon) {
        HttpEntity<MultiValueMap<String, Object>> request = RequestHelper.buildPostRequestParams(
            "placeId", placeId,
            "name", name,
            "lat", lat,
            "lon", lon
        );
        restTemplate.postForEntity(String.format("%s/add/fastFood", adminRequestUrl), request, void.class);
        return "redirect:/admin";
    }

    @PostMapping("/add/pub")
    public String savePub(
        @RequestParam(required = false) Long placeId,
        @RequestParam String name,
        @RequestParam Double lat,
        @RequestParam Double lon) {
        HttpEntity<MultiValueMap<String, Object>> request = RequestHelper.buildPostRequestParams(
            "placeId", placeId,
            "name", name,
            "lat", lat,
            "lon", lon
        );
        restTemplate.postForEntity(String.format("%s/add/pub", adminRequestUrl), request, void.class);
        return "redirect:/admin";
    }

    @GetMapping("/upload-file")
    public String getFileUploader() {
        return "fileUploaderPage";
    }

    @SneakyThrows
    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        MultiValueMap<String, Object> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("file", file.getResource());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(requestParams, headers);
        ResponseEntity<String> response =
            restTemplate.postForEntity(String.format("%s/uploadFile", adminRequestUrl), request, String.class);
        model.addAttribute("message", response.getBody());
        return "fileUploaderPage";
    }
}
