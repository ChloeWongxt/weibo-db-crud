package com.chloe.weibo.core.controller;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.pojo.vo.UploadInfoVO;
import com.chloe.weibo.core.service.interfaces.LocalStorageService;
import com.chloe.weibo.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Angus
 * @date 2019/5/19
 */

@RestController
@RequestMapping("/local-storage")
public class LocalStorageController {

    @Autowired
    private LocalStorageService localStorageService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("type") String type, @RequestParam("file") MultipartFile file) {
        UploadInfoVO uploadInfo = localStorageService.upload(type, file);
        return ResultUtil.success(uploadInfo);
    }

}
