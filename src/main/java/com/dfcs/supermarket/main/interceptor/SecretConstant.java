package com.dfcs.supermarket.main.interceptor;

/**
 * @Author: Helon
 * @Description: JWT使用常量值
 * @Data: Created in 2018/7/27 14:37
 * @Modified By:
 */
public class SecretConstant {

    //签名秘钥 自定义
    public static final String BASE64SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY";

    //超时毫秒数（默认30分钟）
    public static final int EXPIRESSECOND = 3600000;

    //用于JWT加密的密匙 自定义
    public static final String DATAKEY = "1fe9d8a5d6054900717b1c6ec1a9b1a2";

}
