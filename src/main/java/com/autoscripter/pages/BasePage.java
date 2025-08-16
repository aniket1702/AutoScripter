package com.autoscripter.pages;


import com.autoscripter.enums.MegaMenu;
import com.autoscripter.enums.SubMenuItems;
import com.autoscripter.enums.TopMenuItems;
import com.autoscripter.utils.XpathHelper;
import com.caf.automation.loggers.LogType;
import com.caf.automation.loggers.LogUtils;
import com.caf.automation.web.enums.WaitType;
import com.caf.automation.web.waitstrategy.WaitStrategy;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePage  {


    public void sendKeys(String label, String value){
        By webLabel = By.xpath(String.format(XpathHelper.TEXT_BOX,label));
        WaitStrategy.performExplicitWait(WaitType.PRESENCE,webLabel).sendKeys(value);
        LogUtils.log(LogType.PASS,"LABEL: "+label+"\nVALUE: "+value);
    }



    public void clickSubmitButton(String label) {
        By clickSubmitButton = By.xpath(String.format(XpathHelper.SUBMIT_BUTTON,label));
        WaitStrategy.performExplicitWait(WaitType.PRESENCE,clickSubmitButton).click();
        LogUtils.log(LogType.PASS,"Submit button is clicked.");
    }

    public void clickCancelOrResetButton(String label) {
        By clickCancelOrResetButton = By.xpath(String.format(XpathHelper.CANCEL_OR_RESET_BUTTON,label));
        WaitStrategy.performExplicitWait(WaitType.PRESENCE,clickCancelOrResetButton).click();
        LogUtils.log(LogType.PASS,"Submit button is clicked.");
    }



    public void click(MegaMenu megaMenu)
    {
        By menuLink =By.xpath(String.format(XpathHelper.LEFT_PANEL_HEADER,megaMenu.getName()));

        WaitStrategy.performExplicitWait(WaitType.CLICKABLE,menuLink).click();
        LogUtils.log(LogType.PASS, megaMenu.getName() + " Link is clicked.");
    }

    public void click(TopMenuItems topMenuItems)
    {
        By menuLink =By.xpath(String.format(XpathHelper.TOP_MENU,topMenuItems.getName()));

        WaitStrategy.performExplicitWait(WaitType.CLICKABLE,menuLink).click();
        LogUtils.log(LogType.PASS, topMenuItems.getName() + " Link is clicked.");
    }

    public void click(SubMenuItems subMenuItems)
    {
        By menuLink =By.xpath(String.format(XpathHelper.SUB_MENU,subMenuItems.getName()));

        WaitStrategy.performExplicitWait(WaitType.CLICKABLE,menuLink).click();
        LogUtils.log(LogType.PASS, subMenuItems.getName() + " Link is clicked.");
    }

    public void click(By by) {
        WaitStrategy.performExplicitWait(WaitType.CLICKABLE, by).click();
        LogUtils.log(LogType.PASS, "Button is clicked.");

    }

    public void select(String label, String value) {
        By by = By.xpath(String.format(XpathHelper.DROPDOWN,label));
        click(by);
        boolean found = false;
        while (!found) {

            WaitStrategy.performExplicitWait(WaitType.VISIBLE, by).sendKeys(Keys.ARROW_DOWN);
            if (getText(by).trim().equalsIgnoreCase(value)) {
                found = true;
            }
        }
        WaitStrategy.performExplicitWait(WaitType.VISIBLE, by).sendKeys(Keys.ENTER);
    }

    public  String getText(By by) {
        String text = WaitStrategy.performExplicitWait(WaitType.PRESENCE, by).getText();
        LogUtils.log(LogType.PASS, text + " present.");
        return text;
    }

    public JSONArray getTableData(By headerXpath, By rowXpath)
    {

        List<WebElement> headers = WaitStrategy.performExplicitWaitOnListOfWebElements(WaitType.VISIBLE,headerXpath);
        List<WebElement> rows = WaitStrategy.performExplicitWaitOnListOfWebElements(WaitType.VISIBLE,rowXpath);

        List<JSONObject> rowDataList = new ArrayList<>();

        int rowCount = rows.size() / headers.size(); // Calculate total rows based on header count

        for (int i = 0; i < rowCount; i++)
        {
            Map<String, String> headerToRow = new HashMap<>();

            for (int j = 0; j < headers.size(); j++)
            {
                String header = headers.get(j).getText();
                String rowData = rows.get(j + (i * headers.size())).getText(); // Adjust index for multiple rows
                headerToRow.put(header, rowData);
            }

            JSONObject rowJSON = new JSONObject(headerToRow);
            rowDataList.add(rowJSON);
        }


        JSONArray jsonArray = new JSONArray(rowDataList);
        LogUtils.log(LogType.PASS,jsonArray.toString());
        return jsonArray;
    }


    public String getText(TopMenuItems topMenuItemName)
    {
        By text = By.xpath(String.format(XpathHelper.TOP_MENU, topMenuItemName.getName()));
        String topMenuItemText = WaitStrategy.performExplicitWait(WaitType.PRESENCE, text).getText();
        LogUtils.log(LogType.PASS, "Breadcrumb Title:: "+topMenuItemText);
        return topMenuItemText;
    }


    public String getPageHeader(TopMenuItems topMenuItemName)
    {

        By pageHeader = By.xpath(String.format(XpathHelper.BREADCRUMB_HEADER, topMenuItemName.getName()));
        String pageTitle = WaitStrategy.performExplicitWait(WaitType.PRESENCE, pageHeader).getText();
        LogUtils.log(LogType.PASS, "Page Title:: " +pageTitle);
        return pageTitle;
    }


    public  void sendHintKeys(String label, String value) {
        try {
            By inputField = By.xpath(String.format(XpathHelper.HINT_TEXT_BOX, label));
            WebElement wait = WaitStrategy.performExplicitWait(WaitType.PRESENCE, inputField);

            wait.sendKeys(value);

            for (int i = 0; i < 10; i++) {  // Limiting to 10 attempts to avoid infinite loop
                wait.sendKeys(Keys.ARROW_DOWN);
                List<WebElement> options = WaitStrategy.performExplicitWaitOnListOfWebElements(WaitType.VISIBLE, By.xpath("//div[@class='oxd-autocomplete-option']/span"));

                for (WebElement option : options) {
                    if (option.getText().trim().equalsIgnoreCase(value)) {
                        option.click();
                        LogUtils.log(LogType.PASS, label + ": " + value);
                        wait.sendKeys(Keys.ENTER);
                        return;
                    }
                }

                long startTime = System.currentTimeMillis();
                long currentTime;

                do {
                    currentTime = System.currentTimeMillis();
                } while ((currentTime - startTime) < (10 * 1000));  // Timeout after 10 seconds
            }

            LogUtils.log(LogType.FAIL, "Option not found for label: " + label + " and value: " + value);
        } catch (Exception e) {
            LogUtils.log(LogType.FAIL, "label or value is null. " + e.getMessage());
        }
    }

}
