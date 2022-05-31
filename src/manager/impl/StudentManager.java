package manager.impl;

import design_pattern.flyweight.PersonFactory;
import design_pattern.flyweight.PersonType;
import design_pattern.interpreter.Expression;
import design_pattern.interpreter.ExpressionParser;
import design_pattern.iterator.IContainer;
import design_pattern.iterator.IIterator;
import design_pattern.iterator.PersonContainer;
import manager.IManager;
import model.Person;
import model.Student;
import design_pattern.singleton.IDatabase;
import design_pattern.singleton.StudentDatabase;
import design_pattern.templateMethod.UpdateDeleteMethod;
import design_pattern.templateMethod.DeleteStudent;
import design_pattern.templateMethod.UpdateStudent;
import util.CheckValid;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class StudentManager implements IManager<Student> {
    private static final Scanner scanner = new Scanner(System.in);
    private final IDatabase<Person> studentFileManager;
    private final IContainer personContainer;
    private IIterator<Person> personIterator;
    private final UpdateDeleteMethod deleteStudent;
    private final UpdateDeleteMethod updateStudent;

    public StudentManager() {
        studentFileManager = StudentDatabase.getInstance();
        personContainer = new PersonContainer();
        deleteStudent = new DeleteStudent();
        updateStudent = new UpdateStudent();
        updateId(studentFileManager.getData());
    }

    private void updateId(List<Person> students) {
        int maxId = 0;
        for (Person student : students) {
            int curId = Integer.parseInt(student.getId().substring(1));
            if (curId > maxId) {
                maxId = curId;
            }
        }
        Student.setNextId(maxId + 1);
    }
    @Override
    public void add() {
        Student student = (Student) PersonFactory.createPerson(PersonType.STUDENT);
        student.setIdStudent(null);
        System.out.print("\nNhập tên sinh viên: ");
        student.setName(CheckValid.checkString(scanner));
        System.out.print("Nhập tuổi: ");
        student.setAge(CheckValid.checkInt(scanner));
        System.out.print("Nhập giới tính: ");
        student.setGender(CheckValid.checkString(scanner));
        System.out.print("Nhập địa chỉ: ");
        student.setAddress(CheckValid.checkString(scanner));
        System.out.print("Nhập email: ");
        student.setEmail(CheckValid.checkString(scanner));
        System.out.print("Nhập số điện thoại: ");
        student.setPhoneNumber(CheckValid.checkString(scanner));
        System.out.print("Nhập chuyên ngành học: ");
        student.setMajor(CheckValid.checkString(scanner));
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
        System.out.printf("%-5s%-20s%-10d%-15s%-20s%-25s%-15s%-15s\n",
                s.getId(), s.getName(),s.getAge(),s.getGender(), s.getAddress(), s.getEmail(),
                s.getPhoneNumber(), s.getMajor());
    }
    @Override
    public void showAll() {
        personIterator = personContainer.createIterator(studentFileManager.getData());
        int count = 0;
        printTitleInfo();
        while (personIterator.hasNext()) {
            count++;
            Student student = (Student) personIterator.next();
            showOne(student);
        }
        if (count == 0) {
            System.out.println("No data");
        }
    }
    private void printTitleInfo() {
        System.out.printf("%-5s%-20s%-10s%-15s%-20s%-25s%-15s%-15s\n",
                "ID", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ", "email",
                "SĐT", "Chuyên ngành");
    }

    private void saveStudent(Person student) {
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
        personIterator = personContainer.createIterator(studentFileManager.getData());
        while (personIterator.hasNext()) {
            Student student = (Student) personIterator.next();
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public void searchStudent(String type) {
        System.out.println("\nNhập một trong các thuộc tính để tìm sinh viên: id, name, age, gender, email, phone\n" +
                "VD: id:S1, name:Huy, age: 20");
        String contextString = CheckValid.checkString(scanner);
        int dem = 0;
        printTitleInfo();
        for (int i = 0; i< studentFileManager.getData().size(); i++) {
            Expression expression = null;
            if(Objects.equals(type, "OR")){
                expression = ExpressionParser.parseOrExpression(contextString);
            }else if(Objects.equals(type, "AND")){
                expression = ExpressionParser.parseAndExpression(contextString);
            }
            if(expression == null){
                return;
            }
            boolean isFinded = expression.interpreter(studentFileManager.getData().get(i));
            if(isFinded){
                dem++;
                showOne((Student) studentFileManager.getData().get(i));
            }
        }
        if (dem==0) {
            System.out.println("Không tìm thấy sinh viên nào!");
        }
    }

    public void menu() {
        System.out.println("\n------Quản lý sinh viên------");
        System.out.println("-------------Menu------------");
        System.out.println("1. Thêm mới sinh viên.");
        System.out.println("2. Xem danh sách sinh viên.");
        System.out.println("3. Clone sinh viên.");
        System.out.println("4. Xóa sinh viên.");
        System.out.println("5. Chỉnh sửa thông tin 1 sinh viên.");
        System.out.println("6. Tìm kiếm chính xác 1 sinh viên.");
        System.out.println("7. Tìm kiếm sinh viên tương đương.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}
