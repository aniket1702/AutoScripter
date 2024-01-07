package com.autoscripter.pages.login;

import com.autoscripter.pages.BasePage;

public class LoginPage {

    private final BasePage basePage = new BasePage();

    private void enterUsername(String value) {
        basePage.sendKeys("Username", value);
    }

    private void enterPassword(String value) {
        basePage.sendKeys("Password", value);
    }

    private void clickLoginButton() {basePage.clickSubmitButton("Login");}

    public void login(String username, String password)
    {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
