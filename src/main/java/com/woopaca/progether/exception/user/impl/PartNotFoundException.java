package com.woopaca.progether.exception.user.impl;

import com.woopaca.progether.exception.user.UserError;
import com.woopaca.progether.exception.user.UserException;

public class PartNotFoundException extends UserException
{
    public PartNotFoundException() {
        super(UserError.PART_NOT_FOUND);
    }
}
