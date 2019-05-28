package com.chloe.weibo.core.controller;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.common.utils.ResultUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ChloeWong
 * @date 2018/9/12
 */
@RestController
public class ErrorsController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Result handleError(HttpServletRequest request) {
        // 获取 statusCode：401、403、404、500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return ResultUtil.error(statusCode, "Oops！服务器无法处理你的请求 :(");
    }
}
