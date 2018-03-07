package com.grule.core.exception;

/**
 * Created on 2018/03/05.
 */
public class UnitRunException extends Exception {

    public UnitRunException(String message) {
        super(message);
    }

    public UnitRunException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnitRunException(Throwable cause) {
        super(cause);
    }
}
