package manager.impl;

import design_pattern.flyweight.PersonFactory;
import design_pattern.flyweight.PersonType;
import design_pattern.iterator.IContainer;
import design_pattern.iterator.IIterator;
import design_pattern.iterator.PersonContainer;
import design_pattern.mediator.IFindMediator;
import manager.IManager;
import model.Person;
import model.Teacher;
import design_pattern.singleton.IDatabase;
import design_pattern.singleton.TeacherDatabase;
import design_pattern.templateMethod.DeleteTeacher;
import design_pattern.templateMethod.UpdateDeleteMethod;
import design_pattern.templateMethod.UpdateTeacher;
import util.CheckValid;

import java.util.List;
import java.util.Scanner;

public class TeacherManager implements IManager<Teacher> {
    private final IDatabase<Person> teacherFileManager;
    private final IContainer personContainer;
    private IIterator<Person> personIterator;
    private final UpdateDeleteMethod deleteTeacher;
    private final UpdateDeleteMethod updateTeacher;
    private IFindMediator findMediator;

    public TeacherManager() {
        teacherFileManager = TeacherDatabase.getInstance();
        personContainer = new PersonContainer();
        updateId(teacherFileManager.getData());
        deleteTeacher = new DeleteTeacher();
        updateTeacher = new UpdateTeacher();
    }
    public TeacherManager(IFindMediator findMediator) {
        this.findMediator = findMediator;
        teacherFileManager = TeacherDatabase.getInstance();
        personContainer = new PersonContainer();
        updateId(teacherFileManager.getData());
        deleteTeacher = new DeleteTeacher();
        updateTeacher = new UpdateTeacher();
    }

    private void updateId(List<Person> teachers) {
        int maxId = 0;
        for (Person teacher : teachers) {
            int curId = Integer.parseInt(teacher.getId().substring(1));
            if (curId > maxId) {
                maxId = curId;
            }
        }
        Teacher.setNextId(maxId + 1);
    }
    @Override
    public void add() {
        Teacher teacher = (Teacher) PersonFactory.createPerson(PersonType.TEACHER);
        Scanner scanner = new Scanner(System.in);
        teacher.setIdTeacher(null);
        System.out.print("\nNhập tên giảng viên: ");
        teacher.setName(CheckValid.checkString(scanner));
        System.out.print("Nhập tuổi: ");
        teacher.setAge(CheckValid.checkInt(scanner));
        System.out.print("Nhập giới tính: ");
        teacher.setGender(CheckValid.checkString(scanner));
        System.out.print("Nhập địa chỉ: ");
        teacher.setAddress(CheckValid.checkString(scanner));
        System.out.print("Nhập email: ");
        teacher.setEmail(CheckValid.checkString(scanner));
        System.out.print("Nhập số điện thoại: ");
        teacher.setPhoneNumber(CheckValid.checkString(scanner));
        System.out.print("Nhập tên khoa: ");
        teacher.setDepartment(CheckValid.checkString(scanner));
        System.out.print("Nhập lương: ");
        teacher.setSalary(CheckValid.checkInt(scanner));
        saveTeacher(teacher);
    }

    @Override
    public void update() {
        updateTeacher.execute();
    }

    @Override
    public void delete() {
        deleteTeacher.execute();
    }

    @Override
    public void showOne(Teacher t) {
        System.out.printf("%-5s%-20s%-10d%-15s%-20s%-25s%-15s%-15s%-15s\n",
                t.getId(), t.getName(),t.getAge(),t.getGender(), t.getAddress(), t.getEmail(),
                t.getPhoneNumber(), t.getDepartment(), t.getSalary());
    }
    @Override
    public void showAll() {
        personIterator = personContainer.createIterator(teacherFileManager.getData());
        int count = 0;
        System.out.printf("%-5s%-20s%-10s%-15s%-20s%-25s%-15s%-15s%-15s\n",
                "ID", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ", "email",
                "SĐT", "Khoa", "Lương");
        while (personIterator.hasNext()) {
            count++;
            Teacher teacher = (Teacher) personIterator.next();
            showOne(teacher);
        }
        if (count == 0) {
            System.out.println("No data");
        }
    }

    private void saveTeacher(Person teacher) {
        teacherFileManager.writeData(teacher);
        System.out.println("Thêm thành công");
    }

    @Override
    public Teacher findById(String id) {
        personIterator = personContainer.createIterator(teacherFileManager.getData());
        while (personIterator.hasNext()) {
            Teacher teacher = (Teacher) personIterator.next();
            if (teacher.getId().equalsIgnoreCase(id)) {
                return teacher;
            }
        }
        return null;
    }

    public void menu() {
        System.out.println("\n------Quản lý giảng viên-----");
        System.out.println("-------------Menu------------");
        System.out.println("1. Thêm mới giảng viên.");
        System.out.println("2. Xem danh sách giảng viên.");
        System.out.println("3. Xóa giảng viên.");
        System.out.println("4. Chỉnh sửa thông tin 1 giảng viên.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}
