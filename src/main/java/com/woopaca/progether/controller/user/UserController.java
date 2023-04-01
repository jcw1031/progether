package com.woopaca.progether.controller.user;

import com.woopaca.progether.config.jwt.JwtAuthenticationValidator;
import com.woopaca.progether.config.jwt.JwtUtils;
import com.woopaca.progether.controller.user.dto.ProfileUpdateRequestDto;
import com.woopaca.progether.controller.user.dto.UserProfileResponseDto;
import com.woopaca.progether.entity.Part;
import com.woopaca.progether.entity.User;
import com.woopaca.progether.exception.user.UserException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        UserProfileResponseDto userProfileResponseDto;
        try {
            validateToken(token, model);
            userProfileResponseDto = userService.userInfo(token);
        } catch (JwtException | UserException e) {
            return "redirect:/users/sign-out";
        }
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
            return "redirect:/users/sign-out";
        }

        if (model.getAttribute("profileUpdateRequestDto") == null) {
            model.addAttribute("profileUpdateRequestDto", new ProfileUpdateRequestDto());
        }

        User user = jwtUtils.getUserOfToken(token);
        model.addAttribute("user", user);
        model.addAttribute("parts", List.of(Part.values()));
        return "user/update";
    }

    @PostMapping("/update")
    public String userUpdate(
            @ModelAttribute ProfileUpdateRequestDto profileUpdateRequestDto,
            @CookieValue(name = "access_token", required = false) final String token, final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            validateToken(token, model);
        } catch (JwtException e) {
            return "redirect:/users/sign-out";
        }
        try {
            userService.userUpdate(profileUpdateRequestDto, token);
        } catch (UserException e) {
            redirectAttributes.addFlashAttribute("profileUpdateSuccess", false)
                    .addFlashAttribute("errorMessage", e.getUserError().getMessage())
                    .addFlashAttribute("profileUpdateRequestDto", profileUpdateRequestDto);
            return "redirect:/users/update";
        }
        return "redirect:/users/profile";
    }

    private void validateToken(final String token, final Model model) {
        boolean signInStatus = jwtAuthenticationValidator.validateToken(token);
        model.addAttribute("signInStatus", signInStatus);
    }
}
