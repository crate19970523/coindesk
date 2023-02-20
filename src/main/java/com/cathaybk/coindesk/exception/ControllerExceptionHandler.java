package com.cathaybk.coindesk.exception;

import com.cathaybk.coindesk.bean.response.BasicResponse;
import com.cathaybk.coindesk.bean.response.ErrorMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({AuthException.class})
    @ResponseBody
    public BasicResponse authException(Throwable e,
                                       RedirectAttributes redirectAttributes) {
        return new BasicResponse().setErrorMessage(new ErrorMessage().setErrorMessage(e.getMessage()));
    }

    @ExceptionHandler({CoindeskException.class})
    @ResponseBody
    public BasicResponse coindeskException(Throwable e,
                                           RedirectAttributes redirectAttributes) {
        return new BasicResponse().setErrorMessage(new ErrorMessage().setErrorMessage("have unknown error")
                .setErrorDetail(e.getMessage()));
    }

    @ExceptionHandler({DbException.class})
    @ResponseBody
    public BasicResponse dbException(Throwable e,
                                     RedirectAttributes redirectAttributes) {
        return new BasicResponse().setErrorMessage(new ErrorMessage().setErrorMessage("Database access error")
                .setErrorDetail(e.getMessage()));
    }

    @ExceptionHandler({HandlerException.class})
    @ResponseBody
    public BasicResponse handlerException(Throwable e,
                                          RedirectAttributes redirectAttributes) {
        return new BasicResponse().setErrorMessage(new ErrorMessage().setErrorMessage("Error processing 3rd party API.")
                .setErrorDetail(e.getMessage()));
    }

    @ExceptionHandler({ThirdPartyResponseException.class})
    @ResponseBody
    public BasicResponse thirdPartyResponseException(Throwable e,
                                                     RedirectAttributes redirectAttributes) {
        return new BasicResponse().setErrorMessage(new ErrorMessage().setErrorMessage("have error from 3rd party api response: " + e.getMessage()));
    }
}
