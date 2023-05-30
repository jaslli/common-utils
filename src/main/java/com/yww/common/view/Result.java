package com.yww.common.view;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yww.common.constant.ResultCode;
import lombok.Data;

/**
 * <p>
 *      结果封装类
 * </p>
 *
 * @author  yww
 * @since 2023/3/4
 */
@Data
@SuppressWarnings("all")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T> {

    /**
     * 成功标记
     */
    private boolean success;

    /**
     * 状态码
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Integer code;

    /**
     * 返回内容
     */
    private String errMsg;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 私有化无参构造函数
     */
    private Result() {}

    /**
     * 返回一个响应成功的信息，不带数据
     *
     * @return Result
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getStatus());
        result.setSuccess(true);
        return result;
    }

    /**
     * 返回一个响应成功的信息，带数据
     *
     * @param data 返回数据
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getStatus());
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    /**
     * 返回一个响应错误的信息
     *
     * @return Result
     */
    public static <T> Result<T> failure() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAILED.getStatus());
        result.setErrMsg(ResultCode.FAILED.getMessage());
        result.setSuccess(false);
        return result;
    }

    /**
     * 返回一个响应错误的信息，可以自定义错误信息
     *
     * @param message 返回的错误信息内容
     * @return Result
     */
    public static <T> Result<T> failure(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAILED.getStatus());
        result.setErrMsg(message);
        result.setSuccess(false);
        return result;
    }

    /**
     * 根据结果状态码，返回一个响应错误的信息
     *
     * @param resultCode 状态码
     * @return Result
     */
    public static <T> Result<T> failure(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAILED.getStatus());
        result.setErrMsg(resultCode.getMessage());
        result.setSuccess(false);
        return result;
    }

    /**
     * 返回一个自定义状态码和错误信息的错误提示
     *
     * @param code    状态码
     * @param message 消息
     * @return Result
     */
    public static <T> Result<T> failure(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setErrMsg(message);
        result.setSuccess(false);
        return result;
    }

}