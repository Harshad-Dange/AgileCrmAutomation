package com.agilecrmautomation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.agilecrm.pages.DashboardPage;
import com.agilecrm.pages.LoginPage;

public class DashboardTestCases extends BaseClass {
    WebDriver driver;
    @BeforeClass(groups = {"regression", "sanity"})
    public void setup() {
        PropertyHandling prop = new PropertyHandling();
        driver=launchBrowser("chrome");
        driver.navigate().to(prop.getValue("agileCrmUrl"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(prop.getValue("username"), prop.getValue("password"));
    }

    @Test(groups = {"regression", "sanity"})
    public void uploadDoc() {
        driver.findElement(By.id("documentsmenu")).click();
        click(By.xpath("//a[contains(text(),'Add Document')]"), "click on Add Document Button");
        driver.findElement(By.xpath("//span[text()='Computer']")).click();
        uploadFile(System.getProperty("user.dir") + "\\TestData\\FileUploadScript.exe");
    }

    @Test(groups = {"regression", "sanity"})
    public void verifyEvent() {
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.createEvent();
    }
	@AfterClass(groups = {"regression", "sanity"})
	public void tearDown(){
		driver.close();
	}
}
