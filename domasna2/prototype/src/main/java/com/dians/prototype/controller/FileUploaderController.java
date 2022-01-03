package com.dians.prototype.controller;

import com.dians.prototype.service.CsvReaderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload-file")
public class FileUploaderController {
    private final CsvReaderService service;

    public FileUploaderController(CsvReaderService service) {
        this.service = service;
    }

    @GetMapping()
    public String getPage(){
        return "fileUploaderPage";
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            service.readFile(file);
            model.addAttribute("message", "success");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            model.addAttribute("error", "file uploading has failed");
        }
        return "fileUploaderPage";
    }
}
