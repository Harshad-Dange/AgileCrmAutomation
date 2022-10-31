package com.agilecrmautomation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AlertExamples extends BaseClass {
	WebDriver driver;
	@Test(groups= {"regression"})
	@Parameters("browser")
	public void main(String browser) {
		
		driver=launchBrowser(browser);
		driver.get("https://www.amazon.in");
	
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)"); //scroll down by 700 pxl
		
		js.executeScript("window.scrollBy(0,-700)");//scroll up by 700 pxl
		
		
		/*
		 * Alert alert=driver.switchTo().alert();
		 * alert.sendKeys("This is a sample text.."); alert.accept(); String
		 * alerttext=alert.getText(); System.out.println(alerttext);
		 */
	}
	@AfterClass(groups = {"regression", "sanity"})
	public void tearDown(){
		driver.close();
	}

}
