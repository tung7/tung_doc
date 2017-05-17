package com.tung7.docsys.support.excpetion;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/12.
 * @update
 */

public class NullNameException extends RuntimeException{
    public NullNameException() {
        super();
    }

    public NullNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NullNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullNameException(String message) {
        super(message);
    }

    public NullNameException(Throwable cause) {
        super(cause);
    }
}
