package com.woopaca.progether.controller;

import com.woopaca.progether.config.jwt.JwtAuthenticationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final JwtAuthenticationValidator jwtAuthenticationValidator;

    @GetMapping("")
    public String home(
            @CookieValue(name = "access_token", required = false) final String token,
            final Model model
    ) {
        log.info("access_token = {}", token);
        boolean signInStatus = jwtAuthenticationValidator.validateToken(token);
        model.addAttribute("signInStatus", signInStatus);
        log.info("signInStatus = {}", signInStatus);
        return "index";
    }
}
