package db.dao.excel;

import model.AnnualGDPelement;
import model.Country;
import model.Currency;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 21.03.2017.
 */
public class ExcelGDPDAO {

    public static List<AnnualGDPelement> getRealAnnualGDP(
                String fileName,
                Country country,
                Currency currency,
                int fromRow,
                int toRow,
                int yearCol,
                int realCol,
                int chainedCol,
                int basicYear)
    {
        List<AnnualGDPelement> result = new LinkedList<AnnualGDPelement>();
        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            in = new FileInputStream(fileName);
            wb = new HSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = wb.getSheetAt(0);
        for (int i = fromRow; i <= toRow; i++) {
            Row row = sheet.getRow(i);
            int year = (int) row.getCell(yearCol).getNumericCellValue();
            int real = (int) row.getCell(realCol).getNumericCellValue();
            int chained = (int) row.getCell(chainedCol).getNumericCellValue();
            AnnualGDPelement age = new AnnualGDPelement(
                    country,
                    year,
                    currency,
                    real,
                    chained,
                    basicYear
            );
            result.add(age);
        }
        return result;
    }

}
