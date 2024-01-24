package com.autoscripter.pages;


import com.automatrix.annotation.AllureTest;
import com.autoscripter.BaseTest;
import com.autoscripter.pages.admin.AdminPage;
import com.autoscripter.pages.login.LoginPage;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.Map;

public class AdminPageTest extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final AdminPage adminPage = new AdminPage();


    @Test
    @AllureTest(story = "JIRA-12438", description = "Verify Admin Page with valid data", owner = "DEF XYZ",severity = SeverityLevel.MINOR)
    public void adminPageTestValidData(Map<String,String> data)
    {
        loginPage.login(data.get("Username"), data.get("Password"));
        adminPage.clickHeader();
        adminPage.getTableData().getJSONObject(0);
    }
}
