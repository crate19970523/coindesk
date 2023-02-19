package com.cathaybk.coindesk.exception;

public class CoindeskException extends RuntimeException {
    public CoindeskException(String message) {
        super(message);
    }

    public CoindeskException(String message, Throwable cause) {
        super(message, cause);
    }
}
