import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Task> tasks = new ArrayList <>();
        SqlHandler sql = new SqlHandler();
        tasks = sql.read();

        Task task01 = new Task("Tro lo lo lo",
                "critical",
                LocalDate.of(1982, 11, 22),
                LocalDate.of(1985, 11, 23));

        Task task02 = new Task("Mam złączone brwi",
                "high",
                LocalDate.of(1974, 7, 20),
                LocalDate.of(1999, 8, 8));

        Task task03 = new Task("Ona tańczy dla mnie",
                "high",
                LocalDate.of(1988, 4, 28),
                LocalDate.of(2018, 12, 1));


        tasks.add(task01);
        tasks.add(task02);
        tasks.add(task03);
        sql.write(tasks);
        tasks = sql.read();

        for (Task task: tasks) {
            System.out.println(task);
        }



        ExportToExcel export = new ExportToExcel();

        try {
            export.write(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
