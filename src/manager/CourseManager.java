package manager;

import model.Course;
import model.Student;
import model.Subject;
import util.CheckValid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseManager implements IManager<Course>{
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Course> courses = new ArrayList<>();
    private static final SubjectManager subjectManager = new SubjectManager();
    private static final IManager<Student> studentManager = new StudentManager();

    public CourseManager() {
    }
    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nNhập mã lớp học: ");
        String id = CheckValid.checkString(scanner);
        System.out.print("Nhập tên lớp học: ");
        String name = CheckValid.checkString(scanner);
        System.out.print("Nhập phòng học: ");
        String classRoom = CheckValid.checkString(scanner);
        System.out.print("Nhập mã môn học: ");
        String subjectId = CheckValid.checkString(scanner);
        Subject subject = subjectManager.findById(subjectId);
        if (subject == null) {
            System.out.println("Môn học có mã " + subjectId + " không tồn tại!");
        } else {
            Course course = new Course(id, name, classRoom, subject);
            courses.add(course);
        }
    }

    public boolean addStudentToCourse() {
        System.out.print("Nhập mã lớp học: ");
        String courseId = scanner.nextLine();
        Course course = findById(courseId);
        if (course == null) {
            System.out.println("Mã lớp không tồn tại!");
            return false;
        }
        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine();
        Student student = (Student) studentManager.findById(studentId);
        if (student != null) {
            course.registerObserver(student);
            return true;
        } else {
            System.out.println("Mã sinh viên không tồn tại!");
        }
        return false;
    }
    @Override
    public Course findById(String courseId) {
        for (Course co : courses) {
            if (co.getId().compareTo(courseId) == 0)
                return co;
        }
        return null;
    }
    @Override
    public void showOne(Course course) {
        System.out.printf("%-12s%-25s%-15s%-15s\n",
                course.getId(), course.getName(), course.getClassRoom(), course.getSubject().getName());
    }
    @Override
    public void showAll() {
        System.out.println("==> Danh sách các lớp học <==");
        System.out.printf("%-12s%-25s%-15s%-15s\n",
                "Mã lớp", "Tên lớp", "Phòng học", "Môn học");
        for (Course co : courses) {
            showOne(co);
        }
    }

    public void showStudentInCourse() {
        System.out.print("Nhập mã lớp học: ");
        String courseId = scanner.nextLine();
        Course course = findById(courseId);
        if (course == null) {
            System.out.println("Mã lớp không tồn tại!");
        }else {
            System.out.printf("%-5s%-20s%-10s%-15s%-20s%-25s%-15s%-10s\n",
                    "ID", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ", "email",
                    "SĐT", "Tên lớp");
            for(int i=0;i<course.getTranscriptOfStudents().size();i++){
                Student student = course.getTranscriptOfStudents().get(i).getStudent();
                studentManager.showOne(student);
            }
        }
    }
    @Override
    public void update() {
        System.out.print("Nhập mã lớp học: ");
        String courseId = scanner.nextLine();
        Course course = findById(courseId);
        if (course == null) {
            System.out.println("Mã lớp không tồn tại!");
        }else {
            System.out.print("Cập nhật lại phòng học: ");
            String classRoom = scanner.nextLine();
            course.setClassRoom(classRoom);
            System.out.print("Cập nhật thành công! ");
        }
    }

    @Override
    public void delete() {

    }

    public Student findStudentInCourse(Course course ,String studentID) {
        for(int i =0; i< course.getTranscriptOfStudents().size(); i++) {
            if(course.getTranscriptOfStudents().get(i).getStudent().getId().compareTo(studentID) == 0){
                return course.getTranscriptOfStudents().get(i).getStudent();
            }
        }
        return null;
    }

    public void deleteStudentInCourse(Course course ,String studentID) {
        for(int i =0; i< course.getTranscriptOfStudents().size(); i++) {
            if(course.getTranscriptOfStudents().get(i).getStudent().getId().compareTo(studentID) == 0){
                course.getTranscriptOfStudents().remove(i);
            }
        }
    }

    public void deleteStudentFromCourse() {
        System.out.print("Nhập mã lớp học: ");
        String courseId = scanner.nextLine();
        Course course = findById(courseId);
        if (course == null) {
            System.out.println("Mã lớp không tồn tại!");
        }else {
            System.out.print("Nhập mã sinh viên cần xóa: ");
            String studentId = scanner.nextLine();
            Student student = findStudentInCourse(course, studentId);
            if (student != null) {
                deleteStudentInCourse(course, studentId);
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Mã sinh viên không tồn tại!");
            }
        }
    }

    public void menu() {
        System.out.println("\n-----------Menu------------");
        System.out.println("1. Thêm mới lớp học.");
        System.out.println("2. Xem danh sách lớp học.");
        System.out.println("3. Xem sinh viên trong lớp học.");
        System.out.println("4. Thêm sinh viên vào lớp học.");
        System.out.println("5. Xóa sinh viên khỏi lớp học.");
        System.out.println("6. Cập nhật phòng học của lớp học.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }

    public void addData() {
        courses.add(new Course("P1", "Phòng 1", "A1-101", new Subject("DSPT", "Design Pattern", 3, 30)));
    }

}
