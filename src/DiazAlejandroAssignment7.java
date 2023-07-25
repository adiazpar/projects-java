/* 
    Alejandro Diaz
    Class: CS1150
    Due: 07/13/23
    Assignment #7

    This program is meant to emphasize practice using Methods in Java.
    The idea is to make a system for a user to create / update their
    password. There are specific requirements the password MUST follow
    to be considered valid.
*/

import java.util.Scanner;

public class DiazAlejandroAssignment7
{
    public static void main(String[] args)
    {
        // Define constants:
        final int MAX_ATTEMPTS = 4;
        final String PREVIOUS1 = "secret007";
        final String PREVIOUS2 = "your2eyes";

        // Initialize scanner object & local variables:
        Scanner input = new Scanner(System.in);
        boolean validPassword = false;
        int numAttempts = 0;

        // Main logic flow: while loop to get new passwords:
        while(!validPassword && numAttempts < MAX_ATTEMPTS)
        {
            String newPassword = getPassword(input);

            if(!lengthTest(newPassword))
            {
                printErrorMessage(1);
            }
            else if(!onlyLettersAndDigitsTest(newPassword))
            {
                printErrorMessage(2);
            }
            else if(!containsOneToThreeDigitsTest(newPassword))
            {
                printErrorMessage(3);
            }
            else if(!differentThanLastTwoPasswordsTest(PREVIOUS1, PREVIOUS2, newPassword))
            {
                printErrorMessage(4);
            }
            else
            {
                validPassword = true;
            }

            numAttempts++;
        }

        if(validPassword)
        {
            System.out.println("Your password was successfully updated\n");
        }
        else
        {
            System.out.printf("You've tried %d times - password was not updated - goodbye\n\n", MAX_ATTEMPTS);
        }

    } //main

    // Returns a new password obtained from the user:
    public static String getPassword(Scanner input)
    {
        System.out.print("Enter a new password: ");
        String password = input.nextLine();

        return password;

    } //getPassword

    // Performs test to determine if password is between 6 and 15 characters in length:
    public static boolean lengthTest(String password)
    {
        boolean valid = false;

        if(password.length() >= 6 && password.length() <= 15)
        {
            valid = true;
        }

        return valid;

    } //lengthTest

    // Performs test to determine if password contains only digits & letters:
    public static boolean onlyLettersAndDigitsTest(String password)
    {
        int i = 0;
        boolean flag = false;

        while( i < password.length() && !flag)
        {
            char c = password.charAt(i);
            if( !(Character.isLetterOrDigit(c)) )
            {
                flag = true;
            }

            i++;
        }

        //boolean valid = false;
        //if(!flag) {     valid = true;   }

        return !flag;

    } //onlyLetters&DigitsTest

    // Performs test to determine if password contains at least 1 digit but no more than 3:
    public static boolean containsOneToThreeDigitsTest(String password)
    {
        int numDigits = 0;
        boolean valid = false;

        for(int i = 0; i < password.length(); i++)
        {
            if(Character.isDigit(password.charAt(i)))
            {
                numDigits++;
            }
        }

        if(numDigits >= 1 && numDigits <= 3)
        {
            valid = true;
        }

        return valid;

    } //containsOneToThreeDigitsTest

    // Performs test to determine if password is different than previous two passwords:
    public static boolean differentThanLastTwoPasswordsTest(String prevPass1, String prevPass2, String currPass)
    {
        boolean valid = false;

        if( !(currPass.equals(prevPass1) || currPass.equals(prevPass2)) )
        {
            valid = true;
        }

        return valid;

    } //differentThanLast2PasswordsTest

    /* Display a specific error message based on the following error codes:
     *      1 - length test error
     *      2 - letters/digits test error
     *      3 - must contain 1 digit but not more than 3 digits test error
     *      4 - different than previous passwords error
     */
    public static void printErrorMessage(int errorCode)
    {
        System.out.print("Password does not meet requirement: ");
        switch(errorCode)
        {
            case 1:
                System.out.println("must be between 6 and 15 characters\n");
                break;
            
            case 2:
                System.out.println("must contain only letters and digits\n");
                break;

            case 3:
                System.out.println("must contain at least 1 digit and not more than 3\n");
                break;

            case 4:
                System.out.println("must be different than previous 2 passwords\n");
                break;
        }
    }

}
