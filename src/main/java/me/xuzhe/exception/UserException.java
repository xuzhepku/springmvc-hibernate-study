package me.xuzhe.exception;

/**
 * Created by XuZhe on 2016/8/16.
 */
public class UserException extends RuntimeException {
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
