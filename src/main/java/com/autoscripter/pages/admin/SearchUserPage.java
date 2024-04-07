package com.autoscripter.pages.admin;

import com.autoscripter.pages.BasePage;
import com.autoscripter.utils.XpathHelper;
import org.json.JSONArray;

public class SearchUserPage {


    private final BasePage basePage;

    public SearchUserPage() {
        this.basePage = new BasePage();
    }

    public void enterUsername(String value) {
        basePage.sendKeys("Username", value);
    }

    public void selectUserRole(String role)
    {
        basePage.select("User Role" ,role);
    }

    public void enterEmployeeName(String value)
    {
        basePage.sendHintKeys("Employee Name", value);
    }


    public void selectStatus(String value)
    {
        basePage.select("Status",value);
    }

    public void clickSearchButton()
    {
        basePage.clickSubmitButton("Search");
    }

    public void clickResetButton()
    {
        basePage.clickCancelOrResetButton("Reset");
    }

    public JSONArray getTableData()
    {
        return basePage.getTableData(XpathHelper.TABLE_HEADER, XpathHelper.TABLE_ROW);
    }
}
