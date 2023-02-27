package com.woopaca.progether.controller.user;

import com.woopaca.progether.config.jwt.JwtAuthenticationValidator;
import com.woopaca.progether.config.jwt.JwtUtils;
import com.woopaca.progether.controller.user.dto.ProfileUpdateRequestDto;
import com.woopaca.progether.controller.user.dto.UserProfileResponseDto;
import com.woopaca.progether.entity.User;
import com.woopaca.progether.service.UserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtAuthenticationValidator jwtAuthenticationValidator;
    private final JwtUtils jwtUtils;

    @GetMapping("/profile")
    public String userProfile(
            @CookieValue(name = "access_token", required = false) final String token, final Model model
    ) {
        try {
            validateToken(token, model);
        } catch (JwtException e) {
            return "redirect:/";
        }
        UserProfileResponseDto userProfileResponseDto = userService.userInfo(token);
        model.addAttribute("user", userProfileResponseDto);
        return "user/profile";
    }

    @GetMapping("/update")
    public String userUpdateForm(
            @CookieValue(name = "access_token", required = false) final String token, final Model model
    ) {
        try {
            validateToken(token, model);
        } catch (JwtException e) {
            return "redirect:/";
        }
        User user = jwtUtils.getUserOfToken(token);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/update")
    public String userUpdate(
            @ModelAttribute ProfileUpdateRequestDto profileUpdateRequestDto,
            @CookieValue(name = "access_token", required = false) final String token, final Model model
    ) {
        try {
            validateToken(token, model);
        } catch (JwtException e) {
            return "redirect:/";
        }
        userService.userUpdate(profileUpdateRequestDto, token);
        return "redirect:/users/profile";
    }

    private void validateToken(final String token, final Model model) {
        boolean signInStatus = jwtAuthenticationValidator.validateToken(token);
        model.addAttribute("signInStatus", signInStatus);
    }
}
