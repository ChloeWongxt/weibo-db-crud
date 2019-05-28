package com.chloe.weibo.pojo.data;

import lombok.Data;

@Data
public class Result<T> {
    // 成功标志
    private Boolean success;

    // 错误码
    private Integer code;

    // 提示错误信息
    private String msg;

    // 具体内容
    private T data;
}
