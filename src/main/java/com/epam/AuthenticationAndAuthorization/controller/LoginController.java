package com.epam.AuthenticationAndAuthorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(final ModelMap map, @RequestParam("error") final Optional<String> error) {
        error.ifPresent(err -> map.addAttribute("error", err));
        return "login";
    }

}
