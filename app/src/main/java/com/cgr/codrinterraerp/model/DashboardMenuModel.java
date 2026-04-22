package com.cgr.codrinterraerp.model;

public class DashboardMenuModel {

    private String title, sub;
    private int icon, bg, tag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public DashboardMenuModel(String title, String sub, int icon, int bg, int tag) {
        this.title = title;
        this.sub = sub;
        this.icon = icon;
        this.bg = bg;
        this.tag = tag;
    }
}