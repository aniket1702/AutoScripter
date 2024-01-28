package com.autoscripter.enums;

public enum TopMenuItems {
    USER_MANAGEMENT("User Management "),
    JOB("JOB "),
    ORGANIZATION("Organization "),
    QUALIFICATIONS("Qualifications "),
    NATIONALITIES("Nationalities "),
    MORE("More ");

    private final String name;

    public String getName() {
        return name.trim();
    }

    TopMenuItems(String topMenuItem) {
        this.name = topMenuItem;
    }

}
