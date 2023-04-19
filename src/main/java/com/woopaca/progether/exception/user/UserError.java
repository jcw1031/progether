package com.woopaca.progether.exception.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserError {

    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다.", "email"),
    INVALID_SIGN_IN_USER(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.", ""),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원이 존재하지 않습니다.", ""),
    INCORRECT_CHECK_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호 확인이 일치하지 않습니다.", "checkPassword"),
    PART_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 파트입니다.", "part");

    private final HttpStatus httpStatus;
    private final String message;
    private final String field;
}
