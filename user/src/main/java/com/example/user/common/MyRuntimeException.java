package com.example.user.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MyRuntimeException extends RuntimeException {

    private Integer code;

    private String msg;

    public MyRuntimeException() {
        super();
    }

    public MyRuntimeException(String msg) {
        this.code = 500;
        this.msg = msg;
    }

    public MyRuntimeException(ExceptionEnum exceptionEnum) {

        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();

    }

}