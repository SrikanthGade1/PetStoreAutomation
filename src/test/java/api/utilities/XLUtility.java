package api.utilities;

// Below two packages come from java
import java.io.FileInputStream;
import java.io.IOException;

// Below packages come from apache poi
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	private String path;

	// We created below constructor because as soon as you create an object, you should send path of the excel file
	public XLUtility(String path) {
		this.path = path;
	}

	// Gets how many rows are there in the excel sheet
	public int getRowCount(String sheetName) throws IOException {
		FileInputStream fi = new FileInputStream(path);
		Workbook workbook = new XSSFWorkbook(fi);
		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
	}

	// Based on the row number it would return number of cells present in that particular row
	public int getCellCount(String sheetName, int rownum) throws IOException {
		FileInputStream fi = new FileInputStream(path);
		Workbook workbook = new XSSFWorkbook(fi);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}

	// This is the most important method
	// Based on the sheet name, row number and cell count, it would return the data
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		FileInputStream fi = new FileInputStream(path);
		Workbook workbook = new XSSFWorkbook(fi);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(colnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		} finally {
			workbook.close();
			fi.close();
		}
		return data;
	}
}