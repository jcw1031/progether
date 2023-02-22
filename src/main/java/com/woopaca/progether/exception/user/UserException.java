package com.woopaca.progether.exception.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class UserException extends RuntimeException {

    private final UserError userError;
}
