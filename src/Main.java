import manager.CourseManager;
import manager.StudentManager;
import manager.SubjectManager;
import model.Student;
import util.CheckError;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Integer choose = null;
        boolean exit = false;
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();
        SubjectManager subjectManager = new SubjectManager();
        subjectManager.addData();
        courseManager.addData();
        // show menu
        showMenu();
        while (true) {
            choose = CheckError.checkChoose(scanner.nextLine());
            switch (choose) {
                case 1:
                    do
                    {
                        studentManager.menu();
                        choose = CheckError.checkChoose(scanner.nextLine());
                        switch (choose)
                        {
                            case 1:
                                studentManager.addNewStudent();
                                break;
                            case 2:
                                studentManager.showStudents();
                                break;
                            case 3:
                                System.out.print("Nhập mã sinh viên: ");
                                String id = scanner.nextLine().trim();
                                Student student = studentManager.findStudentById(id);
                                if(student != null){
                                    studentManager.clone(student);
                                }else{
                                    System.out.print("Không tìm thấy sinh viên!\n");
                                }
                                break;
                            case 0:
                                System.out.println("Exited!\n");
                                break;
                            default:
                                System.out.println("Invalid! please choose action in below menu.\n");
                                break;
                        }
                    } while (choose != 0);
                    break;
                case 2:
                    do
                    {
                        courseManager.menu();
                        choose = CheckError.checkChoose(scanner.nextLine());
                        switch (choose)
                        {
                            case 1:
                                courseManager.addNewCourse();
                                break;
                            case 2:
                                courseManager.showCourses();
                                break;
                            case 3:
                                courseManager.showStudentInCourse();
                                break;
                            case 4:
                                courseManager.addStudentToCourse();
                                break;
                            case 5:
                                courseManager.deleteStudentFromCourse();
                                break;
                            case 6:
                                courseManager.updateCourse();
                                break;
                            case 0:
                                System.out.println("Exited!\n");
                                break;
                            default:
                                System.out.println("Invalid! please choose action in below menu.\n");
                                break;
                        }
                    } while (choose != 0);
                    break;
                case 3:
                    do
                    {
                        subjectManager.menu();
                        choose = CheckError.checkChoose(scanner.nextLine());
                        switch (choose)
                        {
                            case 1:
                                subjectManager.addNewSubject();
                                break;
                            case 2:
                                subjectManager.showSubjects();
                                break;
                            case 0:
                                System.out.println("Exited!\n");
                                break;
                            default:
                                System.out.println("Invalid! please choose action in below menu.\n");
                                break;
                        }
                    } while (choose != 0);
                    break;
                case 0:
                    System.out.println("Exited!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid! please choose action in below menu.\n");
                    break;
            }
            if (exit) {
                break;
            }
            showMenu();
        }
    }

    public static void showMenu() {
        System.out.println("\n-----------menu------------");
        System.out.println("1. Quản lý sinh viên.");
        System.out.println("2. Quản lý lớp học.");
        System.out.println("3. Quản lý môn học.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}
