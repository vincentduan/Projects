package com.vincent;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 从Excel中读取数据
 * 
 * @author iie4b
 *
 */
public class ExcelDemo {
	static XSSFRow row;
	
	/**
	 * NGO数据导入
	 * @throws Exception
	 */
	public void readDataNGO() throws Exception {
		int i = 0;
		String excelName = "NGO-bycity";
		FileInputStream fis = new FileInputStream(new File("D:\\20170228\\NGO\\巴-NGO-" + excelName +".xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		for(int j = 0; j<workbook.getNumberOfSheets(); j++){
			XSSFSheet spreadsheet = workbook.getSheetAt(j);
			Iterator<Row> rowIterator = spreadsheet.iterator();
			String name = "";
			String establish = "";
			String address = "";
			String comments = "";
			//第一行为表头
			rowIterator.next();
			while (rowIterator.hasNext()) {
				row = (XSSFRow) rowIterator.next();
				name = (row.getCell(0) + "").replaceAll(",", ";");
				establish = row.getCell(1) + "";
				address = (row.getCell(2) + "").replaceAll(",", ";");
				comments = (row.getCell(3) + "").replaceAll(",", ";");
				System.out.println((++i + "," + name + "," + excelName + ","
						+ spreadsheet.getSheetName() + "," + establish + ","
						+ address + "," + comments).replaceAll("\r|\n", " "));
			}
		}
		fis.close();
	}
	
	 
	
	public static void main(String[] args) throws Exception {
		App app = new App();
		app.readDataNGO();
	}
}