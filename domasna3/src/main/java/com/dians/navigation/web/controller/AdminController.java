package com.dians.navigation.web.controller;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.service.CsvReaderService;
import com.dians.navigation.service.NavigationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
    public String adminPage(Model model, HttpServletRequest req){
        Page<FastFood> fastFoods = this.navigationService.findAllFastFoodsInPage(0);
        model.addAttribute("fpage1", 0);
        model.addAttribute("fpage2", 1);
        model.addAttribute("fpage3", 2);

        Page<Pub> pubs = this.navigationService.findAllPubsInPage(0);
        model.addAttribute("ppage1", 0);
        model.addAttribute("ppage2", 1);
        model.addAttribute("ppage3", 2);

        req.getSession().setAttribute("ffPage", 0);
        req.getSession().setAttribute("pubPage", 0);

        model.addAttribute("fastFoods", fastFoods);
        model.addAttribute("pubs", pubs);

        return "admin";
    }

    //mapping with paging
    @GetMapping("/paging")
    public String paging(@RequestParam Integer page,
                         @RequestParam String type,
                                 Model model,
                                 HttpServletRequest req){

        Integer ffPageNr = null;
        Integer pubPageNr = null;

        if(type.equals("fastfood")) {
            ffPageNr = page;
            pubPageNr = (Integer) req.getSession().getAttribute("pubPage");
        } else {
            ffPageNr = (Integer) req.getSession().getAttribute("ffPage");
            pubPageNr = page;
        }

        Page<FastFood> fastFoods = this.navigationService.findAllFastFoodsInPage(ffPageNr);
        Page<Pub> pubs = this.navigationService.findAllPubsInPage(pubPageNr);

        //Arrange 2 closest pages to the current page at Fast Foods
        Integer totalPages = fastFoods.getTotalPages();

        if(ffPageNr >= totalPages - 1) {
            ffPageNr = totalPages - 1;
            model.addAttribute("fpage1", totalPages - 3);
            model.addAttribute("fpage2", totalPages - 2);
            model.addAttribute("fpage3", totalPages - 1);
        } else if(ffPageNr <= 0) {
            ffPageNr = 0;
            model.addAttribute("fpage1", 0);
            model.addAttribute("fpage2", 1);
            model.addAttribute("fpage3", 2);
        } else {
            model.addAttribute("fpage1", ffPageNr - 1);
            model.addAttribute("fpage2", ffPageNr);
            model.addAttribute("fpage3", ffPageNr + 1);
        }
        req.getSession().setAttribute("ffPage", ffPageNr);

        //paging in pubs
        totalPages = pubs.getTotalPages();

        if(pubPageNr >= totalPages - 1) {
            pubPageNr = totalPages - 1;
            model.addAttribute("ppage1", totalPages - 3);
            model.addAttribute("ppage2", totalPages - 2);
            model.addAttribute("ppage3", totalPages - 1);
        } else if(pubPageNr <= 0) {
            pubPageNr = 0;
            model.addAttribute("ppage1", 0);
            model.addAttribute("ppage2", 1);
            model.addAttribute("ppage3", 2);
        } else {
            model.addAttribute("ppage1", pubPageNr - 1);
            model.addAttribute("ppage2", pubPageNr);
            model.addAttribute("ppage3", pubPageNr + 1);
        }
        req.getSession().setAttribute("pubPage", pubPageNr);


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
