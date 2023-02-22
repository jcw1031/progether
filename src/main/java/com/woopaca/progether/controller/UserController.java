package com.woopaca.progether.controller;

import com.woopaca.progether.controller.dto.SignInRequestDto;
import com.woopaca.progether.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/sign-in")
    public String signInForm() {
        return "sign/signIn";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute final SignInRequestDto signInRequestDto,
                         HttpServletResponse response) {
        String token = userService.signIn(signInRequestDto);
        response.setHeader("Authorization", token);
        Cookie cookie = new Cookie("access_token", token);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profileMain() {
        return "profile/profile";
    }
}
