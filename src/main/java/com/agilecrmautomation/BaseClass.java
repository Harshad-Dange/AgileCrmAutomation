package com.agilecrmautomation;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BaseClass {
	public static ExtentTest logger;
	public static WebDriver driver;

	// This will launch the browser based on the input parameter
	public static WebDriver launchBrowser(String browser) {
		switch (browser) {
		case "chrome":
//			System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();			
			 driver= new ChromeDriver(setChromeCapabilities());
			 return driver;
		case "edge":
//			System.setProperty("webdriver.edge.driver", "C:\\Tools\\edgedriver\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			return driver;
		case "mozila":
//			System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			return driver;
		default:
//			System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			return driver;
		}
	}
	//click on the element which is provided in the param 
	public static void click(By by,String msg) {		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();
		System.out.println(msg);
	}
	
	public static void waitForVisibilityOfElement(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));	
	}
	public static void waitForElementToBeClickable(By element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));	
	}
	
	public static void waitForElementToBePresent(By element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));	
	}
	
	public static void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public static void fluentWait(final By by) {
		Wait<WebDriver>  wait= new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(Exception.class);
		wait.until(driver -> {
			System.out.println("Waiting for element to be available....");
			return driver.findElement(by);
		});
	}
	
	public static void  selectDropdownValByIndex(WebElement element, int index) {
		Select select=new Select(element);
		select.selectByIndex(index);	
	}
	
	public static void  selectDropdownValByText(WebElement element, String text) {
		Select select=new Select(element);
		select.selectByVisibleText(text);	
	}
	
	public static void  selectDropdownValByValue(WebElement element, String val) {
		Select select=new Select(element);
		select.selectByValue(val);	
	}
	
	
	private static ChromeOptions setChromeCapabilities() {
		ChromeOptions option=new ChromeOptions();
		option.addArguments("start-maximized");
		option.setHeadless(false);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", "/directory/path");
		option.setExperimentalOption("prefs", prefs);		
		option.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
		return option;		
	}
	
	public void uploadFile(String filePath) {
		try {
			Thread.sleep(10000);
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\TestData\\FileUploadScript.exe "+filePath);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
	}

	public String takeScreenShot(ITestResult result, String reportPath){
		TakesScreenshot takeSc = (TakesScreenshot) driver;
		File screenShotfile = takeSc.getScreenshotAs(OutputType.FILE);

		String fileName = result.getName();
		// get project folder path
		String outputPath =reportPath+"//Screenshots//" + fileName + ".png";

		File ouputFile = new File(outputPath);

		try {
			FileUtils.copyFile(screenShotfile, ouputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  outputPath;
	}
}
