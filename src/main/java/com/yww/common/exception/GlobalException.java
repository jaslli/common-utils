package com.yww.common.exception;

import lombok.Getter;

/**
 * <p>
 *      自定义异常类
 * </p>
 *
 * @author  yww
 * @since 2023/3/4
 */
@Getter
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = -1574716826948451793L;

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;

    public GlobalException(String message) {
        this.code = 500;
        this.message = message;
    }

    public GlobalException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}