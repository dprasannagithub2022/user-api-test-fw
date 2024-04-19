package com.apex.samples.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ApexXlsUtilities {

//	private String fileName = "PostData_excel_file.xls";
//	private String sheetName = "";
	private WritableWorkbook writableWorkbook;
	private int rowCount;
	private Workbook wb;

	// assigns checks if file exists or not, both cases we assign it to a
	// WritableWorkbook // object so that we can write to it.
	private WritableWorkbook assignWorkBook(String fileName) throws IOException, BiffException {

		
		
		// File f = new File(System.getProperty("user.dir") +"\\"+fileName);
		File inp = new File(fileName);
		try {
			wb = Workbook.getWorkbook(inp);
			return Workbook.createWorkbook(inp, wb);
		} catch (FileNotFoundException e) {
			return Workbook.createWorkbook(inp); // Create a new one
		}

	}

	public int getRowCount() {
		return rowCount;
	}

	

	public void writeRow(String fileName, List<User> userLst, String tableName, String sheetName)
			throws WriteException, IOException, BiffException {
		WritableWorkbook writableWorkbook = assignWorkBook(fileName);
//		this.sheetName = sheetName;
		WritableSheet excelSheet;
		if (writableWorkbook.getNumberOfSheets() == 0) {
			excelSheet = writableWorkbook.createSheet(sheetName, 0);
		} else {
			excelSheet = writableWorkbook.getSheet(sheetName);
		}
		rowCount = excelSheet.getRows();
		int colCount = 0;
		for (User user : userLst) {
			if (colCount == 0) {
				// have to add string objects only.
				Label label = new Label(colCount++, rowCount, tableName);
				excelSheet.addCell(label);
				
			} else {
//				Label label = new Label(colCount++, rowCount, user);
//				excelSheet.addCell(label);
			}
		}
		Label label = new Label(colCount, rowCount + 1, tableName);
		excelSheet.addCell(label);
		if (wb != null) {
			wb.close();
		}
		writableWorkbook.write();
		writableWorkbook.close(); // everytime save it.
	}

	public void writeRows(String fileName, List<List<String>> userLst, String tableName, String sheetName)
			throws WriteException, IOException, BiffException {
		WritableWorkbook writableWorkbook = assignWorkBook(fileName);
//		this.sheetName = sheetName;
		WritableSheet excelSheet;
		if (writableWorkbook.getNumberOfSheets() == 0) {
			excelSheet = writableWorkbook.createSheet(sheetName, 0);
		} else {
			excelSheet = writableWorkbook.getSheet(sheetName);
		}
		rowCount = excelSheet.getRows();
		int rowCount = this.rowCount;
		int colCount = 0;
		for (List<String> user : userLst) {
			colCount = 0;
			if (rowCount == 0) {
				// have to add string objects only.
				Label label = new Label(colCount++, rowCount, tableName);
				excelSheet.addCell(label);
				for(String field : user) {
					label = new Label(colCount++, rowCount, field);
					excelSheet.addCell(label);
				}
			}
			else {
				colCount++;
				for(String field : user) {
					Label label = new Label(colCount++, rowCount, field);
					excelSheet.addCell(label);
				}
			}
			rowCount++;
		}
		Label label = new Label(colCount, rowCount + 1, tableName);
		excelSheet.addCell(label);
		if (wb != null) {
			wb.close();
		}
		writableWorkbook.write();
		writableWorkbook.close(); // everytime save it.
	}

	public static String[][] getTableArray(String xlFilePath, String sheetName, String tableName) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			int startRow, startCol, endRow, endCol, ci, cj;
			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();
			endCol = tableEnd.getColumn();
			System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", " + "startCol=" + startCol
					+ ", endCol=" + endCol);
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
			ci = 0;

			for (int i = startRow + 1; i < endRow; i++, ci++) {
				cj = 0;
				for (int j = startCol + 1; j < endCol; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getTableArray()");
			e.printStackTrace();
		}
		return (tabArray);
	}

}
