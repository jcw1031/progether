package com.woopaca.progether.exception.user.impl;

import com.woopaca.progether.exception.user.UserError;
import com.woopaca.progether.exception.user.UserException;

public class UserNotFoundException extends UserException {

    public UserNotFoundException() {
        super(UserError.USER_NOT_FOUND);
    }
}
