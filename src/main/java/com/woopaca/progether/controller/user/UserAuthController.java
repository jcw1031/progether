package com.woopaca.progether.controller.user;

import com.woopaca.progether.config.jwt.JwtAuthenticationValidator;
import com.woopaca.progether.controller.user.dto.SignInRequestDto;
import com.woopaca.progether.controller.user.dto.SignUpRequestDto;
import com.woopaca.progether.exception.user.UserError;
import com.woopaca.progether.exception.user.UserException;
import com.woopaca.progether.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
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
    private final String PATH = "localhost";

    @GetMapping("/sign-up")
    public String signUpForm(
            @CookieValue(name = "access_token", required = false) final String token,
            final Model model) {
        model.addAttribute("signUpRequestDto", new SignUpRequestDto());
        validateToken(token, model);
        return "auth/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(
            @ModelAttribute final SignUpRequestDto signUpRequestDto, final BindingResult bindingResult,
            final Model model, final RedirectAttributes redirectAttributes
    ) {

        try {
            userService.signUp(signUpRequestDto);
        } catch (UserException e) {
            UserError userError = e.getUserError();
            bindingResult.addError(new FieldError(
                    "signUpRequestDto", userError.getField(),
                    getSignUpRejectedValue(userError.getField(), signUpRequestDto),
                    false, null, null, userError.getMessage()
            ));
            model.addAttribute("signInStatus", false);
            return "auth/sign-up";
        }
        redirectAttributes.addFlashAttribute("signUpSuccess", true);
        return "redirect:/users/sign-in";
    }

    private String getSignUpRejectedValue(final String field, final SignUpRequestDto signUpRequestDto) {
        if (field.equals("email")) {
            return signUpRequestDto.getEmail();
        }
        return null;
    }

    @GetMapping("/sign-in")
    public String signInForm(
            @CookieValue(name = "access_token", required = false) final String token,
            final Model model
    ) {
        validateToken(token, model);
        model.addAttribute("signInRequestDto", new SignInRequestDto());
        return "auth/sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(
            @ModelAttribute final SignInRequestDto signInRequestDto, final BindingResult bindingResult,
            final HttpServletResponse response, final Model model, final RedirectAttributes redirectAttributes
    ) {
        String token;
        try {
            token = userService.signIn(signInRequestDto);
        } catch (UserException e) {
            bindingResult.addError(new ObjectError(
                    "signInRequestDto", e.getUserError().getMessage()));
            model.addAttribute("signInStatus", false);
            return "auth/sign-in";
        }

        redirectAttributes.addFlashAttribute("signInStatus", true);
        response.setHeader("Authorization", token);
        Cookie cookie = new Cookie("access_token", token);
        cookie.setDomain(PATH);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 3);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/sign-out")
    public String signOut(final HttpServletResponse response) {
        Cookie cookie = new Cookie("access_token", null);
        cookie.setDomain(PATH);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    private void validateToken(final String token, final Model model) {
        boolean signInStatus = jwtAuthenticationValidator.validateToken(token);
        model.addAttribute("signInStatus", signInStatus);
    }
}
