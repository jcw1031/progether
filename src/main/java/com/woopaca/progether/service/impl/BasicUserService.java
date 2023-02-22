package com.woopaca.progether.service.impl;

import com.woopaca.progether.config.jwt.JwtProvider;
import com.woopaca.progether.controller.dto.SignInRequestDto;
import com.woopaca.progether.entity.User;
import com.woopaca.progether.exception.user.InvalidSignInUserException;
import com.woopaca.progether.repository.UserRepository;
import com.woopaca.progether.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicUserService implements UserService {

    private final UserRepository userRepository;
    private final BCrypt bCrypt;
    private final JwtProvider jwtProvider;

    @Override
    public void signUp() {

    }

    @Override
    public String signIn(final SignInRequestDto signInRequestDto) {
        User user = validateSignInUser(signInRequestDto);
        return jwtProvider.createToken(user, 2);
    }

    private User validateSignInUser(final SignInRequestDto signInRequestDto) {
        User user = userRepository.findByEmail(signInRequestDto.getEmail())
                .orElseThrow(() -> new InvalidSignInUserException());

        if (!BCrypt.checkpw(signInRequestDto.getPassword(), user.getPassword())) {
            throw new InvalidSignInUserException();
        }
        return user;
    }

}
