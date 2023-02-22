package com.woopaca.progether.controller;

import com.woopaca.progether.controller.dto.SignInRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    @GetMapping("/sign-in")
    public String signInForm() {
        return "sign/signIn";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute SignInRequestDto signInRequestDto) {
        log.info("signInRequestDto = {}", signInRequestDto);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profileMain() {
        return "profile/profile";
    }
}
