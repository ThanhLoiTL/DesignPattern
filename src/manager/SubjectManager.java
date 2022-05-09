package manager;

import model.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubjectManager {
    private static Scanner scanner;
    private static List<Subject> subjects = new ArrayList<>();

    public SubjectManager() {
        scanner = new Scanner(System.in);
    }

    public void addNewSubject() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nNhập mã môn học: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên môn học: ");
        String name = scanner.nextLine();
        System.out.print("Nhập số tín chỉ: ");
        int credit = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập số tiết học: ");
        int numOfLesson = Integer.parseInt(scanner.nextLine());
        Subject subject = new Subject(id, name, credit, numOfLesson);
        subjects.add(subject);
    }

    public Subject findSubjectById(String subjectId) {
        for (var sub : subjects) {
            if (sub.getId().compareTo(subjectId) == 0)
                return sub;
        }
        return null;
    }

    private static void showSubject(Subject subject) {
        System.out.printf("%-12s%-25s%-12d%-12d\n",
                subject.getId(), subject.getName(), subject.getCredit(),
                subject.getNumOfLesson());
    }

    public void showSubjects() {
        System.out.println("Danh sách môn học");
        System.out.printf("%-12s%-25s%-12s%-12s\n",
                "Mã môn", "Tên môn", "Số tín", "Số tiết");
        for (var sub : subjects) {
            showSubject(sub);
        }
    }

    public void menu() {
        System.out.println("\n-----------Menu------------");
        System.out.println("1. Thêm mới môn học.");
        System.out.println("2. Xem danh sách môn học.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }

    public void addData() {
        subjects.add(new Subject("DSPT", "Design Pattern", 3, 30));
        subjects.add(new Subject("LTW", "WEB", 3, 25));
    }
}
