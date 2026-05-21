package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MyXLSReader {

    public String filepath;
    FileInputStream fis = null;
    Workbook workbook = null;
    Sheet sheet = null;
    Row row = null;
    Cell cell = null;
    public FileOutputStream fileOut = null;
    String fileExtension = null;

    public MyXLSReader(String filepath) {

        this.filepath = filepath;

        try {

            // FIX 1: remove double path issue
            fis = new FileInputStream(filepath);

            // FIX 2: correct extension handling
            fileExtension = filepath.substring(filepath.lastIndexOf("."));

            if (fileExtension.equalsIgnoreCase(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (fileExtension.equalsIgnoreCase(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new RuntimeException("Invalid file type: " + fileExtension);
            }

            sheet = workbook.getSheetAt(0);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load Excel file: " + filepath);
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ================= GET CELL DATA (STRING COLUMN NAME) =================
    public String getCellData(String sheetname, String colName, int rowNum) {
        try {

            if (rowNum <= 0) return "";

            int sheetIndex = workbook.getSheetIndex(sheetname);
            if (sheetIndex == -1) return "";

            sheet = workbook.getSheetAt(sheetIndex);

            row = sheet.getRow(0);
            int colNum = -1;

            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName)) {
                    colNum = i;
                    break;
                }
            }

            if (colNum == -1) return "";

            row = sheet.getRow(rowNum - 1);
            if (row == null) return "";

            cell = row.getCell(colNum);
            if (cell == null) return "";

            return getCellValue(cell);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // ================= GET CELL DATA (INDEX) =================
    public String getCellData(String sheetname, int colNum, int rowNum) {
        try {

            int sheetIndex = workbook.getSheetIndex(sheetname);
            if (sheetIndex == -1) return "";

            sheet = workbook.getSheetAt(sheetIndex);
            row = sheet.getRow(rowNum - 1);

            if (row == null) return "";

            cell = row.getCell(colNum - 1);
            if (cell == null) return "";

            return getCellValue(cell);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // ================= COMMON CELL VALUE HANDLER =================
    private String getCellValue(Cell cell) {

        if (cell == null) return "";

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
            case FORMULA:

                if (DateUtil.isCellDateFormatted(cell)) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateUtil.getJavaDate(cell.getNumericCellValue()));

                    return cal.get(Calendar.DAY_OF_MONTH) + "/"
                            + (cal.get(Calendar.MONTH) + 1) + "/"
                            + cal.get(Calendar.YEAR);
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case BLANK:
            default:
                return "";
        }
    }
}