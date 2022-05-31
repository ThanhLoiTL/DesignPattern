package design_pattern.iterator;

import model.Person;

import java.util.Collection;
import java.util.List;

public class PersonContainer implements IContainer {
    Collection<Person> collection;
    @Override
    public IIterator createIterator(Collection collection) {
        this.collection = collection;
        IIterator<Person> result = new PersonIterator();
        return result;
    }


    private class PersonIterator implements IIterator<Person> {
        private List<Person> personList = collection.stream().toList();
        private int index = 0;
        @Override
        public boolean hasNext() {
            if(index < personList.size() && personList.get(index) != null)
                return true;
            return false;
        }

        @Override
        public Person next() {
            if(this.hasNext())
                return personList.get(index++);
            return null;
        }
    }
}
