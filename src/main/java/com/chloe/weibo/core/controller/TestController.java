package com.chloe.weibo.core.controller;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.common.exception.WeiboException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping(value = "/addarea")
    public String listArea(){
        return "hello";
    }

    @GetMapping("/exception")
    public Result getException(){
        int a = 1 / 0;
        return null;
    }

    @GetMapping("/weibo-exception")
    public Result getWeiboException() {
        throw new WeiboException("你输入的账号不符合规范！");
    }
}
