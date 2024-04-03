package com.belovstech.prjsalews.exception;

public class DataBaseException extends RuntimeException {

    public DataBaseException() {
        super("Ошибка в базе данных");
    }

    public DataBaseException(String message) {
        super(message);
    }
}
