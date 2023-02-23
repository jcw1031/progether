package com.woopaca.progether.service.impl;

import com.woopaca.progether.config.jwt.JwtProvider;
import com.woopaca.progether.config.jwt.JwtUtils;
import com.woopaca.progether.controller.user.dto.SignInRequestDto;
import com.woopaca.progether.controller.user.dto.SignUpRequestDto;
import com.woopaca.progether.controller.user.dto.UserProfileResponseDto;
import com.woopaca.progether.entity.User;
import com.woopaca.progether.exception.user.impl.EmailDuplicateException;
import com.woopaca.progether.exception.user.impl.InvalidSignInUserException;
import com.woopaca.progether.exception.user.impl.UserNotFoundException;
import com.woopaca.progether.repository.UserRepository;
import com.woopaca.progether.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicUserService implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final JwtUtils jwtUtils;

    @Override
    public void signUp(final SignUpRequestDto signUpRequestDto) {
        User user = validateDuplicateEmail(signUpRequestDto);
        userRepository.save(user);
    }

    private User validateDuplicateEmail(final SignUpRequestDto signUpRequestDto) {
        userRepository.findByEmail(signUpRequestDto.getEmail())
                .ifPresent(user -> {
                    throw new EmailDuplicateException();
                });
        return User.from(signUpRequestDto);
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

    @Override
    public UserProfileResponseDto userInfo(final String token) {
        String userEmail = jwtUtils.getEmailInToken(token);
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException());
        return user.toProfileDto();
    }
}
