import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by vladimir on 16.08.16.
 */
public class Excel {

	public static void main(String[] args) throws IOException {
	readFromExcel("/home/vladimir/NewBangs1.xls");
	}

	public static void readFromExcel(String file) throws IOException {
		HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet myExcelSheet = myExcelBook.getSheet("Лист1");
		HSSFRow row = myExcelSheet.getRow(5);

		if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
			String name = row.getCell(0).getStringCellValue();
			System.out.println("name : " + name);
		}

		if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			Date birthdate = row.getCell(1).getDateCellValue();
			System.out.println("birthdate :" + birthdate);
		}

		myExcelBook.close();

	}

}
