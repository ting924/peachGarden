package com.greenhi.peach_garden.item;

public class ItemComment {

    private Integer id;
    private Integer uid;
    private Integer dynamicId;
    private String commentContent;
    private String createTime;
    private String updateTime;

    public ItemComment(Integer id, Integer uid, Integer dynamicId, String commentContent, String createTime, String updateTime) {
        this.id = id;
        this.uid = uid;
        this.dynamicId = dynamicId;
        this.commentContent = commentContent;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


}
