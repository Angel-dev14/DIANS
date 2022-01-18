package com.dians.navigation.web.controller;

import com.dians.navigation.model.FastFood;
import com.dians.navigation.model.Pub;
import com.dians.navigation.service.AdminService;
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
    private final AdminService adminService;

    public AdminController(CsvReaderService csvReaderService, NavigationService navigationService,
                           AdminService adminService) {
        this.csvReaderService = csvReaderService;
        this.navigationService = navigationService;
        this.adminService = adminService;
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

        int pageSize = 3; //default is 3

        Page<FastFood> fastFoods = this.adminService.findFastFoodPaginated(fPageNo, pageSize);
        Page<Pub> pubs = this.adminService.findPubsPaginated(pPageNo, pageSize);

        model.addAttribute("fCurrentPage", fPageNo);
        model.addAttribute("pCurrentPage", pPageNo);

        model.addAttribute("fTotalPages", fastFoods.getTotalPages());
        model.addAttribute("pTotalPages", pubs.getTotalPages());

        model.addAttribute("fTotalItems", fastFoods.getTotalElements());
        model.addAttribute("pTotalItems", pubs.getTotalElements());

        model.addAttribute("fastFoods", fastFoods);
        model.addAttribute("pubs", pubs);
        return "admin";
    }

    @GetMapping("/delete-fast-food/{id}")
    public String deleteFastFood(@PathVariable Long id) {
        this.adminService.deleteFastFoodById(id);
        return "redirect:/admin";
    }

    @GetMapping("/delete-pub/{id}")
    public String deletePub(@PathVariable Long id) {
        this.adminService.deletePubById(id);
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
        FastFood fastFood = null;
        if (navigationService.findFastFoodById(id).isPresent()) {
            fastFood = navigationService.findFastFoodById(id).get();
        }
        model.addAttribute("place", fastFood);
        return "adminDetail";
    }

    @GetMapping("/edit-pub/{id}")
    public String editPub(@PathVariable Long id, Model model) {
        model.addAttribute("type", "pub");
        Pub pub = null;
        if (navigationService.findPubById(id).isPresent()) {
            pub = navigationService.findPubById(id).get();
        }
        model.addAttribute("place", pub);
        return "adminDetail";
    }

    @PostMapping("/add/fastfood")
    public String saveFastFood(
        @RequestParam(required = false) Long placeId,
        @RequestParam String name,
        @RequestParam Double lat,
        @RequestParam Double lon) {
        this.adminService.saveFastFood(placeId, name, lat, lon);
        return "redirect:/admin";
    }

    @PostMapping("/add/pub")
    public String savePub(
        @RequestParam(required = false) Long placeId,
        @RequestParam String name,
        @RequestParam Double lat,
        @RequestParam Double lon) {
        this.adminService.savePub(placeId, name, lat, lon);
        return "redirect:/admin";
    }

    @GetMapping("/upload-file")
    public String getFileUploader() {
        return "fileUploaderPage";
    }

    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            csvReaderService.readFile(file);
            model.addAttribute("message", "success");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            model.addAttribute("error", "file uploading has failed");
        }
        return "fileUploaderPage";
    }
}
