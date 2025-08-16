package com.autoscripter.pages;


import com.autoscripter.BaseTest;
import com.autoscripter.pages.login.LoginPage;
import com.caf.automation.annotation.AllureTest;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginPageTest extends BaseTest {

    private final LoginPage loginPage = new LoginPage();

    @Test
    @AllureTest(story = "JIRA-12398", description = "Verify Login Page with valid credentials", owner = "ABC XYZ",severity = SeverityLevel.CRITICAL)
    public void validLoginPageTest(Map<String,String> data) {

        loginPage.login(data.get("Username"), data.get("Password"));

    }

    @Test
    @AllureTest(story = "JIRA-12398", description = "Verify Login Page with Invalid credentials", owner = "ABC XYZ",severity = SeverityLevel.CRITICAL)
    public void inValidLoginPageTest(Map<String,String> data) {
        loginPage.login(data.get("Username"), data.get("Password"));
    }
}
