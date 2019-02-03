import java.time.LocalDate;

public class Task {

    public String name;
    public String priority;
    public LocalDate startDate;
    public LocalDate dueDate;

    public Task(String name, String priority, LocalDate startDate, LocalDate dueDate) {
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    public Object[] getObjectArrayFromTaskFields() {
        Object[] objectArratFromFilmFields = {name, priority, startDate, dueDate};
        return objectArratFromFilmFields;
    }

    @Override
    public String toString() {
        return (name +" | "+ priority + " | " + startDate + " | " + dueDate);
    }
}
