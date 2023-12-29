/* 
    Alejandro Diaz
    Class: CS1150
    Due: 06/29/23
    Assignment #4

    This program is focused on building skills with comparing strings
    and characters. The idea is to generate a random "powerball" lottery
    ticket, and prompt the user to enter values to create another ticket.
    Then, the two tickets will be compared to determine a prize when
    certain conditions are met.
*/

import java.util.Scanner;

public class demo4_charCompare 
{
    public static void main(String[] args)
    {
        final int MIN_POWERBALL = 1;
        final int MAX_POWERBALL = 22;

        final int MIN_ASCII = 65;
        final int MAX_ASCII = 90;

        Scanner input = new Scanner(System.in);
        boolean validTicket = false;
        char letter1 = '0';
        char letter2 = '0';
        int userNum = 0;

        // Print Menu:
        System.out.printf("\nCS1150 Powerball Lottery Game\n");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-40s Win Jackpot\n", "Match 2 Letters and Powerball Number:");
        System.out.printf("%-40s Win $100.00\n", "Match Only 2 Letters:");
        System.out.printf("%-40s Win $40.00\n", "Match Only 1 Letter:");
        System.out.printf("%-40s Win $20.00\n", "Match Only Powerball Number:");
        System.out.println("----------------------------------------------------------------");

        // Getting 2 letters from user input:
        System.out.printf("\nEnter 2 letters between A and Z: ");
        String responseLetters = input.nextLine().toUpperCase();

        letter1 = responseLetters.charAt(0);
        letter2 = responseLetters.charAt(1);

        if((responseLetters.length() == 2) && (letter1 >= 'A' && letter1 <= 'Z') && (letter2 >= 'A' && letter2 <= 'Z'))
        {
            // Getting 1 number from user input:
            System.out.printf("Enter 1 Powerball number between %d and %d: ", MIN_POWERBALL, MAX_POWERBALL);
            userNum = input.nextInt();

            if(userNum >= MIN_POWERBALL && userNum <= MAX_POWERBALL)
            {
                validTicket = true;
            }
            else
            {
                System.out.printf("Invalid entry - need number between %d and %d for this special Powerball\n", MIN_POWERBALL, MAX_POWERBALL);
            }
        }
        else
        {
            System.out.println("Invalid entry - need 2 letters between A and Z for this special Powerball");
        }

        if(validTicket)
        {
            // Generate random powerball ticket number:
            int range = (MAX_POWERBALL - MIN_POWERBALL) + 1;
            int powerballNum = (int)(Math.random() * range) + MIN_POWERBALL;

            // Generate random powerball letters:
            range = (MAX_ASCII - MIN_ASCII) + 1;
            char powerballLetter1 = (char)((int)(Math.random() * range) + MIN_ASCII);
            char powerballLetter2 = (char)((int)(Math.random() * range) + MIN_ASCII);
            
            // Display customer lottery ticket:
            System.out.printf("\nCustomer Lottery Ticket \n%c %c %d\n", letter1, letter2, userNum);
            
            // Display powerball lottery ticket:
            System.out.printf("\nPowerball Lottery Ticket \n%c %c %d\n", powerballLetter1, powerballLetter2, powerballNum);


            // Compare & display winning prize, if any:
            if(letter1 == powerballLetter1 && letter2 == powerballLetter2 && userNum == powerballNum)
            {
                System.out.printf("\nPlayer ticket matched 2 letters & Powerball number - Jackpot!\n\n");
            }
            else if(letter1 == powerballLetter1 && letter2 == powerballLetter2 && userNum != powerballNum)
            {
                System.out.printf("\nPlayer ticket matched 2 letters - you won $100.00\n\n");
            }
            else if(letter1 == powerballLetter1 || letter2 == powerballLetter2 && userNum != powerballNum)
            {
                System.out.printf("\nPlayer ticket matched 1 letter - you won $40.00\n\n");
            }
            else if(letter1 != powerballLetter1 && letter2 != powerballLetter2 && userNum == powerballNum)
            {
                System.out.printf("\nPlayer ticket matched Powerball number - you won $20.00\n\n");
            }
            else
            {
                System.out.printf("\nYour Powerball ticket did not win.\n\n");
            }
        }

        input.close();
    }
}
