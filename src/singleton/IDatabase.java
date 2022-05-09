package singleton;

public interface IDatabase<T> {
    void writeData(T t);

    void readData();
}
