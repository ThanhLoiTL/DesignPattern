package manager;

import iterator.IContainer;
import iterator.IIterator;
import iterator.StudentContainer;
import model.Student;
import singleton.IDatabase;
import singleton.StudentDatabase;
import templateMethod.UpdateDeleteMethod;
import templateMethod.DeleteStudent;
import templateMethod.UpdateStudent;
import util.CheckValid;

import java.util.List;
import java.util.Scanner;

public class StudentManager implements IManager<Student>{
    private static Scanner scanner = new Scanner(System.in);
    private IDatabase<Student> studentFileManager;
    private IContainer studentContainer;
    private IIterator studentIterator;
    private UpdateDeleteMethod deleteStudent = new DeleteStudent();
    private UpdateDeleteMethod updateStudent = new UpdateStudent();

    public StudentManager() {
        studentFileManager = StudentDatabase.getInstance();
        studentContainer = new StudentContainer();
        updateEmployeeId(studentFileManager.getData());
    }

    private void updateEmployeeId(List<Student> students) {
        int maxId = 0;
        for (Student student : students) {
            int curId = Integer.parseInt(student.getId().substring(1));
            if (curId > maxId) {
                maxId = curId;
            }
        }
        Student.setNextId(maxId + 1);
    }
    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nNhập tên sinh viên: ");
        String name = CheckValid.checkString(scanner);
        System.out.print("Nhập tuổi: ");
        int age = CheckValid.checkInt(scanner);
        System.out.print("Nhập giới tính: ");
        String gender = CheckValid.checkString(scanner);
        System.out.print("Nhập địa chỉ: ");
        String address = CheckValid.checkString(scanner);
        System.out.print("Nhập email: ");
        String email = CheckValid.checkString(scanner);
        System.out.print("Nhập số điện thoại: ");
        String phone = CheckValid.checkString(scanner);
        Student student = new Student(null, name, age, gender, address, email, phone);
        saveStudent(student);
    }

    @Override
    public void update() {
        updateStudent.execute();
    }

    @Override
    public void delete() {
        deleteStudent.execute();
    }

    @Override
    public void showOne(Student s) {
        System.out.printf("%-5s%-20s%-10d%-15s%-20s%-25s%-15s\n",
                s.getId(), s.getName(),s.getAge(),s.getGender(), s.getAddress(), s.getEmail(),
                s.getPhoneNumber());
    }
    @Override
    public void showAll() {
        studentIterator = studentContainer.createIterator(studentFileManager.getData());
        int count = 0;
        System.out.printf("%-5s%-20s%-10s%-15s%-20s%-25s%-15s\n",
                "ID", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ", "email",
                "SĐT");
        while (studentIterator.hasNext()) {
            count++;
            Student student = (Student) studentIterator.next();
            showOne(student);
        }
        if (count == 0) {
            System.out.println("No data");
        }
    }

    private void saveStudent(Student student) {
        studentFileManager.writeData(student);
        System.out.println("Thêm thành công");
    }

    public void clone(Student student) {
        if (student != null) {
            Student s = (Student) student.clone();
            saveStudent(s);
            System.out.println("Clone thành công");
        }
    }
    @Override
    public Student findById(String id) {
        studentIterator = studentContainer.createIterator(studentFileManager.getData());
        while (studentIterator.hasNext()) {
            Student student = (Student) studentIterator.next();
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public void menu() {
        System.out.println("\n-----------menu------------");
        System.out.println("1. Thêm mới sinh viên.");
        System.out.println("2. Xem danh sách sinh viên.");
        System.out.println("3. Clone sinh viên.");
        System.out.println("4. Xóa sinh viên.");
        System.out.println("5. Chỉnh sửa thông tin 1 sinh viên.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}
