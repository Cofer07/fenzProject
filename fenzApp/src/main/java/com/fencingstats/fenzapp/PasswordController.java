package com.fencingstats.fenzapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {
    @GetMapping("/forgot-password")
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }


    @PostMapping("/forgot-password")
    public String processForgotPasswordForm(@RequestParam("email") String userEmail) {

        return "redirect:/resetSuccess";
    }
}
