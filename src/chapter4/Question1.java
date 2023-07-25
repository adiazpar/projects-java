package chapter4;
import java.util.Scanner;

public class Question1
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        boolean validPassword = false;
        String correctPassword = "Indian Summer";

        System.out.println("Enter password:");

        while(!validPassword)
        {
            String enteredPassword = input.nextLine();

            if( enteredPassword.equals(correctPassword) )
            {
                validPassword = true;
                System.out.printf("%c%c\n", enteredPassword.charAt(0), enteredPassword.charAt(enteredPassword.length() - 1));
            }
            else
            {
                System.out.printf("%s is not a valid password, try again. Enter password again:\n", enteredPassword);
            }
        }

        input.close();
    }
}