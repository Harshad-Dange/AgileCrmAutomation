package com.agilecrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.agilecrmautomation.BaseClass;

public class LoginPage extends BaseClass {
	WebDriver driver;
	By email= By.name("email");
	By password=By.name("password");
	By submitButton=By.xpath("//input[@type='submit']");

	public LoginPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void login(String username,String password) {
		fluentWait(By.name("email"));
		WebElement uname=driver.findElement(email);
		uname.clear();
		uname.sendKeys(username);
		WebElement pwd=driver.findElement(this.password);
		pwd.clear();
		pwd.sendKeys(password);
		WebElement signIn=driver.findElement(submitButton);
		signIn.click();		
		/*
		 * if(driver.findElement(By.xpath("//a[@class='close']/parent::*")).isEnabled())
		 * { String
		 * validationMsg=driver.findElement(By.xpath("//a[@class='close']/parent::*")).
		 * getText();
		 * if(validationMsg.equals("We have not been able to locate any user "+
		 * username)) {
		 * 
		 * }
		 * 
		 * System.out.println(validationMsg); System.out.println("Invalid username: "+
		 * username+ " or password: "+ password); }else { ///logout code
		 * 
		 * }
		 */	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
