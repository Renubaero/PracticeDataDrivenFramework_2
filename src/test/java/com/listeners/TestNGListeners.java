package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.util.ExtentManager;

public class TestNGListeners implements ITestListener {

	static String fileName = "ExtentReport";
	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName+".html");
	public static ExtentTest test;
	
	public void onTestStart(ITestResult result) {
		
		test=extent.createTest(result.getInstanceName()+" @TestCase - "+result.getMethod().getMethodName());
	}
	public void onTestSuccess(ITestResult result) {
		
		String logText = result.getMethod().getMethodName()+" - PASS";
		Markup m= MarkupHelper.createLabel(logText, ExtentColor.PINK);
		test.pass(m);
	  }
	public void onFinish(ITestContext context) {
		if(extent != null) {
			extent.flush();
		}
	  }
}
