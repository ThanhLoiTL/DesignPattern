package design_pattern.singleton;

import java.util.List;

public interface IDatabase<T> {
    void writeData(T t);
    void readData();
    List<T> getData();
}
