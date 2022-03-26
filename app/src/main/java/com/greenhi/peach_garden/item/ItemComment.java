package com.greenhi.peach_garden.item;

public class ItemComment {

    private Integer id;
    private Integer uid;
    private String userName;
    private Integer dynamicId;
    private String commentContent;
    private String createTime;

    public ItemComment(Integer id, Integer uid, String userName,Integer dynamicId, String commentContent, String createTime) {
        this.id = id;
        this.uid = uid;
        this.userName=userName;
        this.dynamicId = dynamicId;
        this.commentContent = commentContent;
        this.createTime = createTime;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
