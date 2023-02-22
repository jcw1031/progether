package com.woopaca.progether.service;

import com.woopaca.progether.controller.dto.SignInRequestDto;

public interface UserService {

    void signUp();

    String signIn(final SignInRequestDto signInRequestDto);
}
