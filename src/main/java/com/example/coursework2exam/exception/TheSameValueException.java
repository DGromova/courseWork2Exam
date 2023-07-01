package com.example.coursework2exam.exception;

public class TheSameValueException extends RuntimeException {
    public TheSameValueException() {
    }

    public TheSameValueException(String message) {
        super(message);
    }

    public TheSameValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public TheSameValueException(Throwable cause) {
        super(cause);
    }

    public TheSameValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
