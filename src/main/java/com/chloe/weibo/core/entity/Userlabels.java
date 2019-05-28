package com.chloe.weibo.core.entity;

/**
 * @author ChloeWong
 * @date 2019/5/4
 */
public class Userlabels {
    private int labelsId;
    private int userId;
    private String labels;

    public int getLabelsId() {
        return labelsId;
    }

    public void setLabelsId(int labelsId) {
        this.labelsId = labelsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
