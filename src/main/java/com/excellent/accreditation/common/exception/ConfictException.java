package com.excellent.accreditation.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @Author 安羽兮
 * @Description 冲突异常
 * @Date 9:10 2019/12/4
 **/
public class ConfictException extends CommonException {
    public ConfictException(String message) {
        super(message);
    }

    public ConfictException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
