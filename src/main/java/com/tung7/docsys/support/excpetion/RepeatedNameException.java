package com.tung7.docsys.support.excpetion;

/**
 * 记录的id重复时
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/10.
 * @update
 */

public class RepeatedNameException extends RuntimeException{
    public RepeatedNameException() {
        super();
    }

    public RepeatedNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RepeatedNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatedNameException(String message) {
        super(message);
    }

    public RepeatedNameException(Throwable cause) {
        super(cause);
    }
}
