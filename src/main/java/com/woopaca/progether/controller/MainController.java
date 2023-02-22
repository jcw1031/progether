package com.woopaca.progether.controller;

import com.woopaca.progether.config.jwt.JwtAuthenticationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final JwtAuthenticationValidator jwtAuthenticationValidator;

    @GetMapping("")
    public String home(@CookieValue(name = "access_token") final String token, final Model model) {
        boolean signInStatus = jwtAuthenticationValidator.validateToken(token);
        model.addAttribute("signInStatus", signInStatus);
        return "index";
    }
}
