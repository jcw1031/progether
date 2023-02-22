package com.woopaca.progether.exception.user;

public class InvalidSignInUserException extends UserException {

    public InvalidSignInUserException() {
        super(UserError.INVALID_SIGN_IN_USER);
    }
}
