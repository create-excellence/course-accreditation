package com.excellent.accreditation.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @program: course-accreditation
 * @description: 唯一的编号(代码)的异常类
 * @author: ashe
 * @create: 2019-12-03 14:15
 */
public class UniqueException extends CommonException {
    public UniqueException(String message) {
        super(message);
    }

    public UniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }
}
