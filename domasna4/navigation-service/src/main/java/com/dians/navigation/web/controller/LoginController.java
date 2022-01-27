package com.dians.navigation.web.controller;

import com.dians.navigation.model.AdminUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    public LoginController() {
    }

    @GetMapping
    public String loginPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("hasError", true);
        model.addAttribute("error", error);
        return "login";
    }

}
