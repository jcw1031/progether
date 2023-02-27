package com.woopaca.progether.exception.user.impl;

import com.woopaca.progether.exception.user.UserError;
import com.woopaca.progether.exception.user.UserException;

public class IncorrectCheckPassword extends UserException {

    public IncorrectCheckPassword() {
        super(UserError.INCORRECT_CHECK_PASSWORD);
    }
}
