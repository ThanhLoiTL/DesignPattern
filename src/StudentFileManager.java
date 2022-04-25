import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFileManager implements IFileManager<Student>{
    private List<Student> studentList;
    private String fileName = "student.txt";
    private static StudentFileManager instance;

    private StudentFileManager() {
        studentList = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            studentList = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        } finally {
            closeStream(fis);
            closeStream(ois);
        }
    }

    public static StudentFileManager getInstance(){
        if(instance == null) {
            synchronized (StudentFileManager.class) {
                if (instance == null) {
                    instance = new StudentFileManager();
                }
            }
        }
        return instance;
    }

    public void write(Student student) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            studentList.add(student);
            oos.writeObject(studentList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fos);
            closeStream(oos);
        }
    }

    public List<Student> read() {
        return studentList;
    }

    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
