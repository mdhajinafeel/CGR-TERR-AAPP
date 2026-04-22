package com.cgr.codrinterraerp.model;

import com.cgr.codrinterraerp.constants.NavigationType;

import java.io.Serializable;

public class MenuModel implements Serializable {

    private int menuIcon;
    private String menuName;
    private NavigationType navigationType;

    public int getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public NavigationType getNavigationType() {
        return navigationType;
    }

    public void setNavigationType(NavigationType navigationType) {
        this.navigationType = navigationType;
    }

    public MenuModel(int menuIcon, String menuName, NavigationType navigationType) {
        this.menuIcon = menuIcon;
        this.menuName = menuName;
        this.navigationType = navigationType;
    }
}