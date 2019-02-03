import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class ExportToExcel implements Writeable {

    static final String PATH = "c:\\Users\\malgorzata.majchrowi\\MOJE\\EDU\\TECH\\JAVA\\TaskManagerByJavaDreamTeam\\Export.xlsx";


    @Override
    public void write(List<Task> tasks) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("TasksExport");
        addTasks(sheet, tasks);
        FileOutputStream outputStream = new FileOutputStream(PATH);
        workbook.write(outputStream);
        workbook.close();
    }

    private void addTasks(XSSFSheet sheet, List<Task> tasks) {
        int i_row = 0;
        for (Task task : tasks) {
            Row row = sheet.createRow(i_row++);
            int j_column = 0;
            Object[] taskFields = task.getObjectArrayFromTaskFields();
            for (Object field : taskFields) {
                Cell cell = row.createCell(j_column++);
                cell.setCellValue(field.toString());
            }
        }
    }
}