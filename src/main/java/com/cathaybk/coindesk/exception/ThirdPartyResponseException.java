package com.cathaybk.coindesk.exception;

public class ThirdPartyResponseException extends RuntimeException {

    public ThirdPartyResponseException(String message) {
        super(message);
    }

    public ThirdPartyResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
