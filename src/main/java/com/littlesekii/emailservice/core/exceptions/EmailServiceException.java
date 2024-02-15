package com.littlesekii.emailservice.core.exceptions;

import java.io.Serial;

public class EmailServiceException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -8357045300218060790L;

    public EmailServiceException(String message) {
        super(message);
    }

    public EmailServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
