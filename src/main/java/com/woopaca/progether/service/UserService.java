package com.woopaca.progether.service;

import com.woopaca.progether.controller.dto.SignInRequestDto;
import com.woopaca.progether.controller.dto.SignUpRequestDto;

public interface UserService {

    void signUp(final SignUpRequestDto signUpRequestDto);

    String signIn(final SignInRequestDto signInRequestDto);
}
