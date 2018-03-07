package com.regel.core.exception;

/**
 * Created by  on 2017/8/11.
 */
public class AutoConfigException extends Exception {

    public AutoConfigException(String message) {
        super(message);
    }

    public AutoConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutoConfigException(Throwable cause) {
        super(cause);
    }
}
