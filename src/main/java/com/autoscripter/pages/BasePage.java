package com.autoscripter.pages;

import com.automatrix.enums.WaitType;
import com.automatrix.enums.loggers.LogType;
import com.automatrix.loggers.LogUtils;
import com.automatrix.waitstrategy.WaitStrategy;
import com.autoscripter.utils.XpathHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePage {


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



    public void clickLink(By xPath)
    {
        WaitStrategy.performExplicitWait(WaitType.CLICKABLE,xPath).click();
    }

    public static void click(By by) {
        WaitStrategy.performExplicitWait(WaitType.CLICKABLE, by).click();
        LogUtils.log(LogType.PASS, "Button is clicked.");

    }

    public static void select(String label, String value) {
        By by = By.xpath(String.format(XpathHelper.DROPDOWN,label));
        BasePage.click(by);
        boolean found = false;
        while (!found) {

            WaitStrategy.performExplicitWait(WaitType.VISIBLE, by).sendKeys(Keys.ARROW_DOWN);
            if (BasePage.getText(by).trim().equalsIgnoreCase(value)) {
                found = true;
            }
        }
        WaitStrategy.performExplicitWait(WaitType.VISIBLE, by).sendKeys(Keys.ENTER);
    }

    public static String getText(By by) {
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
}
