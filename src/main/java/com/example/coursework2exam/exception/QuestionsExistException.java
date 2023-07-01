package com.example.coursework2exam.exception;

public class QuestionsExistException extends RuntimeException {
    public QuestionsExistException() {
    }

    public QuestionsExistException(String message) {
        super(message);
    }

    public QuestionsExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionsExistException(Throwable cause) {
        super(cause);
    }

    public QuestionsExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
