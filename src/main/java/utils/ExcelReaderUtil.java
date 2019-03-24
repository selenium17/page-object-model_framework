package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {
	
	
	public Object[][] readExcel() throws IOException {
		
//		1) Locate the excel file
		PropertyFileReader pfr = new PropertyFileReader(); 		
		String excelFileName = pfr.readProperty("testdata.excel.path");

//		2) Open the excel workbook
		FileInputStream fis = new FileInputStream(excelFileName);
//	
//		3) Read the excel file
//			Workbook 
//			Worksheet
//			Each worksheet will rows and columns
//			Each row will have columns
//		    What class objects should be created?
//			what are the methods that need to be called?
		
		//class names provided by POI
		//Workbook -> XSSFWorkbook
		//Worksheet -> XSSFSheet
		//Row -> XSSFRow
		//Column -> XSSFCell
		
		//Workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet("logindata");
		
		//I want to store the data which I am reading into Object[][] array
		int rowCount = sheet.getLastRowNum() + 1;
		int columnCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rowCount][columnCount];
		
		//read the entire excel data - every row and every column
		for(int i=0;i<rowCount;i++) {
			XSSFRow row = sheet.getRow(i);
			for(int j=0;j<columnCount;j++) {
				XSSFCell cell = row.getCell(j);
				data[i][j] = cell.getStringCellValue();
				
				try {
					String value = cell.getStringCellValue();
					System.out.println(value);
				}catch(NullPointerException npee) {
					System.out.println("cell" + j + " data is empty");					
				}				
			}
		}
		
		
		workbook.close();
		fis.close();
		
		System.out.println("Rows Count:" + data.length);
		System.out.println("Column count:" + data[0].length);
		
		return data;

	}

}
