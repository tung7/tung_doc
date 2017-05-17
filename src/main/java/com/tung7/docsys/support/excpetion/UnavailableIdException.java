package com.tung7.docsys.support.excpetion;

/**
 * id值异常时
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/10.
 * @update
 */

public class UnavailableIdException extends RuntimeException{
    public UnavailableIdException() {
        super();
    }

    public UnavailableIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnavailableIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavailableIdException(String message) {
        super(message);
    }

    public UnavailableIdException(Throwable cause) {
        super(cause);
    }
}
