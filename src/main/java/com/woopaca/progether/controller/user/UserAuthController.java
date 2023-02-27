package com.woopaca.progether.controller.user;

import com.woopaca.progether.config.jwt.JwtAuthenticationValidator;
import com.woopaca.progether.controller.user.dto.SignInRequestDto;
import com.woopaca.progether.controller.user.dto.SignUpRequestDto;
import com.woopaca.progether.exception.user.UserException;
import com.woopaca.progether.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
    private final JwtAuthenticationValidator jwtAuthenticationValidator;

    @GetMapping("/sign-up")
    public String signUpForm(
            @CookieValue(name = "access_token", required = false) final String token,
            final Model model) {
        validateToken(token, model);
        return "auth/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(
            @ModelAttribute final SignUpRequestDto signUpRequestDto,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            userService.signUp(signUpRequestDto);
        } catch (UserException e) {
            redirectAttributes.addFlashAttribute("signUpSuccess", false)
                    .addFlashAttribute("errorMessage", e.getUserError().getMessage());
            return "redirect:/users/sign-up";
        }
        redirectAttributes.addFlashAttribute("signUpSuccess", true);
        return "redirect:/users/sign-in";
    }

    @GetMapping("/sign-in")
    public String signInForm(
            @CookieValue(name = "access_token", required = false) final String token,
            final Model model
    ) {
        validateToken(token, model);
        return "auth/sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(
            @ModelAttribute final SignInRequestDto signInRequestDto,
            final HttpServletResponse response, final RedirectAttributes redirectAttributes
    ) {
        String token;
        try {
            token = userService.signIn(signInRequestDto);
        } catch (UserException e) {
            redirectAttributes.addFlashAttribute("signInSuccess", false)
                    .addFlashAttribute("errorMessage", e.getUserError().getMessage());
            return "redirect:/users/sign-in";
        }
        redirectAttributes.addFlashAttribute("signInStatus", true);
        response.setHeader("Authorization", token);
        Cookie cookie = new Cookie("access_token", token);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 3);
        response.addCookie(cookie);
        return "redirect:/";
    }

    private void validateToken(final String token, final Model model) {
        boolean signInStatus = jwtAuthenticationValidator.validateToken(token);
        model.addAttribute("signInStatus", signInStatus);
    }
}
