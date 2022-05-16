package templateMethod;

import iterator.IContainer;
import iterator.SubjectContainer;
import model.Subject;
import singleton.IDatabase;
import singleton.SubjectDatabase;
import util.CheckValid;

import java.util.Scanner;

public class DeleteSubject extends UpdateDeleteMethod {
    private static final Scanner scanner = new Scanner(System.in);
    private final IDatabase<Subject> subjectFileManager = SubjectDatabase.getInstance();
    private final IContainer subjectContainer = new SubjectContainer();

    @Override
    protected String input() {
        System.out.print("\nNhập mã môn học cần xóa: ");
        return CheckValid.checkString(scanner);
    }

    @Override
    protected int findById(String id) {
        for (int i = 0; i<subjectFileManager.getData().size(); i++){
            if(subjectFileManager.getData().get(i).getId().equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean action(int index) {
        if(index != -1) {
            subjectFileManager.getData().remove(index);
            return true;
        }
        return false;
    }

    @Override
    protected void announce(boolean isDelete) {
        if(isDelete)
            System.out.print("Xóa thành công");
        else
            System.out.print("Xóa thất bại! Mã môn học không tồn tại.");
    }
}
