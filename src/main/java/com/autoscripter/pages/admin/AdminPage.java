package com.autoscripter.pages.admin;

import com.autoscripter.enums.MegaMenu;
import com.autoscripter.enums.SubMenuItems;
import com.autoscripter.enums.TopMenuItems;
import com.autoscripter.pages.BasePage;
import com.autoscripter.utils.XpathHelper;
import org.json.JSONArray;

public class AdminPage {
    private final BasePage basePage = new BasePage();

    public void clickAdminMenu()
    {
        basePage.click(MegaMenu.ADMIN);
    }

    public void clickTopMenu()
    {
        basePage.click(TopMenuItems.USER_MANAGEMENT);
    }

    public void clickSubMenu()
    {
        basePage.click(SubMenuItems.USERS);
    }

    public String getTopMenuItemText()
    {
        return basePage.getText(TopMenuItems.USER_MANAGEMENT);
    }

    public String getPageHeader()
    {
        return basePage.getPageHeader(TopMenuItems.USER_MANAGEMENT);
    }

    public JSONArray getTableData()
    {
        return basePage.getTableData(XpathHelper.TABLE_HEADER, XpathHelper.TABLE_ROW);
    }
}
