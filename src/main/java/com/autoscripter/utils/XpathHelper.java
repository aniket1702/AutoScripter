package com.autoscripter.utils;


import org.openqa.selenium.By;

public class XpathHelper
{

    private XpathHelper(){}

    public static final String BREADCRUMB_HEADER = "//h6[text()='%s']";
    public static final String LEFT_PANEL_HEADER = "//span[text()='%s']/parent::a";
    public static final String TOP_MENU = "//ul/li/span[text()='%s ']";
    public static final String SUB_MENU = "//ul[@class='oxd-dropdown-menu']/li/a[text()='%s']";
    public static final String TEXT_BOX = "//label[text()='%s']/parent::div/following-sibling::div/input";
    public static final String HINT_TEXT_BOX = "";
    public static final String DROPDOWN = "";
    public static final String CANCEL_OR_RESET_BUTTON = "";
    public static final String SUBMIT_BUTTON = "//button[@type='submit' and text()=' %s ']";
    public static final By TABLE_HEADER = By.xpath("//div[@class='oxd-table-header']//div[@class='oxd-table-header-cell oxd-padding-cell oxd-table-th']");
    public static final By TABLE_ROW = By.xpath("//div[@class='oxd-table-card']//div[@class='oxd-table-cell oxd-padding-cell']/div");


}