import java.io.IOException;
import java.util.List;

public interface Writeable {
    void write(List<Task> list) throws IOException;
}
