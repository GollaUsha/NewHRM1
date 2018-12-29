package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	static Workbook wb;

	public ExcelFileUtil() throws  IOException, EncryptedDocumentException, InvalidFormatException{
		FileInputStream f = new FileInputStream("./TestInput/InputSheet.xlsx");
		wb = WorkbookFactory.create(f);
	}

	public  int rowCount(String sheetname) {
		return wb.getSheet(sheetname).getLastRowNum();
	}

	public  int colCount(String sheetname, int rowNum) {
		return wb.getSheet(sheetname).getRow(rowNum).getLastCellNum();
	}

	public  String getData(String sheetname, int rowNum, int colNum) {
		String celldata = "";
		if (wb.getSheet(sheetname).getRow(rowNum).getCell(colNum).getCellType() ==Cell.CELL_TYPE_NUMERIC) {
			int data = (int) (wb.getSheet(sheetname).getRow(rowNum).getCell(colNum).getNumericCellValue());
			System.out.println(data);
			celldata = String.valueOf(data);
		} else {
			celldata = wb.getSheet(sheetname).getRow(rowNum).getCell(colNum).getStringCellValue();
		}
		return celldata;
	}

	public  void setData(String sheetname, int rowNum, int ColNum, String status) throws Throwable {
		Sheet sh = wb.getSheet(sheetname);
		Row rw = sh.getRow(rowNum);
		Cell cell = rw.createCell(ColNum);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rw.getCell(ColNum).setCellStyle(style);
		}
		if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rw.getCell(ColNum).setCellStyle(style);
		}
		
		if(status.equalsIgnoreCase("Not Executed"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rw.getCell(ColNum).setCellStyle(style);
		}
		
		FileOutputStream fos=new FileOutputStream("./TestOutput/OutputSheet.xlsx");
		wb.write(fos);
		fos.close();
		
	}

}
