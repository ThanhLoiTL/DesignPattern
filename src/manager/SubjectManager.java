package manager;

import iterator.IContainer;
import iterator.IIterator;
import iterator.SubjectContainer;
import model.Subject;
import singleton.IDatabase;
import singleton.SubjectDatabase;
import templateMethod.UpdateDeleteMethod;
import templateMethod.DeleteSubject;
import util.CheckValid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubjectManager implements IManager<Subject>{
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Subject> subjects = new ArrayList<>();
    private IDatabase<Subject> subjectFileManager = SubjectDatabase.getInstance();
    private IContainer subjectContainer = new SubjectContainer();
    private IIterator subjectIterator;
    private UpdateDeleteMethod deleteSubject = new DeleteSubject();
    public SubjectManager() {
    }

    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nNhập mã môn học: ");
        String id = CheckValid.checkString(scanner);
        System.out.print("Nhập tên môn học: ");
        String name = CheckValid.checkString(scanner);
        System.out.print("Nhập số tín chỉ: ");
        int credit = CheckValid.checkInt(scanner);
        System.out.print("Nhập số tiết học: ");
        int numOfLesson = CheckValid.checkInt(scanner);
        Subject subject = new Subject(id, name, credit, numOfLesson);
        saveSubject(subject);
    }

    private void saveSubject(Subject subject) {
        subjectFileManager.writeData(subject);
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {
        deleteSubject.execute();
    }
    @Override
    public Subject findById(String subjectId) {
        for (Subject sub : subjects) {
            if (sub.getId().compareTo(subjectId) == 0)
                return sub;
        }
        return null;
    }
    @Override
    public void showOne(Subject subject) {
        System.out.printf("%-12s%-25s%-12d%-12d\n",
                subject.getId(), subject.getName(), subject.getCredit(),
                subject.getNumOfLesson());
    }

    @Override
    public void showAll() {
        subjectIterator = subjectContainer.createIterator(subjectFileManager.getData());
        int count = 0;
        System.out.println("Danh sách môn học");
        System.out.printf("%-12s%-25s%-12s%-12s\n",
                "Mã môn", "Tên môn", "Số tín", "Số tiết");
        while (subjectIterator.hasNext()) {
            count++;
            Subject subject = (Subject) subjectIterator.next();
            showOne(subject);
        }
        if (count == 0) {
            System.out.println("No data");
        }
    }

    public void menu() {
        System.out.println("\n-----------Menu------------");
        System.out.println("1. Thêm mới môn học.");
        System.out.println("2. Xóa môn học.");
        System.out.println("3. Xem danh sách môn học.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}
