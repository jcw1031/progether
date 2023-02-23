package com.woopaca.progether.controller.user;

import com.woopaca.progether.config.jwt.JwtAuthenticationValidator;
import com.woopaca.progether.config.jwt.JwtUtils;
import com.woopaca.progether.controller.user.dto.UserProfileResponseDto;
import com.woopaca.progether.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtAuthenticationValidator jwtAuthenticationValidator;
    private final JwtUtils jwtUtils;

    @GetMapping("/profile")
    public String userProfile(@CookieValue(name = "access_token", required = false) final String token,
                              final Model model) {
        validateToken(token, model);
        UserProfileResponseDto userProfileResponseDto = userService.userInfo(token);
        model.addAttribute("user", userProfileResponseDto);
        return "user/profile";
    }

    private void validateToken(final String token, final Model model) {
        boolean signInStatus = jwtAuthenticationValidator.validateToken(token);
        model.addAttribute("signInStatus", signInStatus);
    }
}
