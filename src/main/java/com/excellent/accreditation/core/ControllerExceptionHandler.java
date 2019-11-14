package com.excellent.accreditation.core;

import com.excellent.accreditation.common.domain.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author evildoer
 * @Description Exception handler of controller
 * @Date 21:36 2019/11/14
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServerResponse handleGlobalException(Exception e) {
        ServerResponse serverResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return serverResponse.createByErrorCodeMessage(status.value(), status.getReasonPhrase());
    }


    private <T> ServerResponse<T> handleBaseException(Throwable t) {
        Assert.notNull(t, "Throwable must not be null");

        log.error("Captured an exception", t);

        ServerResponse<T> serverResponse = ServerResponse.createByErrorMessage(t.getMessage());

        return serverResponse;
    }
}

