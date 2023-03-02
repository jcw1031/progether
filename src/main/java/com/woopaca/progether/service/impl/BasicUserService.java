package com.woopaca.progether.service.impl;

import com.woopaca.progether.config.jwt.JwtProvider;
import com.woopaca.progether.config.jwt.JwtUtils;
import com.woopaca.progether.controller.user.dto.ProfileUpdateRequestDto;
import com.woopaca.progether.controller.user.dto.SignInRequestDto;
import com.woopaca.progether.controller.user.dto.SignUpRequestDto;
import com.woopaca.progether.controller.user.dto.UserProfileResponseDto;
import com.woopaca.progether.entity.User;
import com.woopaca.progether.exception.user.impl.EmailDuplicateException;
import com.woopaca.progether.exception.user.impl.IncorrectCheckPassword;
import com.woopaca.progether.exception.user.impl.InvalidSignInUserException;
import com.woopaca.progether.exception.user.impl.UserNotFoundException;
import com.woopaca.progether.repository.UserRepository;
import com.woopaca.progether.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BasicUserService implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final JwtUtils jwtUtils;

    @Override
    @Transactional
    public void signUp(final SignUpRequestDto signUpRequestDto) {
        validateDuplicateEmail(signUpRequestDto);
        validateCheckPassword(signUpRequestDto.getPassword(), signUpRequestDto.getCheckPassword());

        User user = User.from(signUpRequestDto);
        userRepository.save(user);
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

    @Override
    @Transactional
    public void userUpdate(final ProfileUpdateRequestDto profileUpdateRequestDto, final String token) {
        validateCheckPassword(profileUpdateRequestDto.getPassword(),
                profileUpdateRequestDto.getCheckPassword());
        User user = jwtUtils.getUserOfToken(token);
        user.updateUserProfile(profileUpdateRequestDto);
    }

    private void validateDuplicateEmail(final SignUpRequestDto signUpRequestDto) {
        userRepository.findByEmail(signUpRequestDto.getEmail())
                .ifPresent(user -> {
                    throw new EmailDuplicateException();
                });
    }

    private void validateCheckPassword(String password, String checkPassword) {
        if (!password.equals(checkPassword)) {
            throw new IncorrectCheckPassword();
        }
    }
}
