package com.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports createInstance(String fileName) {
		
		
		ExtentHtmlReporter html=new ExtentHtmlReporter(fileName);
		html.config().setEncoding("utf-8");
		html.config().setDocumentTitle("Automation Report");
		html.config().setReportName("Extent Report");
		html.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(html);
		
		//config 
		
		extent.setSystemInfo("Application Name", "Banking Sector");
		extent.setSystemInfo("Author Name", "Renub");
		extent.setSystemInfo("Environment", "Testing");
		return extent;
	}

}
