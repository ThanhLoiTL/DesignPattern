package design_pattern.singleton;

import constant.SystemConstant;
import model.Person;
import model.Teacher;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TeacherDatabase implements IDatabase<Person> {
    private List<Person> teacherList;
    private static String fileName = SystemConstant.FILE_TEACHER;

    private static TeacherDatabase instance;

    private TeacherDatabase() {
        teacherList = new ArrayList<>();
        readData();
    }

    public static TeacherDatabase getInstance() {
        if (instance == null) {
            synchronized (TeacherDatabase.class) {
                if (instance == null) {
                    instance = new TeacherDatabase();
                }
            }
        }
        return instance;
    }

    public void writeData(Person teacher) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            if(teacher != null){
                teacherList.add(teacher);
            }
            for (Person t : teacherList) {
                String line = t.line();
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
                Person teacher = new Teacher();
                teacher.parse(line);
                teacherList.add(teacher);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fis);
        }
    }

    public List<Person> getData() {
        return teacherList;
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
