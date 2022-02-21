package com.greenhi.peach_garden.item;

public class ItemDataSC {

    private String title;
    private String username;
    private int coverID,headID;

    public ItemDataSC(String title, String username, int coverID, int headID) {
        this.title = title;
        this.username = username;
        this.coverID = coverID;
        this.headID = headID;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public int getCoverID() {
        return coverID;
    }

    public int getHeadID() {
        return headID;
    }
}
