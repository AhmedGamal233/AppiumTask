package helper;


import logging.LoggingHandling;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import static logging.LoggingHandling.logger;

/**
 * this class for generic action which is needed in script like:
 * 1-take screenshot when the test cases fail
 * 2-choose test data file with specific format
 * 3-retrieve data from those test data and send them to data provider which in turn send them to test method
 */

public class Helper {
    private static int DataRow ;
	private static int DataCol ;
	private static Object[][] data;

	/**
	 *convert DataSizeRow from String to Integer then assign int value to DataRow
	 * @param DataSizeRow to set DataSizeRow
	 */
	public static void setRow(String DataSizeRow)
	{
		Integer Row =Integer.valueOf(DataSizeRow);
		DataRow=Row.intValue();
	}

	/**
	 *convert DataSizeCol from String to Integer then assign int value to DataCol
	 * @param DataSizeCol to set DataSizeCol
	 */
	public static void setCol(String DataSizeCol)
	{
		Integer Col =Integer.valueOf(DataSizeCol);
		DataCol=Col.intValue();
	}

	/**
	 *
	 *  Method to take screenshot when the test cases fail
	 * @param browserObject take browserObject from BaseTest Class
	 * @param screenShotName take screenShotName from failed test method name
	 */
	public static void captureScreenshot(WebDriver browserObject, String screenShotName) {
		Path dest = Paths.get("src\\test\\Screenshots", screenShotName + ".png");
		try {
			Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot) browserObject).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot" + e.getMessage());
		}
	}

	/**
	 * this method responsible for:choosing spesific file with specific format
	 * @param fileExtension to pass fileExtension from dataProvider method which came from testngSuite.xml
	 * @param fileName to pass fileName from dataProvider method which came from testngSuite.xml
	 * @return 2d array from type object to receive the data in data provider
	 */
	public static Object[][] chooseFile(String fileExtension ,String fileName)  {
		data = new Object[DataRow][DataCol];
		if (fileExtension.equalsIgnoreCase(".properties") && fileName.equalsIgnoreCase(fileName)) {
			String filePath = "Configs/Data/"+fileName + fileExtension;
			try {
			data = readFromPropertyFile(filePath);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("Error");
				LoggingHandling.logError(e);
				System.out.println("Please Check your file name or file extension ");
			}
		} else if (fileExtension.equalsIgnoreCase(".xlsx") && fileName.equalsIgnoreCase(fileName)) {
			String filePath = "Configs/Data/"+fileName + fileExtension;
			try {
				data = readFromExcelFile(filePath);
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("Error");
				LoggingHandling.logError(e);
				System.out.println("Please Check your file name or file extension ");
			}
		} else if (fileExtension.equalsIgnoreCase(".csv") && fileName.equalsIgnoreCase(fileName)) {
		String filePath = "Configs/Data/"+fileName + fileExtension;
			try {
				data = readFromCSVFile(filePath);
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("Error");
				LoggingHandling.logError(e);
				System.out.println("Please Check your file name or file extension ");
			}
		}

		return data;
	}

	/**
	 *this method responsible for: read data from Property file
	 * @param filepath to pass filepath from chooseFile method
	 * @return 2d array from type object to receive the data in data provider
	 */
	public static Object[][] readFromPropertyFile(String filepath) {

		File file = new File(filepath);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			System.out.println("file not found");
			e1.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(reader);
		} catch (IOException e) {
			System.out.println("file not read");
			e.printStackTrace();
		}

		Enumeration names = prop.propertyNames();

		ArrayList<String> listOfNames = new ArrayList<>();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			listOfNames.add(name);
			System.out.println(name);

		}

		for (int i = 0; i < data.length; i++) {

			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = prop.getProperty(listOfNames.get(j));
			}
		}

		return data;
	}

	/**
	 *this method responsible for: read data from Excel file
	 * @param filePath to pass filepath from chooseFile method
	 * @return 2d array from type object to receive the data in data provider
	 * @throws IOException if file is not found
	 */
	public static Object[][] readFromExcelFile(String filePath) throws IOException {

		File srcFile = new File(filePath);


		FileInputStream fis = new FileInputStream(srcFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int TotalNumberOfRows = (sheet.getLastRowNum()+1);
		int TotalNumberOfCols = data[0].length;

		String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols];
		ArrayList<String> listOfValues = new ArrayList<>();
		for (int i = 0; i < TotalNumberOfRows; i++) {

			for (int j = 0; j < TotalNumberOfCols; j++) {
				XSSFRow row = sheet.getRow(i);
				String value="";
				if(row.getCell(j).getCellTypeEnum()== CellType.STRING)
				{ value= row.getCell(j).getStringCellValue();}
				else if(row.getCell(j).getCellTypeEnum()==CellType.NUMERIC)
				{value = String.valueOf((int)row.getCell(j).getNumericCellValue());}



					listOfValues.add(value);
					arrayExcelData[i][j] = listOfValues.get(j);

				System.out.println(listOfValues.get(j));
			}
		}

		wb.close();
		return arrayExcelData;
	}

	/**
	 * this method responsible for: read data from CSV file
	 * @param filepath to pass filepath from chooseFile method
	 * @return 2d array from type object to receive the data in data provider
	 * @throws IOException if file is not found
	 */
	public static Object[][] readFromCSVFile(String filepath) throws IOException {
		String csvCell;
		String[] values = new String[0];
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		while ((csvCell = br.readLine()) != null)   //returns a Boolean value
		{
			 values = csvCell.split(",");// use comma as separator

		}

		for (int i = 0; i < data.length; i++) {

			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = values[j];
				System.out.println(data[i][j].toString());
			}
		}

		return data;
	}


}
