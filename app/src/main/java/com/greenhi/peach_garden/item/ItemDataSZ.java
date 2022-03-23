package com.greenhi.peach_garden.item;

public class ItemDataSZ {
    private String userName,createTime,dynamicContent;
    private int image,uid,loveNumber,commentNumber;

    public ItemDataSZ(String userName, String createTime, String dynamicContent, int image,int uid, int loveNumber, int commentNumber) {
        this.userName = userName;
        this.createTime = createTime;
        this.dynamicContent = dynamicContent;
        this.image=image;
        this.uid = uid;
        this.loveNumber = loveNumber;
        this.commentNumber = commentNumber;
    }

    public String getUsername() {
        return userName;
    }

    public String getTime() {
        return createTime;
    }

    public String getText() {
        return dynamicContent;
    }

    public int getHead() {
        return image;
    }

    public int getHeadID() {
        return uid;
    }

    public int getLikes() {
        return loveNumber;
    }

    public int getComments() {
        return commentNumber;
    }
}
