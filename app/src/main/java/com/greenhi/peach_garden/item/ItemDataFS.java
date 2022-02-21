package com.greenhi.peach_garden.item;

public class ItemDataFS {
    private String username,intro;
    private int headID;

    public ItemDataFS(String username, String intro, int headID) {
        this.username = username;
        this.intro = intro;
        this.headID = headID;
    }

    public String getUsername() {
        return username;
    }

    public String getIntro() {
        return intro;
    }

    public int getHeadID() {
        return headID;
    }
}
