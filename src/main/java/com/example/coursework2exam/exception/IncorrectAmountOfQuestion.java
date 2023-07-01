package com.example.coursework2exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectAmountOfQuestion extends RuntimeException {

    public IncorrectAmountOfQuestion() {
    }

    public IncorrectAmountOfQuestion(String message) {
        super(message);
    }

    public IncorrectAmountOfQuestion(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectAmountOfQuestion(Throwable cause) {
        super(cause);
    }

    public IncorrectAmountOfQuestion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
