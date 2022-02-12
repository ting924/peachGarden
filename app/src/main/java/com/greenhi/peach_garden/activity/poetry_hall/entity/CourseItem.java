package com.greenhi.peach_garden.activity.poetry_hall.entity;

import android.graphics.drawable.Drawable;

public class CourseItem {
    private int img_id;
    private String type;
    private String intro;
    private String teacher;
    public CourseItem(int img_id,String type,String intro,String teacher){
        this.img_id=img_id;
        this.intro=intro;
        this.teacher=teacher;
        this.type=type;
    }

    public int getImg_id() {
        return img_id;
    }

    public String getType() {
        return type;
    }

    public String getIntro() {
        return intro;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
