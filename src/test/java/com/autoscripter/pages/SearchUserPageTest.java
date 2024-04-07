package com.autoscripter.pages;

import com.automatrix.annotation.AllureTest;
import com.autoscripter.BaseTest;
import com.autoscripter.pages.admin.AdminPage;
import com.autoscripter.pages.admin.SearchUserPage;
import com.autoscripter.pages.login.LoginPage;
import io.qameta.allure.SeverityLevel;
import org.json.JSONArray;
import org.testng.annotations.Test;

import java.util.Map;

public class SearchUserPageTest extends BaseTest {


    private final LoginPage loginPage;
    private final AdminPage adminPage;
    private final SearchUserPage searchUserPage;

    public SearchUserPageTest()
    {
        this.loginPage = new LoginPage();
        this.adminPage = new AdminPage();
        this.searchUserPage = new SearchUserPage();
    }

    @Test
    @AllureTest(story = "JIRA-13438", description = "Verify Search Page with valid data", owner = "DXF XYW",severity = SeverityLevel.CRITICAL)
    public void searchPageTestValidData(Map<String,String> data)
    {
        loginPage.login(data.get("Username"), data.get("Password"));
        adminPage.clickAdminMenu();
        adminPage.clickTopMenu();
        adminPage.clickSubMenu();
        searchUserPage.enterUsername(data.get("User_Name"));
        searchUserPage.selectUserRole(data.get("User_Role"));
        searchUserPage.enterEmployeeName(data.get("Employee_Name"));
        searchUserPage.selectStatus(data.get("Status"));
        searchUserPage.clickSearchButton();

        JSONArray tableData = searchUserPage.getTableData();
        System.out.println(tableData);

    }

}
