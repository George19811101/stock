package com.example.user.common;


import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * desc: 返回结果集
 *
 * @author: xwx
 * @mail: 10086@126.com
 * @create 2018-03-23 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    @ApiModelProperty("业务状态响应码")
    private Integer code;

    @ApiModelProperty("响应消息")
    private String msg;

    @ApiModelProperty("响应数据")
    private T data;

    @ApiModelProperty("总条数")
    private Long total;

    private static Integer successCode = 200;

    private static String successMsg = "OK";

    /**
     * 成功返回，不带有数据的，直接调用ok方法，data无须传入（其实就是null）
     *
     * @return
     */
    public static <T> Result<T> ok() {
        return new Result<T>(successCode, successMsg, null, null);
    }

    /**
     * 成功返回，带有数据的，直接往OK方法丢data数据即可
     *
     * @param data
     * @return JSONResult
     */
    public static <T> Result<T> ok(T data) {
        return new Result<T>(successCode, successMsg, data, null);
    }

    /**
     * 成功返回，带有数据的，直接往OK方法丢data数据即可 分页
     *
     * @param data
     * @return JSONResult
     */
    public static <T> Result<T> ok(Page<Object> page, T data) {
        return new Result<T>(successCode, successMsg, data, page.getTotal());
    }

    /**
     * 自定义错误范围，需要传入一个自定义的枚举，可以到[ExceptionEnum.java]中自定义后再传入
     *
     * @param code 状态码
     * @param msg  返回消息
     * @return
     */

    public static <T> Result<T> exception(Integer code, String msg) {
        return new Result<T>(code, msg, null, null);
    }

    /**
     * 自定义错误，传入枚举
     *
     * @param exceptionEnum
     * @param <T>
     * @return
     */

    public static <T> Result<T> exception(ExceptionEnum exceptionEnum) {
        return new Result<T>(exceptionEnum.getCode(), exceptionEnum.getMsg(), null, null);
    }

    /**
     * 自定义错误，传入枚举
     *
     * @param exceptionEnum
     * @param <T>
     * @return
     */

    public static <T> Result<T> exception(ExceptionEnum exceptionEnum, T data) {
        return new Result<T>(200, exceptionEnum.getMsg(), data, null);
    }

    public static <T> Result<T> exception3(ExceptionEnum exceptionEnum, T data) {
        return new Result<T>(541, exceptionEnum.getMsg(), data, null);
    }

    /**
     * 自定义错误，传入枚举
     *
     * @param exceptionEnum
     * @param <T>
     * @return
     */

    public static <T> Result<T> exception2(ExceptionEnum exceptionEnum, T data) {
        return new Result<T>(201, exceptionEnum.getMsg(), data, null);
    }


}
