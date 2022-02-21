package com.greenhi.peach_garden.item;

public class ItemDataSZ {
    private String username,time,text;
    private int headID,likes,comments;

    public ItemDataSZ(String username, String time, String text, int headID, int likes, int comments) {
        this.username = username;
        this.time = time;
        this.text = text;
        this.headID = headID;
        this.likes = likes;
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public int getHeadID() {
        return headID;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }
}
