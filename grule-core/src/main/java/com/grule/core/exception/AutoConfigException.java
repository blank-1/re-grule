package com.grule.core.exception;

/**
 * Created on 2018/03/05.
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
