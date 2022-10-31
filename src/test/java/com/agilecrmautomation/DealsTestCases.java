package com.agilecrmautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.agilecrm.pages.DealsPage;
import com.agilecrm.pages.LoginPage;

public class DealsTestCases extends BaseClass {
    WebDriver driver;
    @Test(groups = {"regression", "sanity"})
//	@Parameters({"browser","username", "password"})
    public void main() throws Exception {
        PropertyHandling prop = new PropertyHandling();
        String browser = prop.getValue("browser");
        String url = prop.getValue("agileCrmUrl");
        String username = prop.getValue("username");
        String password = prop.getValue("password");
        driver=launchBrowser(browser);
        driver.navigate().to(url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        click(By.id("dealsmenu"), "Click on Deals Menu");
        DealsPage dealsPage = new DealsPage(driver);
        dealsPage.changeDealsStatus("New", "Prospect");
    }

    @AfterClass(groups = {"regression", "sanity"})
    public void tearDown() {
        driver.close();
    }
}
