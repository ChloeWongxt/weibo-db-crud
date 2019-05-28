package com.chloe.weibo.common.utils;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.common.enums.ResultEnum;

public class ResultUtil {

    public static Result success(Object object){
        Result result=new Result();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("OK");
        result.setData(object);
        return result;
    }

    public static Result success(String msg){
        Result result=new Result();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result success(){
        Result result=new Result();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(null);
        return result;
    }
    public static Result error(ResultEnum resultEnum){
        Result result=new Result();
        result.setSuccess(false);
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(message);
        return result;
    }
    public static Result error(String msg){
        Result result=new Result();
        result.setSuccess(false);
        result.setCode(401);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result error() {
       return error(400, "错误请求");
    }
}
