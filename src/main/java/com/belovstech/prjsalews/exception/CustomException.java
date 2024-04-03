package com.belovstech.prjsalews.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomException {
    String message;
    Throwable stackTrace;
}
