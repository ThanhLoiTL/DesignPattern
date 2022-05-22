package manager;

public interface IManager<T> {
    void add();
    void update();
    void delete();
    void showAll();
    void showOne(T t);
    T findById(String id);
    void menu();
}
