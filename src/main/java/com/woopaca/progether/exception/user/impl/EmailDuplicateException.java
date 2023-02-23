package com.woopaca.progether.exception.user.impl;

import com.woopaca.progether.exception.user.UserError;
import com.woopaca.progether.exception.user.UserException;

public class EmailDuplicateException extends UserException {

    public EmailDuplicateException() {
        super(UserError.EMAIL_DUPLICATE);
    }
}
