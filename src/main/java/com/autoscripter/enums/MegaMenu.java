package com.autoscripter.enums;

public enum MegaMenu
{
    ADMIN("Admin");

    private final String name;

    public String getName() {
        return name.trim();
    }

    MegaMenu(String megaMenu) {
        this.name = megaMenu;
    }

}
