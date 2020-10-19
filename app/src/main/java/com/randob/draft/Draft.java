package com.randob.draft;

import com.google.gson.annotations.SerializedName;

public class Draft {
    private int id;
    private String title;
    private String text;

    Draft(String title, String text){
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
