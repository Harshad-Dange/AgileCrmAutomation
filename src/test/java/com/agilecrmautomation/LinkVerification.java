package com.agilecrmautomation;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LinkVerification extends BaseClass {
	WebDriver driver;
	@Test(groups = {"regression","sanity"})
	@Parameters("browser")
	public void main(String browser) {
		driver=launchBrowser(browser);
		driver.navigate().to("https://www.amazon.in/");
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		for (WebElement e : elements) {
			String link = e.getAttribute("href");
			if (link != null && link.startsWith("http")) {
				URL url;
				try {
					url = new URL(link);
					URLConnection urlConnection = url.openConnection();
					HttpsURLConnection connection = (HttpsURLConnection) urlConnection;
					connection.connect();
					int statusCode = connection.getResponseCode();
					if (statusCode != 200) {
						System.out.println(statusCode + " Url: " + link);
					}
					connection.disconnect();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	@AfterClass(groups = {"regression","sanity"})
	public void tearDown(){
		driver.close();
	}
}
