package com.woopaca.progether.controller;

import com.woopaca.progether.controller.dto.SignInRequestDto;
import com.woopaca.progether.exception.user.UserException;
import com.woopaca.progether.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserAuthController {

    private final UserService userService;

    @GetMapping("/sign-up")
    public String signUpForm() {
        return "auth/sign-up";
    }

    @GetMapping("/sign-in")
    public String signInForm(Model model) {
        return "auth/sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute final SignInRequestDto signInRequestDto,
                         final HttpServletResponse response, final RedirectAttributes redirectAttributes) {
        String token;
        try {
            token = userService.signIn(signInRequestDto);
        } catch (UserException e) {
            redirectAttributes.addFlashAttribute("signInStatus", false);
            return "redirect:/users/sign-in";
        }
        redirectAttributes.addFlashAttribute("signInStatus", true);
        response.setHeader("Authorization", token);
        Cookie cookie = new Cookie("access_token", token);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
