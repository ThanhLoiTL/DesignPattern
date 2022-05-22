package manager.impl;

import design_pattern.iterator.IContainer;
import design_pattern.iterator.IIterator;
import design_pattern.iterator.SubjectContainer;
import design_pattern.mediator.IFindMediator;
import manager.IManager;
import model.Subject;
import design_pattern.singleton.IDatabase;
import design_pattern.singleton.SubjectDatabase;
import design_pattern.templateMethod.UpdateDeleteMethod;
import design_pattern.templateMethod.DeleteSubject;
import util.CheckValid;
import java.util.Scanner;

public class SubjectManager implements IManager<Subject> {
    private final IDatabase<Subject> subjectFileManager;
    private final IContainer subjectContainer;
    private final UpdateDeleteMethod deleteSubject;
    private IFindMediator findMediator;

    public SubjectManager(IFindMediator findMediator) {
        this.findMediator = findMediator;
        subjectFileManager = SubjectDatabase.getInstance();
        subjectContainer = new SubjectContainer();
        deleteSubject = new DeleteSubject();
    }

    public SubjectManager() {
        subjectFileManager = SubjectDatabase.getInstance();
        subjectContainer = new SubjectContainer();
        deleteSubject = new DeleteSubject();
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
        for (Subject sub : subjectFileManager.getData()) {
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
        IIterator<Subject> subjectIterator = subjectContainer.createIterator(subjectFileManager.getData());
        int count = 0;
        System.out.println("Danh sách môn học");
        System.out.printf("%-12s%-25s%-12s%-12s\n",
                "Mã môn", "Tên môn", "Số tín", "Số tiết");
        while (subjectIterator.hasNext()) {
            count++;
            Subject subject = subjectIterator.next();
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
