package design_pattern.iterator;

public interface IIterator<T>{
    boolean hasNext();
    T next();
}
