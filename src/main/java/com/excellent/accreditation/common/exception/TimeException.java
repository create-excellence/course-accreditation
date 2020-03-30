package com.excellent.accreditation.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @description:
 * @author: ashe
 * @DATE: 2020/3/30 11:03
 */
public class TimeException extends CommonException {
    public TimeException(String message) {
        super(message);
    }

    public TimeException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }
}
