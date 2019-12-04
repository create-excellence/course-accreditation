package com.excellent.accreditation.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @program: course-accreditation
 * @description: Excel操作异常
 * @author: ashe
 * @create: 2019-12-04 10:20
 */
public class ExcelException extends  CommonException {
    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }
}
