package design_pattern.templateMethod;

import model.Person;
import model.Teacher;
import design_pattern.singleton.IDatabase;
import design_pattern.singleton.TeacherDatabase;
import util.CheckError;
import util.CheckValid;

import java.util.Scanner;

public class UpdateTeacher extends UpdateDeleteMethod {
    private static final Scanner scanner = new Scanner(System.in);
    private final IDatabase<Person> teacherFileManager = TeacherDatabase.getInstance();

    @Override
    protected String input() {
        System.out.print("\nNhập mã giảng viên cần sửa: ");
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
            setValue((Teacher) teacherFileManager.getData().get(index));
            teacherFileManager.writeData(null);
            return true;
        }
        return false;
    }

    @Override
    protected void announce(boolean isSuccess) {
        if(isSuccess)
            System.out.println("Update thành công\n");
        else
            System.out.println("Update thất bại! Mã sinh viên không tồn tại.\n");
    }

    private void setValue(Teacher teacher) {
        Integer choose = null;
        do {
            menuUpdate();
            choose = CheckError.checkChoose(scanner.nextLine());
            switch (choose) {
                case 1:
                    System.out.print("\nNhập tên giảng viên: ");
                    teacher.setName(CheckValid.checkString(scanner));
                    break;
                case 2:
                    System.out.print("\nNhập tuổi giảng viên: ");
                    teacher.setAge(CheckValid.checkInt(scanner));
                    break;
                case 3:
                    System.out.print("\nNhập giới tính giảng viên: ");
                    teacher.setGender(CheckValid.checkString(scanner));
                    break;
                case 4:
                    System.out.print("\nNhập địa chỉ giảng viên: ");
                    teacher.setAddress(CheckValid.checkString(scanner));
                    break;
                case 5:
                    System.out.print("\nNhập email giảng viên: ");
                    teacher.setEmail(CheckValid.checkString(scanner));
                    break;
                case 6:
                    System.out.print("\nNhập số điên thoại giảng viên: ");
                    teacher.setPhoneNumber(CheckValid.checkString(scanner));
                    break;
                case 7:
                    System.out.print("\nNhập khoa của giảng viên: ");
                    teacher.setDepartment(CheckValid.checkString(scanner));
                    break;
                case 8:
                    System.out.print("\nNhập số lương của giảng viên: ");
                    teacher.setSalary(CheckValid.checkInt(scanner));
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
        System.out.println("\n------Update giảng viên------");
        System.out.println("-------------Menu------------");
        System.out.println("1. Update tên.");
        System.out.println("2. Update tuổi.");
        System.out.println("3. Update giới tính.");
        System.out.println("4. Update địa chỉ.");
        System.out.println("5. Update email.");
        System.out.println("6. Update số điện thoại.");
        System.out.println("7. Update khoa.");
        System.out.println("8. Update lương.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}

