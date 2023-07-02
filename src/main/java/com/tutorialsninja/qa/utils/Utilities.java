package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class Utilities {
	
	public static XSSFWorkbook workbook;
	public static final int IMPLICIT_WAIT_TIME=15;
	public static final int PAGE_WAIT_TIME=15;

	
	public static String generateTimestamp()
	{
		Date dt=new Date();
		return "t"+dt.toString().replaceAll(" ", "_").replaceAll(":", "_")+"@gmail.com";
	}
	
	public static Object[][] getTestDataExcel(String sheetName) throws IOException
	{
		File excelfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		FileInputStream fis=new FileInputStream(excelfile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[rows][cols];
		for(int i=0;i<rows;i++)
		{
			XSSFRow row=sheet.getRow(i+1);
			for(int j=0;j<cols;j++)
			{
				XSSFCell cell=row.getCell(j);
				org.apache.poi.ss.usermodel.CellType cellType = cell.getCellType();
				
				switch(cellType)
				{
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
				}
			}
		}
		return data;
	
	}
	
	public static String captureScreenshot(WebDriver driver,String testName)
	{
		File scrScreenshots=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		
		try {
			FileHandler.copy(scrScreenshots, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}
}