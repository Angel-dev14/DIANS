package com.dians.navigation.web.controller;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.service.CsvReaderService;
import com.dians.navigation.service.NavigationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CsvReaderService csvReaderService;
    private final NavigationService navigationService;

    public AdminController(CsvReaderService csvReaderService, NavigationService navigationService) {
        this.csvReaderService = csvReaderService;
        this.navigationService = navigationService;
    }

    //main page
    @GetMapping
    public String adminPage(Model model){

        List<FastFood> fastFoods = this.navigationService.findAllFastFoods();
        List<Pub> pubs = this.navigationService.findAllPubs();

        model.addAttribute("fastFoods", fastFoods);
        model.addAttribute("pubs", pubs);

        return "admin";
    }

    //mapping for deleting places
    @GetMapping("/delete-fast-food/{id}")
    public String deleteFastFood(@PathVariable Long id){
        this.navigationService.deleteFastFoodById(id);
        return "redirect:/admin";
    }

    @GetMapping("/delete-pub/{id}")
    public String deletePub(@PathVariable Long id){
        this.navigationService.deletePubById(id);
        return "redirect:/admin";
    }

    //mapping for adding places
    @GetMapping("/add-fast-food")
    public String addFastFood(Model model){
        model.addAttribute("type", "fastfood");
        return "adminDetail";
    }

    @GetMapping("/add-pub")
    public String addPub(Model model){
        model.addAttribute("type", "pub");
        return "adminDetail";
    }

    //mapping for editing places
    @GetMapping("/edit-fast-food/{id}")
    public String editFastFood(@PathVariable Long id, Model model){
        model.addAttribute("type", "fastfood");
        FastFood fastFood = null;
        if(navigationService.findFastFoodById(id).isPresent())
            fastFood = navigationService.findFastFoodById(id).get();
        model.addAttribute("place", fastFood);
        return "adminDetail";
    }

    @GetMapping("/edit-pub/{id}")
    public String editPub(@PathVariable Long id, Model model){
        model.addAttribute("type", "pub");
        Pub pub = null;
        if(navigationService.findPubById(id).isPresent())
            pub = navigationService.findPubById(id).get();
        model.addAttribute("place", pub);
        return "adminDetail";
    }

    //mapping for saving places
    @PostMapping("/add/fastfood")
    public String saveFastFood(@RequestParam String name,
                            @RequestParam Double lat,
                            @RequestParam Double lon){
        this.navigationService.saveFastFood(name, lat, lon);
        return "redirect:/admin";
    }

    @PostMapping("/add/pub")
    public String savePub(@RequestParam String name,
                            @RequestParam Double lat,
                            @RequestParam Double lon){
        this.navigationService.savePub(name, lat, lon);
        return "redirect:/admin";
    }

    @GetMapping("/upload-file")
    public String getFileUploader(){
        return "fileUploaderPage";
    }

    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            csvReaderService.readFile(file);
            model.addAttribute("message", "success");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            model.addAttribute("error", "file uploading has failed");
        }
        return "fileUploaderPage";
    }

}
