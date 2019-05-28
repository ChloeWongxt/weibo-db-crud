package com.chloe.weibo.common.exception;

import com.chloe.weibo.common.enums.ResultEnum;

/**
 * 自定义统一异常
 *
 * @author ChloeWong
 * @date 2018/9/8
 */
public class WeiboException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    public WeiboException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public WeiboException(String message) {
        this(400, message);
    }

    public WeiboException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
