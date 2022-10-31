package com.agilecrmautomation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class AmazonApplianceSearch extends BaseClass {
    WebDriver driver;

    @BeforeGroups(groups = {"regression"})
    public void beforeGroup() {

        System.out.println("This is beforeGroup method");
    }

    @Test(groups = {"regression", "sanity"}, dependsOnGroups = {"regression"})
    @Parameters("browser")
    public void main(String browser) throws AWTException, InterruptedException {
        driver = launchBrowser(browser);
        driver.navigate().to("https://www.amazon.in/");
        WebElement customerService = driver
                .findElement(By.xpath("//div[@id='nav-xshop']/a[text()='Customer Service']"));
        Actions action = new Actions(driver);
        //right click on customer service link
        action.contextClick(customerService).build().perform();
        //select first option in right click pannel
        Robot rb = new Robot();
        rb.keyPress(KeyEvent.VK_DOWN);
        rb.keyRelease(KeyEvent.VK_DOWN);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        //get parent window id
        String parentWindowId = driver.getWindowHandle();
        System.out.println("Parent Window Id: " + parentWindowId);
        // get all window ids
        Set<String> allWindowIds = driver.getWindowHandles();
        //iterate set of all windows
        for (String windowId : allWindowIds) {
            //if window id is not parent then go inside if condition
            if (!windowId.equals(parentWindowId)) {

                System.out.println("Child window id: " + windowId);
                //switch to another window
                driver.switchTo().window(windowId);
                //print the title of page
                System.out.println(driver.getTitle());

            }
        }
        //switch back control to parent window
        driver.switchTo().window(parentWindowId);
//		driver.switchTo().defaultContent();
        //print the title of parent window
        System.out.println(driver.getTitle());

//		  rb.keyPress(KeyEvent.VK_DOWN); // rb.keyRelease(KeyEvent.VK_DOWN);

//		action.moveToElement(customerService).click().build().perform();
        /*
         * WebElement sourceElement=driver.findElement(By.xpath(
         * "//ul[@id='deals-by-paging-model-list']/descendant::div[@data='New'][1]"));
         *
         * WebElement
         * dstElement=driver.findElement(By.id("deals-by-paging-model-list"));
         *
         * action.dragAndDrop(sourceElement, dstElement).build().perform();
         */

        /*
         * selectDropdownValByText(driver.findElement(By.id("searchDropdownBox")),
         * "Appliances");
         * driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Washing Machine");
         * click(By.id("nav-search-submit-button"), "Click on search button");
         * List<WebElement> applianceNames =
         * driver.findElements(By.xpath("//div[@class='sg-row']/descendant::h2/a/span"))
         * ; List<WebElement> appliancePrices = driver .findElements(By.xpath(
         * "//div[@class='sg-row']/descendant::span[@class='a-price']")); for (int i =
         * 0; i < applianceNames.size(); i++) {
         *
         * String name = applianceNames.get(i).getText(); String price =
         * appliancePrices.get(i).getText(); System.out.println(name + " : " + price);
         *
         * }
         */
    }

    @AfterClass(groups = {"regression", "sanity"})
    public void tearDown() {
        driver.close();
    }
}
