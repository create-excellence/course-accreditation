package com.excellent.accreditation.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @ClassName DatabaseException
 * @Description TODO
 * @Author 安羽兮
 * @Date 2019/12/49:17
 * @Version 1.0
 **/
public class DatabaseException extends CommonException {

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
