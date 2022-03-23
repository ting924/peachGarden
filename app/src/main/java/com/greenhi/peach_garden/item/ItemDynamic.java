package com.greenhi.peach_garden.item;

public class ItemDynamic {
    private Integer id;
    private Integer uid;
    private String userName;
    private String dynamicContent;
    private String imgCount;
    private Integer loveNumber;
    private Integer commentNumber;
    private String createTime;

    public ItemDynamic(Integer id, Integer uid, String userName, String dynamicContent, String imgCount, Integer loveNumber, Integer commentNumber, String createTime) {
        this.id = id;
        this.uid = uid;
        this.userName = userName;
        this.dynamicContent = dynamicContent;
        this.imgCount = imgCount;
        this.loveNumber = loveNumber;
        this.commentNumber = commentNumber;
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

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public String getImgCount() {
        return imgCount;
    }

    public void setImgCount(String image) {
        this.imgCount = image;
    }

    public Integer getLoveNumber() {
        return loveNumber;
    }

    public void setLoveNumber(Integer loveNumber) {
        this.loveNumber = loveNumber;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
