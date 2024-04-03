package com.belovstech.prjsalews.exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException() {
        super("Данные уже существуют");
    }

    public AlreadyExistException(String message) {
        super(message);
    }
}
