package design_pattern.iterator;

import java.util.Collection;

public interface IContainer {
    IIterator createIterator(Collection collection);
}
