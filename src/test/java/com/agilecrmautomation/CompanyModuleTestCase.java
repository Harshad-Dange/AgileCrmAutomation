package com.agilecrmautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.agilecrm.pages.CompanyPage;
import com.agilecrm.pages.LoginPage;

public class CompanyModuleTestCase extends BaseClass {
    WebDriver driver;
    public CompanyModuleTestCase() {

        System.out.println("This is a constructor...");
    }


    @BeforeTest
    public void beforeTest() {
        System.out.println("This is before test class method");

    }

    @AfterTest
    public void aftertest() {
        System.out.println("This is after test class method");


    }

    @BeforeClass(groups = {"regression", "sanity"})
    public void beforeClass() {
        System.out.println("This is before class method");
        driver=launchBrowser("chrome");
        driver.navigate().to("https://learnautomation.agilecrm.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("cst25@yopmail.com", "Test1234");
    }

    @AfterClass(groups = {"regression", "sanity"})
    public void afterClass() {
        System.out.println("This is after class method");
        driver.quit();
    }


    @BeforeMethod
    public void beforeMethod() {
        System.out.println("This is a before method");

    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("This is after method");
        //logout from application

    }

    @Test(groups = {"regression", "sanity"})
    public void verifyAddCompany() {
        click(By.id("companiesmenu"), "Clicked on Company menu");
        CompanyPage companyPage = new CompanyPage(driver);
        companyPage.addCompany();
        //verify company
    }

    @Test(groups = {"regression", "sanity"})
    public void verifyUpdateCompany() {
        System.out.println("This is a update company method");

    }
}
