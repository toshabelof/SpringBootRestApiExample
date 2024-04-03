package com.belovstech.prjsalews.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Данные не найдены");
    }

    public NotFoundException(String message) {
        super(message);
    }

}
