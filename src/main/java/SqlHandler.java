import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SqlHandler implements Writeable, Readable {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DATABASE_URL = "jdbc:sqlite:c:/Users/malgorzata.majchrowi/MOJE/EDU/TECH/JAVA/TaskManagerByJavaDreamTeam/Database/";
    static final String DATABASE_NAME = "TASKSDATABASE.db";
    static final String TABLE_NAME = "Tasks";

    @Override
    public List read() {
        List<Task> tasks = new ArrayList<>();
        try {
            Connection connection = createConnection(DATABASE_NAME);
            tasks = readTasks(connection, TABLE_NAME);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private void removeTable(String tableName, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("DROP TABLE IF EXISTS %s", tableName));
        statement.close();
    }

    private List<Task> readTasks(Connection connection, String tableName) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", tableName));
        while (resultSet.next()) {

            String name = resultSet.getString("taskName");
            String priority = resultSet.getString("priority");

            String startDate = resultSet.getString("startDate");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate01 = LocalDate.parse(startDate,formatter);

            String dueDate = resultSet.getString("dueDate");
            LocalDate dueDate01 = LocalDate.parse(dueDate,formatter);

            tasks.add(new Task(name, priority, startDate01, dueDate01));
        }
        statement.close();
        return tasks;
    }

    @Override
    public void write(List<Task> tasks) {
        try {
            Connection connection = createConnection(DATABASE_NAME);
            removeTable(TABLE_NAME, connection);
            createTableInDatabase(connection, TABLE_NAME);
            addListToDatabase(connection, tasks, TABLE_NAME);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableInDatabase(Connection connection, String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("CREATE TABLE IF NOT EXISTS %s " +
                "(taskName VARCHAR, " +
                " priority VARCHAR, " +
                " startDate DATE,"+
                " dueDate DATE)", tableName));
        statement.close();
    }


    private void addListToDatabase(Connection connection, List<Task> tasks, String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        for (Task task : tasks) {
            statement.executeUpdate(String.format("INSERT INTO %s (taskName, priority, startDate, dueDate) VALUES ('%s', '%s', '%s', '%s')",
                    tableName,
                    task.name,
                    task.priority,
                    task.startDate,
                    task.dueDate));
            }
        statement.close();
    }


    private Connection createConnection(String databaseName) throws SQLException {

        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL + databaseName);
            if (connection != null) {
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
