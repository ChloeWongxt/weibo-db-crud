package com.chloe.weibo.pojo.vo;

/**
 * @author ChloeWong
 * @date 2018/9/17
 */

public class StorageInfoVO {
    /**
     * 文件名
     */
    private String name;

    /**
     * 存储路径（相对）
     */
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
