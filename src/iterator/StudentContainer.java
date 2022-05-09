package iterator;

import model.Student;

import java.util.Collection;
import java.util.List;

public class StudentContainer implements IContainer {
    Collection<Student> integerCollection;
    @Override
    public IIterator createIterator(Collection collection) {
        this.integerCollection = collection;
        IIterator<Student> result = new StudentIterator();
        return result;
    }


    private class StudentIterator implements IIterator<Student> {
        private List<Student> studentList = integerCollection.stream().toList();
        private int index = 0;
        @Override
        public boolean hasNext() {
            if(index < studentList.size() && studentList.get(index) != null)
                return true;
            return false;
        }

        @Override
        public Student next() {
            if(this.hasNext())
                return studentList.get(index++);
            return null;
        }
    }
}
