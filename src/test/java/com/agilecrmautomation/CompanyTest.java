package com.agilecrmautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.agilecrm.pages.CompanyPage;
import com.agilecrm.pages.LoginPage;

public class CompanyTest extends BaseClass {
    WebDriver driver;

    @Test(groups = {"regression", "sanity"}, priority = 1)
    public void login() {
        driver=launchBrowser("chrome");
        driver.navigate().to("https://learnautomation.agilecrm.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("cst23@yopmail.com", "Test1234");
    }

    @Test(groups = {"regression", "sanity"}, priority = 2)
    public void addCompany() {

        click(By.id("companiesmenu"), "Clicked on Company menu");
        CompanyPage companyPage = new CompanyPage(driver);
        companyPage.addCompany();
		/*click(By.xpath("//button[contains(text(),'Add Company')]"), "Click on Add Company button");
		waitForElementToBeClickable(By.id("company_name"));
		driver.findElement(By.id("company_name")).sendKeys("Amazon_JmLK");
		click(By.id("continue-company"), "click on Continue Edit button");
		fluentWait(By.name("email-select"));
		Select select = new Select(driver.findElement(By.xpath("//div[contains(@class,'second')]/descendant::select[@name='email-select']")));
//		select.selectByIndex(1);
		List<WebElement> emailDropDownList = select.getOptions();
		for (WebElement element : emailDropDownList) {
			String text = element.getText(); // element.getText() System.out.println(text);
			if (text.equalsIgnoreCase("primary")) {
//				select.selectByValue(text);
				select.selectByVisibleText(text);
					System.out.println("This is not multiselect dropdown");
				}
				break;
			}*/
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}


