package com.example.user.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5187687995319002219L;
    private Integer code;
    private Boolean success;
    private String message;
    private Integer pages;
    private Integer total;
    private T data;

    public static <T> Result<T> definition(int code, String message, Boolean success, T data) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setSuccess(success);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    public static <T> Result<T> ok(String message, T data) {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.SUCCESS.getCode());
        if(message == null){
            r.setMessage(ResultEnum.SUCCESS.getMessage());
        }else{
            r.setMessage(message);
        }
        r.setSuccess(true);
        r.setData(data);
        return r;
    }

    public static <T> Result<T> pageOk(Integer pages, Integer total, T data) {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMessage(ResultEnum.SUCCESS.getMessage());
        r.setSuccess(true);
        r.setData(data);
        r.setPages(pages);
        r.setTotal(total);
        return r;
    }

    public static <T> Result<T> pageError() {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.ERROR.getCode());
        r.setMessage(ResultEnum.ERROR.getMessage());
        r.setSuccess(false);
        r.setPages(0);
        r.setTotal(0);
        return r;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMessage(ResultEnum.SUCCESS.getMessage());
        r.setSuccess(true);
        r.setData(data);
        return r;
    }

    public static <T> Result<T> ok(String message) {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setSuccess(true);
        if(message == null){
            r.setMessage(ResultEnum.SUCCESS.getMessage());
        }else{
            r.setMessage(message);
        }
        return r;
    }

    public static <T> Result<T> ok() {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setSuccess(true);
        r.setMessage(ResultEnum.SUCCESS.getMessage());
        return r;
    }

    public static <T> Result<T> error() {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.ERROR.getCode());
        r.setSuccess(false);
        r.setMessage(ResultEnum.ERROR.getMessage());
        return r;
    }

    public static <T> Result<T> error(String message) {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.ERROR.getCode());
        r.setSuccess(false);
        if(message == null){
            r.setMessage(ResultEnum.SUCCESS.getMessage());
        }else{
            r.setMessage(message);
        }
        return r;
    }

    public static <T> Result<T> error(int code, String message) {
        Result<T> r = new Result<>();
        r.setCode(ResultEnum.ERROR.getCode());
        r.setSuccess(false);
        if(message == null){
            r.setMessage(ResultEnum.SUCCESS.getMessage());
        }else{
            r.setMessage(message);
        }
        return r;
    }

}