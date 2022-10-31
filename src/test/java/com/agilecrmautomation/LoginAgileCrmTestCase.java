package com.agilecrmautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilecrm.pages.LoginPage;

public class LoginAgileCrmTestCase extends BaseClass {
    WebDriver driver;
    @Test(groups = {"regression", "sanity"})
    public void login() {
        driver=launchBrowser("chrome");
        driver.navigate().to("https://learnautomation.agilecrm.com");
        LoginPage loginPage = new LoginPage(driver);

        String filePath = System.getProperty("user.dir")+"\\TestData\\AutomationTestData.xlsx";

        ExcelUtil excelValues = new ExcelUtil();
        Object[][] data = excelValues.getExcelData(filePath, "LoginTestData");
        int rowLength = data.length;
        for (int i = 1; i <= rowLength - 1; i++) {
            String username = data[i][0].toString();
            String password = data[i][1].toString();
            loginPage.login(username, password);

        }

        // click on contact menu
        click(By.id("contactsmenu"), "Click on Contact Menu");
        //click on select all check box
        click(By.id("contacts-list-view-checkbox"), "Select All checkbox is selected");
        //click on delete button
        click(By.xpath("//button[text()='Delete']"), "Click on Delete Button");

    }


    @DataProvider(name = "getLoginTestData")
    public Object[][] getTestData() {
        String filePath = System.getProperty("user.dir")+"\\TestData\\AutomationTestData.xlsx";
        ExcelUtil excelValues = new ExcelUtil();
        return excelValues.getExcelData(filePath, "LoginTestData");
    }


    @Test(dataProvider = "getLoginTestData", groups = {"regression", "sanity"})
    public void loginTestCase(Object username, Object password) {
        System.out.println(username + " : " + password);
    }

    @Test(groups = {"regression", "sanity"})
    public void test() {
        String filePath = System.getProperty("user.dir")+"\\TestData\\AutomationTestData.xlsx";
        ExcelUtil excelValues = new ExcelUtil();
        excelValues.setDataInExcel(filePath, "EmpData", 0, 1, "Test");
    }

    @AfterClass(groups = {"regression", "sanity"})
    public void tearDown() {
        driver.close();
    }
}
