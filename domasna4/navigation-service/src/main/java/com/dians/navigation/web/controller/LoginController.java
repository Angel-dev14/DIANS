package com.dians.navigation.web.controller;

import com.dians.navigation.model.AdminUser;
import com.dians.navigation.service.AuthService;
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

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String loginPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("hasError", true);
        model.addAttribute("error", error);
        return "login";
    }

//    @PostMapping
//    public String login(@RequestParam String username,
//                        @RequestParam String password,
//                        Model model,
//                        HttpServletRequest req){
//
//        AdminUser user = null;
//        try{
//            user = this.authService.login(username, password);
//            req.getSession().setAttribute("adminUser", user);
//            return "redirect:/admin";
//        }catch (RuntimeException ex) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", ex.getMessage());
//            return "login";
//        }
//
//    }
}
