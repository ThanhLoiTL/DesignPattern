package design_pattern.singleton;

import constant.SystemConstant;
import model.Subject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SubjectDatabase implements IDatabase<Subject>{
    private List<Subject> subjectList;
    private static String fileName = SystemConstant.FILE_SUBJECT;

    private static SubjectDatabase instance;

    private SubjectDatabase() {
        subjectList = new ArrayList<>();
        readData();
    }

    public static SubjectDatabase getInstance() {
        if (instance == null) {
            synchronized (SubjectDatabase.class) {
                if (instance == null) {
                    instance = new SubjectDatabase();
                }
            }
        }
        return instance;
    }

    @Override
    public void writeData(Subject subject) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            if(subject != null){
                subjectList.add(subject);
            }
            for (Subject s : subjectList) {
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

    @Override
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
                Subject subject = new Subject();
                subject.parse(line);
                subjectList.add(subject);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fis);
        }
    }

    @Override
    public List<Subject> getData() {
        return subjectList;
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
