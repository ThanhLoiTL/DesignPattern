package design_pattern.templateMethod;

import model.Person;
import design_pattern.singleton.IDatabase;
import design_pattern.singleton.StudentDatabase;
import util.CheckError;
import util.CheckValid;

import java.util.Scanner;

public class UpdateStudent extends UpdateDeleteMethod {
    private static final Scanner scanner = new Scanner(System.in);
    private final IDatabase<Person> studentFileManager = StudentDatabase.getInstance();

    @Override
    protected String input() {
        System.out.print("\nNhập mã sinh viên cần sửa: ");
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
            setValue(studentFileManager.getData().get(index));
            studentFileManager.writeData(null);
            return true;
        }
        return false;
    }

    @Override
    protected void announce(boolean isSuccess) {
        if(isSuccess)
            System.out.print("Update thành công\n");
        else
            System.out.print("Update thất bại! Mã sinh viên không tồn tại.\n");
    }

    private void setValue(Person student) {
        Integer choose = null;
        do {
            menuUpdate();
            choose = CheckError.checkChoose(scanner.nextLine());
            switch (choose) {
                case 1:
                    System.out.print("\nNhập tên sinh viên: ");
                    student.setName(CheckValid.checkString(scanner));
                    break;
                case 2:
                    System.out.print("\nNhập tuổi sinh viên: ");
                    student.setAge(CheckValid.checkInt(scanner));
                    break;
                case 3:
                    System.out.print("\nNhập giới tính sinh viên: ");
                    student.setGender(CheckValid.checkString(scanner));
                    break;
                case 4:
                    System.out.print("\nNhập địa chỉ sinh viên: ");
                    student.setAddress(CheckValid.checkString(scanner));
                    break;
                case 5:
                    System.out.print("\nNhập email sinh viên: ");
                    student.setEmail(CheckValid.checkString(scanner));
                    break;
                case 6:
                    System.out.print("\nNhập số điên thoại sinh viên: ");
                    student.setPhoneNumber(CheckValid.checkString(scanner));
                    break;
                case 0:
                    System.out.println("Exited!\n");
                    break;
                default:
                    System.out.println("Invalid! please choose action in below menu.\n");
                    break;
            }
        }while (choose != 0);
    }
    private void menuUpdate() {
        System.out.println("\n-------Update sinh viên------");
        System.out.println("-------------Menu------------");
        System.out.println("1. Update tên.");
        System.out.println("2. Update tuổi.");
        System.out.println("3. Update giới tính.");
        System.out.println("4. Update địa chỉ.");
        System.out.println("5. Update email.");
        System.out.println("6. Update số điện thoại.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}
