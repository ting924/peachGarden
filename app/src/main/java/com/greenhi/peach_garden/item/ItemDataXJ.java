package com.greenhi.peach_garden.item;

public class ItemDataXJ {
    private String name,time,content;
    private int headID;

    public ItemDataXJ(String name, String time, String content, int headID) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.headID = headID;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int getHeadID() {
        return headID;
    }
}
