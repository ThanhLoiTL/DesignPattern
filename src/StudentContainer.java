import java.util.List;

public class StudentContainer implements IContainer{
    @Override
    public IIterator createIterator() {
        IIterator result = new StudentIterator();
        return result;
    }
    private class StudentIterator implements IIterator {
        private List<Student> studentList;
        int index = 0;

        public StudentIterator() {
            this.studentList = StudentFileManager.getInstance().read();
        }

        @Override
        public boolean hasNext() {
            if(index < studentList.size() && studentList.get(index) != null)
                return true;
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext())
                return studentList.get(index++);
            return null;
        }
    }
}
