package com.excellent.accreditation.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @program: course-accreditation
 * @description: 空异常
 * @author: ashe
 * @create: 2019-12-13 16:37
 */
public class EmptyException extends CommonException {
    public EmptyException(String message) {
        super(message);
    }

    public EmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }
}
