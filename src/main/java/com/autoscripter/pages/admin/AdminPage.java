package com.autoscripter.pages.admin;

import com.autoscripter.pages.BasePage;
import org.json.JSONArray;
import org.openqa.selenium.By;

public class AdminPage {
    private final BasePage basePage = new BasePage();

    private final By adminMenu = By.xpath("//span[text()='Admin']/parent::a");
    By headerXpath = By.xpath("//div[@class='oxd-table-header']//div[@class='oxd-table-header-cell oxd-padding-cell oxd-table-th']");
    By rowXpath = By.xpath("//div[@class='oxd-table-card']//div[@class='oxd-table-cell oxd-padding-cell']/div");


    public void clickHeader()
    {
        basePage.clickLink(adminMenu);
    }

    public JSONArray getTableData()
    {
        return basePage.getTableData(headerXpath, rowXpath);
    }
}
