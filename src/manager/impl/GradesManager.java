package manager.impl;

import design_pattern.mediator.IFindMediator;
import design_pattern.mediator.ObjectType;
import manager.IManager;
import model.Course;
import model.Student;
import model.Transcript;
import util.CheckValid;

import java.util.Scanner;

public class GradesManager implements IManager<Transcript> {
    private static final Scanner scanner = new Scanner(System.in);
    private IFindMediator findMediator;

    public GradesManager(IFindMediator findMediator) {
        this.findMediator = findMediator;
    }

    public GradesManager() {

    }

    private Course findCourse() {
        System.out.print("Nhập mã lớp học: ");
        String gradeId = CheckValid.checkString(scanner);
        return (Course) findMediator.findOne(ObjectType.COURSE, gradeId);
    }

    private Transcript createTranscript() {
        System.out.print("Nhập điểm hệ số 1: ");
        float grade1 = CheckValid.checkFloat(scanner);
        System.out.print("Nhập điểm hệ số 2: ");
        float grade2 = CheckValid.checkFloat(scanner);
        System.out.print("Nhập điểm hệ số 3: ");
        float grade3 = CheckValid.checkFloat(scanner);

        Transcript transcript = new Transcript();
        transcript.setGrade1(grade1);
        transcript.setGrade2(grade2);
        transcript.setGrade3(grade3);

        transcript.calCulAvgGrade();
        return transcript;
    }

    public void showOne(Course.TranscriptOfStudent tOS) {
        if(tOS.getTranscript() != null){
            System.out.printf("%-15s%-25s%-20s%-15s%-15s%-15s\n",
                    tOS.getStudent().getId() ,tOS.getStudent().getName(), tOS.getTranscript().getGrade1(), tOS.getTranscript().getGrade2(), tOS.getTranscript().getGrade3(),
                    tOS.getTranscript().getAvgGrade());
        }else {
            System.out.printf("%-15s%-25s%-20s%-15s%-15s%-15s\n",
                    tOS.getStudent().getId() ,tOS.getStudent().getName(), "null", "null", "null", "null");
        }
    }

    @Override
    public void add() {
        //find course
        Course course = findCourse();
        //find student in course
        if(course != null) {
            System.out.println("Nhập mã sinh viên: ");
            String studentId = CheckValid.checkString(scanner);
            Student student = null;
            for(int i =0; i< course.getTranscriptOfStudents().size(); i++) {
                if(course.getTranscriptOfStudents().get(i).getStudent().getId().compareTo(studentId) == 0){
                    student = course.getTranscriptOfStudents().get(i).getStudent();
                }
            }
            if (student != null) {
                Transcript tran = createTranscript();
                course.setTranscriptOfStudents(student, tran);
                System.out.println("==> Cập nhật bảng điểm thành công <==");
            } else {
                System.out.println("==> Sinh viên mã " + studentId
                        + " không tồn tại trong lớp <==");
            }
        } else {
            System.out.println("==> Mã lớp không tồn tại. Vui lòng kiểm tra lại <==");
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void showAll() {
        Course course = findCourse();
        if(course != null) {
            System.out.println("==> Danh sách điểm <==");
            System.out.printf("%-15s%-25s%-20s%-15s%-15s%-15s\n",
                    "Mã sinh viên", "Tên sinh viên", "Điểm hệ số 1", "Điểm hệ số 2", "Điểm hệ số 3","Điểm trung bình");
            for (int i = 0; i< course.getTranscriptOfStudents().size(); i++) {
                showOne(course.getTranscriptOfStudents().get(i));
            }
        } else {
            System.out.println("==> Mã lớp không tồn tại. Vui lòng kiểm tra lại <==");
        }
    }

    @Override
    public void showOne(Transcript transcript) {

    }

    @Override
    public Transcript findById(String id) {
        return null;
    }

    public void menu() {
        System.out.println("\n-----------Menu------------");
        System.out.println("1. Xem danh sách điểm.");
        System.out.println("2. Thêm điểm cho sinh viên.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}
