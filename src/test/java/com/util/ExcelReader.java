package com.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader{

	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	//String path;
	String path = "//src//test//resources//excel//data.xlsx";

	public ExcelReader() throws IOException {
		//this.path=path;
		fis = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fis);
		fis.close();
	}

	public int rowCount(String sheetName) {

		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		return rowCount;
	}

	public int cellCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		int cellCount = row.getLastCellNum() - 1;
		return cellCount;
	}

	public String cellData(String sheetName, int rows, int cols) {

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rows);
		cell = row.getCell(cols);
		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return "";
		}
	}
}
