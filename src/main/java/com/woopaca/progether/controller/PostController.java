package com.woopaca.progether.controller;

import com.woopaca.progether.config.jwt.JwtAuthenticationValidator;
import com.woopaca.progether.controller.dto.PostListResponseDto;
import com.woopaca.progether.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final JwtAuthenticationValidator jwtAuthenticationValidator;
    private final PostService postService;

    @GetMapping("")
    public String board(
            @CookieValue(name = "access_token", required = false) final String token,
            Model model
    ) {
        boolean signInStatus = jwtAuthenticationValidator.validateToken(token);
        model.addAttribute("signInStatus", signInStatus);

        List<PostListResponseDto> postList = postService.postList();
        model.addAttribute("postList", postList);
        return "board/board";
    }
}
