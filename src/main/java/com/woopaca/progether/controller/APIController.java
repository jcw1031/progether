package com.woopaca.progether.controller;

import com.woopaca.progether.KakaoAPI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class APIController {

    KakaoAPI kakaoApi = new KakaoAPI();

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("code") String code, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        //1. 인증 코드 요청 전달
        String accessToken = kakaoApi.getAccessToken(code);

        //2. 인증 코드로 토큰 전달
        Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);

        System.out.println("userInfo = " + userInfo);

        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("accessToken", accessToken);
        }
        modelAndView.addObject("userId", userInfo.get("email"));
        modelAndView.setViewName("sign/signIn");
        return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        kakaoApi.kakaoLogout((String)session.getAttribute("access_token"));
        session.removeAttribute("accessToken");
        session.removeAttribute("userId");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
