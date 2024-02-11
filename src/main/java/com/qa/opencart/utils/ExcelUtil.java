package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static Workbook wk;

	private static Sheet sheet;

	private static final String USER_DATA_EXCEL_FILE_PATH = "./src/test/resource/testdata/UserRegisterData.xlsx";

	public static Object[][] getDatafromExcel(String sheetName) {

		System.out.println("Sheet from Data Is coming is: " + sheetName);

		Object[][] obj = null;

		try {
			FileInputStream fp = new FileInputStream(USER_DATA_EXCEL_FILE_PATH);
			wk = WorkbookFactory.create(fp);
			sheet = wk.getSheet(sheetName);

			obj = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

					obj[i][j] = sheet.getRow(i + 1).getCell(j).toString();

				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return obj;
	}

}
