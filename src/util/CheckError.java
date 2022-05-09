package util;

public class CheckError {
    public static int checkChoose(String check)
    {
        int menu = -1;
        try
        {
            menu = Integer.parseInt(check);
        }
        catch (Exception ex)
        {

        }
        return menu;
    }
}
