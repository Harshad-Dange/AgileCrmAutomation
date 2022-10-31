package com.agilecrmautomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestCase2 extends BaseClass {
    WebDriver driver;
    @Test(groups = {"regression", "sanity"})
    public void test1() {
        System.out.println("This is beforeTest method..");
        logger.info("starting the chrome browser");
        driver=launchBrowser("chrome");
        logger.info("navigate to the google website");
        driver.get("https://google.com");
        logger.info("The excution started for test1 method");
        System.out.println("This is test1 method");
    }

    @Test(groups = {"regression", "sanity"})
    public void test2() {
        logger.info("Execution started for test2 method");
        System.out.println("This is test2 method");
        System.out.println(driver.getTitle());
        Assert.fail("Test2 failed");
    }

    @Test(dependsOnMethods = "test2", groups = {"regression", "sanity"})
    public void test3() {
        logger.info("Execution started for test3 method");

    }


    @Test(groups = {"regression", "sanity"})
    public void sample() {
        //implementation of Math interface using anynomus class
        /*
         * Math math =new Math() {
         *
         * @Override public void add() { System.out.println("add method"); } };
         *
         * math.add();
         */


    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
