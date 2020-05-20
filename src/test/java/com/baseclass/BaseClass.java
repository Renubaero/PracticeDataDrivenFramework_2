package com.baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Properties prop;
	public static WebDriver driver;
	public static WebDriverWait wait;
//	public static String path = "//src//test//resources//excel//data.xlsx";
//	public ExcelReader excel;

	@BeforeSuite
	public void setUp() throws IOException {
		//excel=new ExcelReader(path);
		prop = new Properties();
		FileInputStream config = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//properties//config.properties");
		prop.load(config);
		FileInputStream OR = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//properties//OR.properties");
		prop.load(OR);
		System.out.println(prop.getProperty("url"));
		if (prop.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5);
		driver.get(prop.getProperty("url"));
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public Boolean verifyElement(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
			return false;
		}
	}

	public void click(By by) {
		try {
			driver.findElement(by).click();
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		}

	}

	public void sendKey(By by, String text) {
		try {
			driver.findElement(by).sendKeys(text);
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public Boolean verifyElements(By by) {

		List<WebElement> list = driver.findElements(by);
		// System.out.println(list.size());
		if (list.size() > 0) {
			for (WebElement element : list) {
				Assert.assertTrue(element.isDisplayed());
			}
			return true;
		} else {
			return false;
		}
	}

}
