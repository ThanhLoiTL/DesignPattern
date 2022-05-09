package singleton;

import constant.SystemConstant;
import model.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabase implements IDatabase<Student> {
    private List<Student> studentList;
    private static String fileName = SystemConstant.FILE_STUDENT;

    private static StudentDatabase instance;

    private StudentDatabase() {
        studentList = new ArrayList<>();
        readData();
    }

    public static StudentDatabase getInstance() {
        if (instance == null) {
            synchronized (StudentDatabase.class) {
                if (instance == null) {
                    instance = new StudentDatabase();
                }
            }
        }
        return instance;
    }

    public void writeData(Student student) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            studentList.add(student);
            for (Student s : studentList) {
                String line = s.line();
                byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
                fos.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fos);
        }
    }

    public void readData() {
        FileInputStream fis = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            fis = new FileInputStream(fileName);
            reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                Student student = new Student();
                student.parse(line);
                studentList.add(student);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fis);
        }
    }

    public List<Student> getList() {
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
