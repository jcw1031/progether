package com.woopaca.progether.service;

import com.woopaca.progether.controller.user.dto.ProfileUpdateRequestDto;
import com.woopaca.progether.controller.user.dto.SignInRequestDto;
import com.woopaca.progether.controller.user.dto.SignUpRequestDto;
import com.woopaca.progether.controller.user.dto.UserProfileResponseDto;

public interface UserService {

    void signUp(final SignUpRequestDto signUpRequestDto);

    String signIn(final SignInRequestDto signInRequestDto);

    UserProfileResponseDto userInfo(final String token);

    void userUpdate(final ProfileUpdateRequestDto profileUpdateRequestDto, final String token);
}
