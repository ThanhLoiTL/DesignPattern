package manager;

import iterator.IContainer;
import iterator.IIterator;
import iterator.StudentContainer;
import model.Student;
import singleton.StudentDatabase;

import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private static Scanner scanner = new Scanner(System.in);
    private StudentDatabase studentFileManager;
    private IContainer studentContainer;
    private IIterator studentIterator;

    public StudentManager() {
        studentFileManager = StudentDatabase.getInstance();
        studentContainer = new StudentContainer();
        updateEmployeeId(studentFileManager.getList());
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

    public void addNewStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nNhập tên sinh viên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập tuổi: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập giới tính: ");
        String gender = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();
        System.out.print("Nhập tên lớp: ");
        String className = scanner.nextLine();
        Student student = new Student(null, name, age, gender, address, email, phone, className);
        saveStudent(student);
    }

    public void showStudent(Student s) {
        System.out.printf("%-5s%-20s%-10d%-15s%-20s%-25s%-15s%-10s\n",
                s.getId(), s.getName(),s.getAge(),s.getGender(), s.getAddress(), s.getEmail(),
                s.getPhoneNumber(), s.getClassName());
    }

    public void showStudents() {
        studentIterator = studentContainer.createIterator(studentFileManager.getList());
        int count = 0;
        System.out.printf("%-5s%-20s%-10s%-15s%-20s%-25s%-15s%-10s\n",
                "ID", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ", "email",
                "SĐT", "Tên lớp");
        while (studentIterator.hasNext()) {
            count++;
            Student student = (Student) studentIterator.next();
            showStudent(student);
        }
        if (count == 0) {
            System.out.println("No data");
        }
    }

    private void saveStudent(Student student) {
        studentFileManager.writeData(student);
    }

    public void clone(Student student) {
        if (student != null) {
            Student s = (Student) student.clone();
            saveStudent(s);
        }
    }

    public Student findStudentById(String id) {
        studentIterator = studentContainer.createIterator(studentFileManager.getList());
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
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}
