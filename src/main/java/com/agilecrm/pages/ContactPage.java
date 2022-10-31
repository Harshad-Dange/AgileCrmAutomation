package com.agilecrm.pages;

import org.openqa.selenium.By;

import com.agilecrmautomation.BaseClass;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BaseClass{
	WebDriver driver;
	String firstName;
	String lastName;
	String emailId;
	String mobNum;
	
	public void addContact() {
		firstName="abc";
		lastName="xyz";
		emailId="test@ac.com";
		mobNum="3245363636474";
		click(By.id("contactsmenu"), "Click on Contact Menu");
		click(By.id("AddContact"), "Click on Add Contact button");
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		click(By.id("SaveButton"), "Click on Save button");
	}
	
	public void searchContact() {
		
	}
	
	public void deleteContacts() {
		
	}
	public void updateContact() {
		
	}

}
