package com.woopaca.progether.exception.user;

public class EmailDuplicateException extends UserException {

    public EmailDuplicateException() {
        super(UserError.EMAIL_DUPLICATE);
    }
}
