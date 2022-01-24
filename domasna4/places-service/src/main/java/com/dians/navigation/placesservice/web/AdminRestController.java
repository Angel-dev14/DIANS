package com.dians.navigation.placesservice.web;

import com.dians.navigation.placesservice.model.FastFood;
import com.dians.navigation.placesservice.model.Pub;
import com.dians.navigation.placesservice.service.AdminService;
import com.dians.navigation.placesservice.service.impl.CsvReaderService;
import java.util.List;
import java.util.stream.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/private/admin")
public class AdminRestController {

    private final AdminService adminService;
    private final CsvReaderService csvReaderService;

    private static final int pageSize = 3;

    public AdminRestController(AdminService adminService,
                               CsvReaderService csvReaderService) {
        this.adminService = adminService;
        this.csvReaderService = csvReaderService;
    }

    @GetMapping("/paginated/fastFoods")
    public ResponseEntity<List<FastFood>> getFastFoodsByPage(@RequestParam Integer pageNumber) {
        Page<FastFood> fastFoodsPages = adminService.findFastFoodPaginated(pageNumber, pageSize);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("totalPages", String.valueOf(fastFoodsPages.getTotalPages()));
        responseHeaders.add("totalItems", String.valueOf(fastFoodsPages.getTotalElements()));
        return ResponseEntity.ok()
            .headers(responseHeaders)
            .body(fastFoodsPages.get().collect(Collectors.toList()));
    }

    @GetMapping("/paginated/pubs")
    public ResponseEntity<List<Pub>> getPubsByPage(@RequestParam Integer pageNumber) {
        Page<Pub> pubsPages = adminService.findPubsPaginated(pageNumber, pageSize);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("totalPages", String.valueOf(pubsPages.getTotalPages()));
        responseHeaders.add("totalItems", String.valueOf(pubsPages.getTotalElements()));
        return ResponseEntity.ok()
            .headers(responseHeaders)
            .body(pubsPages.get().collect(Collectors.toList()));
    }

    @PostMapping("/add/fastFood")
    public void saveFastFood(
        @RequestParam(required = false) Long placeId,
        @RequestParam String name,
        @RequestParam Double lat,
        @RequestParam Double lon) {
        this.adminService.saveFastFood(placeId, name, lat, lon);
    }

    @PostMapping("/add/pub")
    public void savePub(
        @RequestParam(required = false) Long placeId,
        @RequestParam String name,
        @RequestParam Double lat,
        @RequestParam Double lon) {
        this.adminService.savePub(placeId, name, lat, lon);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            csvReaderService.readFile(file);
            return ResponseEntity.ok("Success");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.ok("File uploading has failed");
        }
    }
}
