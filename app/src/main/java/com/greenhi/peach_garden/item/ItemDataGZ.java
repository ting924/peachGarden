package com.greenhi.peach_garden.item;

public class ItemDataGZ {
    private String username,university,intro;
    private int headID;

    public ItemDataGZ(String username, String university, String intro, int headID) {
        this.username = username;
        this.university = university;
        this.intro = intro;
        this.headID = headID;
    }

    public String getUsername() {
        return username;
    }

    public String getUniversity() {
        return university;
    }

    public String getIntro() {
        return intro;
    }

    public int getHeadID() {
        return headID;
    }
}
