package com.woopaca.progether.exception.user.impl;

import com.woopaca.progether.exception.user.UserError;
import com.woopaca.progether.exception.user.UserException;

public class InvalidSignInUserException extends UserException {

    public InvalidSignInUserException() {
        super(UserError.INVALID_SIGN_IN_USER);
    }
}
