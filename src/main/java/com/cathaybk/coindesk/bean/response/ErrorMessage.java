package com.cathaybk.coindesk.bean.response;

public class ErrorMessage {
    private String errorMessage;
    private String errorDetail;

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorMessage setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public ErrorMessage setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
        return this;
    }
}
