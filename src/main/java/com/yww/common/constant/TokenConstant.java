package com.yww.common.constant;

/**
 * <p>
 *      Token相关常量类
 * </p>
 *
 * @author  yww
 * @since 2023/3/4
 */
public class TokenConstant {

    /**
     * Token的密钥
     */
    public static final String TOKEN_SECRET = "wslioy";

    /**
     * Token在请求头部的名称
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * Token的前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * header的头部加密算法声明
     */
    public static final String TOKEN_ALG = "HMAC512";

    /**
     * header的头部Token类型
     */
    public static final String TOKEN_TYP = "JWT";

    /**
     * Token的签发者
     */
    public static final String TOKEN_ISSUER = "yww";

    /**
     * Token面向的主体
     */
    public static final String TOKEN_SUBJECT = "management";

    /**
     * Token的接收方
     */
    public static final String TOKEN_AUDIENCE = "vue-management";

    /**
     * Token解析后当前用户的信息
     */
    public static final String ADMIN_TOKEN_CONTEXT = "current_user";

}
