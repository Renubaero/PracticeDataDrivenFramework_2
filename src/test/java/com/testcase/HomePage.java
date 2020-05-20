package com.testcase;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baseclass.BaseClass;
import com.util.ExcelReader;

public class HomePage extends BaseClass {

	@Test(priority = 1)
	public void homePage() {
		Assert.assertTrue(verifyElement(By.cssSelector(prop.getProperty("bankManagerBtn_CSS"))));
		click(By.cssSelector(prop.getProperty("bankManagerBtn_CSS")));
	}

	@Test(dataProvider = "getData", priority = 2)
	public void addCustomerPage(String fName, String lName, String pCode) throws IOException {
		System.out.println(fName);
		System.out.println(lName);
		System.out.println(pCode);
		Assert.assertTrue(verifyElement(By.cssSelector(prop.getProperty("addCustomerBtn_CSS"))));
		click(By.cssSelector(prop.getProperty("addCustomerBtn_CSS")));
		sendKey(By.cssSelector(prop.getProperty("firstNameTextBox_CSS")), fName);
		sendKey(By.cssSelector(prop.getProperty("lastNameTextBox_CSS")), lName);
		sendKey(By.cssSelector(prop.getProperty("postCodeTextBox_CSS")), pCode);
		click(By.cssSelector(prop.getProperty("addSubmitBtn_CSS")));
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		alert.accept();
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		ExcelReader excel = new ExcelReader();
		String sheetName = "addCustomer";
		int rowCount = excel.rowCount(sheetName);
		int cellCount = excel.cellCount(sheetName);
		System.out.println(rowCount);
		System.out.println(cellCount);
		Object[][] data = new Object[rowCount][cellCount + 1];

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j <= cellCount; j++) {
				data[i][j] = excel.cellData(sheetName, i + 1, j);
			}
		}
		return data;
	}
}
