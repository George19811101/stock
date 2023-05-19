package com.example.user.config;

public enum ResultEnum {


    SUCCESS(200, "操作成功"),

    ERROR(500, "操作失败");

    private final Integer code;

    private final String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return code;
    }

    public String getMessage() {
        return message;
    }
}
