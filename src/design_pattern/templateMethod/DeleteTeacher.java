package design_pattern.templateMethod;

import model.Person;
import design_pattern.singleton.IDatabase;
import design_pattern.singleton.TeacherDatabase;
import util.CheckValid;

import java.util.Scanner;

public class DeleteTeacher extends UpdateDeleteMethod {
    private static final Scanner scanner = new Scanner(System.in);
    private final IDatabase<Person> teacherFileManager = TeacherDatabase.getInstance();

    @Override
    protected String input() {
        System.out.print("\nNhập mã giảng viên cần xóa: ");
        return CheckValid.checkString(scanner);
    }

    @Override
    protected int findById(String id) {
        for (int i = 0; i<teacherFileManager.getData().size(); i++){
            if(teacherFileManager.getData().get(i).getId().equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean action(int index) {
        if(index != -1) {
            teacherFileManager.getData().remove(index);
            teacherFileManager.writeData(null);
            return true;
        }
        return false;
    }

    @Override
    protected void announce(boolean isSuccess) {
        if(isSuccess)
            System.out.print("Xóa thành công");
        else
            System.out.print("Xóa thất bại! Mã sinh viên không tồn tại.");
    }
}
