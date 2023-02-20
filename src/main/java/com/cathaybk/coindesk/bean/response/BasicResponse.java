package com.cathaybk.coindesk.bean.response;

public class BasicResponse {
    private ErrorMessage errorMessage;

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public BasicResponse setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
