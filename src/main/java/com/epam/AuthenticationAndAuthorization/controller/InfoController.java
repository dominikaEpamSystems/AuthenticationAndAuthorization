package com.epam.AuthenticationAndAuthorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @GetMapping("/info")
    public String getInfo() {
        return "info";
    }

    @GetMapping("/admin")
    public String getAdminInfo() {
        return "admin";
    }

    @GetMapping("/about")
    public String getInfoAbout() {
        return "about";
    }


}
