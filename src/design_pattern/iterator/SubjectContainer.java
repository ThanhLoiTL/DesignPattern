package design_pattern.iterator;

import model.Subject;
import java.util.Collection;
import java.util.List;

public class SubjectContainer implements IContainer {
    Collection<Subject> integerCollection;
    @Override
    public IIterator createIterator(Collection collection) {
        this.integerCollection = collection;
        IIterator<Subject> result = new SubjectIterator();
        return result;
    }


    private class SubjectIterator implements IIterator<Subject> {
        private List<Subject> subjectList = integerCollection.stream().toList();
        private int index = 0;
        @Override
        public boolean hasNext() {
            if(index < subjectList.size() && subjectList.get(index) != null)
                return true;
            return false;
        }

        @Override
        public Subject next() {
            if(this.hasNext())
                return subjectList.get(index++);
            return null;
        }
    }
}
