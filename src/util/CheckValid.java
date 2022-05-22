package util;

import java.util.Scanner;

public class CheckValid {
    public static String checkString(Scanner scanner)
    {
        String s;
        do{
            s = scanner.nextLine().trim();
            if(s == null || s == "") {
                System.out.print("Nhập sai! Vui lòng nhập lại: ");
            }
        }while (s == null || s == "");
        return s;
    }

    public static int checkInt(Scanner scanner)
    {
        int number = -1;
        do{
            try
            {
                number = Integer.parseInt(scanner.nextLine().trim());
            }
            catch (Exception ex)
            {
                System.out.print("Nhập sai! Vui lòng nhập lại: ");
            }
        }while (number == -1);
        return number;
    }
    public static float checkFloat(Scanner scanner)
    {
        float number = -1;
        do{
            try
            {
                number = Float.parseFloat(scanner.nextLine().trim());
            }
            catch (Exception ex)
            {
                System.out.print("Nhập sai! Vui lòng nhập lại: ");
            }
        }while (number == -1);
        return number;
    }
}
