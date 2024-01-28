package com.autoscripter.enums;

public enum SubMenuItems {
    USERS("Users");

    private final String name;

    public String getName() {
        return name.trim();
    }

    SubMenuItems(String subMenuItem) {
        this.name = subMenuItem;
    }
}
