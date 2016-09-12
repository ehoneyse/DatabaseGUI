import java.io.File;
import java.io.IOException;
import java.util.*;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Read in the excel files.
 * Name is specified by user.
 */
public class ReadExcel {
    private String inputFile;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public boolean read(HashMap hash) throws IOException  {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        int counter = 0;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            // Loop over first 10 column and lines

            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
                        hash.put(counter,cell.getContents());
                        counter++;
                    }

                    if (type == CellType.NUMBER) {
                        hash.put(counter,cell.getContents());
                        counter++;
                    }

                }
            }
        } catch (BiffException e) {
            return false;
        }
        return true;
    }

} 