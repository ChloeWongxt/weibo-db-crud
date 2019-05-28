package com.chloe.weibo.base.handler;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.common.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常统一处理 Handler
 *
 * @author ChloeWong
 * @date 2018/9/8
 */
@ControllerAdvice
public class ExceptionsHandler {

    Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    /**
     * 处理业异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(WeiboException.class)
    private Result handleWeiboException(WeiboException e) {
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理系统异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    private Result handleException(Exception e) {
        logger.error("系统异常 =>", e);
        return ResultUtil.error(500, "系统异常!");
    }
}
