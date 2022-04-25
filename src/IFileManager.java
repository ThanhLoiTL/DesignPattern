import java.util.List;

public interface IFileManager <T>{
    void write(T t);
    List<T> read();
}
