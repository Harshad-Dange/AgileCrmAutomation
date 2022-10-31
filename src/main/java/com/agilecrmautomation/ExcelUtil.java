package com.agilecrmautomation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	/**
	 * Method to get file extention based on the provided file path @author Harshad
	 */
	private String getFileExtention(String filePath) {
		// get the file path extention
		String extention = filePath.substring(filePath.indexOf("."));
		System.out.println(extention);
		return extention;
	}

	/**
	 * Method to get workbook instance based on the provided file path
	 * 
	 * @author Harshad
	 */
	private Workbook getWorkbookInstance(String filePath) throws IOException {
		Workbook wb;
		FileInputStream input = new FileInputStream(filePath);
		// based on the file extention, take the controle of workbook
		if (getFileExtention(filePath).equals(".xlsx")) {
			wb = new XSSFWorkbook(input);
		} else {
			wb = new HSSFWorkbook(input);
		}
		return wb;
	}

	/** method to get sheet referance from the workbook instance @author Harshad */
	private Sheet getSheet(String filePath, String sheetName) {
		Sheet sheet = null;
		try {
			Workbook wb = getWorkbookInstance(filePath);
			sheet = wb.getSheet(sheetName);
			/*
			 * wb.getSheetAt(0); wb.getSheetIndex(0);
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * @description This method will return the excel file data in two dimentional array that is
	 * Object[][] based on provided filepath and sheetname
	 * 
	 * @param filePath filepath in string format
	 * @param sheetName sheet name which should be accessed
	 */
	public Object[][] getExcelData(String filePath, String sheetName) {
		Sheet sheet = getSheet(filePath, sheetName);
		int totalRow = sheet.getPhysicalNumberOfRows();
		int totalColumn = sheet.getRow(0).getPhysicalNumberOfCells();
		return getCellValue(sheet, totalRow, totalColumn);
	}

	/**
	 * this method will return the value from each cell of excel file
	 * 
	 * @author Harshad
	 * @param sheet        --> this is sheet referance
	 * @param totalRows    -->. total number of rows that sheet contains
	 * @param totalColumns --> total number of coloumns that sheet contains
	 */
	private Object[][] getCellValue(Sheet sheet, int totalRows, int totalColumns) {
		Object[][] value = new Object[totalRows][totalColumns];
		for (int i = 1; i < totalRows; i++) {
			// take the control on rows based on the i value
			Row row = sheet.getRow(i);

			for (int j = 0; j < totalColumns; j++) {
				// take the cotrol of cell using row referance
				Cell cell = row.getCell(j);

				CellType type = cell.getCellType();

				switch (type) {

				case STRING:
					value[i - 1][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					value[i - 1][j] = cell.getNumericCellValue();
					break;
				case BOOLEAN:
					value[i - 1][j] = cell.getBooleanCellValue();
					break;
				case _NONE:
					value[i - 1][j] = null;
					break;
				case BLANK:
					value[i - 1][j] = null;
					break;
				default:
					value[i - 1][j] = null;
				}
			}
		}
		return value;
	}

	/** Method to add data into the excel file 
	 * @param filePath in string format
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param value
	 * @author Harshad
	 * */
	public void setDataInExcel(String filePath, String sheetName, int rowNum, int cellNum, Object value) {
		try {
			Workbook wb = getWorkbookInstance(filePath);
			Sheet sheet;
			// get the sheet details if it is present in the workbook
			if (wb.getSheet(sheetName) != null) {
				sheet = wb.getSheet(sheetName);

			} else {
				// create new sheet in the workbook
				sheet = wb.createSheet(sheetName);
			}
			// create row if not present else get the row control
			Row row;
			if (sheet.getRow(rowNum) != null) {
				row = sheet.getRow(rowNum);
			} else {
				row = sheet.createRow(rowNum);
			}
			// create cell if not present else get the cell control
			Cell cell;
			if (row.getCell(cellNum) != null) {
				cell = row.getCell(cellNum);
			} else {
				cell = row.createCell(cellNum);
			}

			setCellValue(cell, value);
			FileOutputStream output=new FileOutputStream(filePath);
			wb.write(output);
			wb.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void setCellValue(Cell cell, Object val) {

		if (val instanceof Integer) {
			cell.setCellValue((int) val);
		} else if (val instanceof String) {
			cell.setCellValue(val.toString());
		} else if (val instanceof Boolean) {
			cell.setCellValue((boolean) val);

		}

	}
}
