package design_pattern.templateMethod;

import model.Person;
import design_pattern.singleton.IDatabase;
import design_pattern.singleton.StudentDatabase;
import util.CheckValid;

import java.util.Scanner;

public class DeleteStudent extends UpdateDeleteMethod {
    private static final Scanner scanner = new Scanner(System.in);
    private final IDatabase<Person> studentFileManager = StudentDatabase.getInstance();

    @Override
    protected String input() {
        System.out.print("\nNhập mã sinh viên cần xóa: ");
        return CheckValid.checkString(scanner);
    }

    @Override
    protected int findById(String id) {
        for (int i = 0; i<studentFileManager.getData().size(); i++){
            if(studentFileManager.getData().get(i).getId().equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean action(int index) {
        if(index != -1) {
            studentFileManager.getData().remove(index);
            studentFileManager.writeData(null);
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
