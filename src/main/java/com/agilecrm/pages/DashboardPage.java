package com.agilecrm.pages;

import java.time.LocalDateTime;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.agilecrmautomation.BaseClass;

public class DashboardPage extends BaseClass {
	WebDriver driver;

	@FindBy(id = "calendarmenu")
	public WebElement calendarMenu;

	@FindBy(xpath = "//div[@id='calendar_event']/div//tbody/tr")
	List<WebElement> totalWeeks;

	public DashboardPage(WebDriver driver) {
		// initialize the current class webelement which are declared
		// using @findBy annotation
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void createEvent() {
		calendarMenu.click();
		int totalRows = totalWeeks.size();
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 1; j <= 7; j++) {
				String dayXpath = "//div[@id='calendar_event']/div//tbody/tr[" + i + "]/td[" + j + "]";
				WebElement weekDay = driver.findElement(By.xpath(dayXpath));
				if (!weekDay.getAttribute("class").contains("fc-other-month")) {
					WebElement weekDays = driver.findElement(By.xpath(dayXpath + "/div/div[1]"));
					String day = weekDays.getText();
					String currentDay = String.valueOf(LocalDateTime.now().getDayOfMonth());
					if (day.equals(currentDay)) {
						weekDays.click();
						break;
					}
				}
			}
		}
	}
}
