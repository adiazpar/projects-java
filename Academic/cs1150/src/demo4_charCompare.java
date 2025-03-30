/* 
    Alejandro Diaz
    Class: CS1150
    Due: 06/29/23
    Assignment #4

    This program is focused on building skills with comparing strings
    and characters. The idea is to generate a random "PowerBall" lottery
    ticket, and prompt the user to enter values to create another ticket.
    Then, the two tickets will be compared to determine a prize when
    certain conditions are met.

    WHAT I WANT TO DO IN THE FUTURE:
    - Add dynamic ticket length. So instead of just generating 2 letters and 1 number, the user can pick
      how long a ticket can be. Therefore, generate a ticket with 2 letters followed by x amount of numbers,
      and validate user input with 2 letters followed by x amount of numbers.
*/

import java.util.Scanner;

public class demo4_charCompare {
    final static int TICKET_SIZE = 3;

    final static int MIN_NUM = 1;
    final static int MAX_NUM = 22;

    final static int MIN_ASCII = 65;
    final static int MAX_ASCII = 90;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean valid_ticket = false;

        // Print Menu:
        System.out.print("\nCS1150 PowerBall Lottery Game\n");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-40s Win Jackpot\n", "Match 2 Letters and PowerBall Number:");
        System.out.printf("%-40s Win $100.00\n", "Match Only 2 Letters:");
        System.out.printf("%-40s Win $40.00\n", "Match Only 1 Letter:");
        System.out.printf("%-40s Win $20.00\n", "Match Only PowerBall Number:");
        System.out.println("----------------------------------------------------------------");

        // Getting 2 letters from user input:
        System.out.print("\nEnter 2 letters between A and Z: ");
        String response = input.nextLine();

        String serialized_ticket = serialize(response, MIN_NUM, MAX_NUM, MIN_ASCII, MAX_ASCII);

        if (serialized_ticket.charAt(serialized_ticket.length() - 1) == '1') {
            valid_ticket = true;
        }

        if (valid_ticket) {
            Ticket generated_ticket = generate_powerball(TICKET_SIZE, MIN_ASCII, MAX_ASCII, MIN_NUM, MAX_NUM);
            print_winner(generated_ticket, generated_ticket);
        }

        input.close();
    }

    public static String serialize(String res, int min_num, int max_num, int min_ascii, int max_ascii) {
        boolean char0 = false;
        boolean char1 = false;
        boolean char2 = false;
        boolean char3 = false;

        // Parsing the user response into separate characters:
        // Checking the length of the response before running any logic for optimization
        if (res.length() == 3 || res.length() == 4) {
            res = res.toUpperCase().trim();

            char0 = res.charAt(0) >= min_ascii && res.charAt(0) <= max_ascii;
            char1 = res.charAt(1) >= min_ascii && res.charAt(0) <= max_ascii;
            char2 = res.charAt(2) >= '1' && res.charAt(2) <= '9';

            if (res.length() == 4) {
                char3 = res.charAt(3) >= '0' && res.charAt(2) <= '9';
            }
        }

        // Make sure the first two characters are within the ASCII range:
        if (char0 && char1) {
            int digit_compare = -1;

            // Checking to see if there is a double-digit, in this case parse the
            // last two characters in the ticket code to check validity
            if (res.length() == 4 && char2 && char3) {
                digit_compare = Integer.parseInt(res.charAt(2) + "" + res.charAt(3));
            }
            // If not a double-digit, check to see if there is a valid single digit:
            else if (res.length() == 3 && char2) {
                digit_compare = Integer.parseInt(res.charAt(2) + "");
            }
            // Lastly, check to see if this parsed digit is within the POWER-BALL digit range:
            if (digit_compare >= min_num && digit_compare <= max_num) {
                return res + "1";
            }
        }

        // If you got to this point, sorry compadre, but you've entered an INVALID ticket number!!!!
        System.out.printf("""
                                               \s
                        Invalid Input: Must enter a valid ticket number... \
                                               \s
                        - Please enter 2 letters between %c and %c!\
                                               \s
                        - Please enter a digit between %d and %d! \
                                               \s
                        - Format: [Letter][Letter][Digit]""",
                min_ascii, max_ascii, min_num, max_num);

        return res + '0';
    }

    public static Ticket generate_powerball(int size, int min_letter, int max_letter, int min_num, int max_num) {
        // Generate random PowerBall letters:
        int range = (max_letter - min_letter) + 1;
        char pwrChar0 = (char) ((int) (Math.random() * range) + min_letter);
        char pwrChar1 = (char) ((int) (Math.random() * range) + min_letter);

        // Generate random PowerBall ticket number:
        range = (max_num - min_num) + 1;
        int pwrNum = (int) (Math.random() * range) + min_num;

        return new Ticket(pwrChar0);
    }

    public static void print_winner(Ticket user_ticket, Ticket winner_ticket) {
        // Display customer lottery ticket:
        System.out.println("\nCustomer Lottery Ticket: ");
        user_ticket.print();

        // Display PowerBall lottery ticket:
        System.out.println("\nPowerball Lottery Ticket: ");
        winner_ticket.print();

        // Compare & display winning prize, if any:
        boolean num_check = numbers_all_match(user_ticket.getTicketNums(), winner_ticket.getTicketNums());
        boolean letter1_check = (user_ticket.getLetter1() == winner_ticket.getLetter1());
        boolean letter2_check = (user_ticket.getLetter2() == winner_ticket.getLetter2());

        if (letter1_check && letter2_check && num_check) {
            System.out.print("\nPlayer ticket matched 2 letters & Powerball number - Jackpot!\n\n");
        }
        else if (letter1_check && letter2_check && userNum != powerballNum) {

            System.out.print("\nPlayer ticket matched 2 letters - you won $100.00\n\n");
        }
        else if (char1 == powerballLetter1 || char2 == powerballLetter2 && userNum != powerballNum) {

            System.out.print("\nPlayer ticket matched 1 letter - you won $40.00\n\n");
        }
        else if (char1 != powerballLetter1 && char2 != powerballLetter2 && userNum == powerballNum) {

            System.out.print("\nPlayer ticket matched Powerball number - you won $20.00\n\n");
        }
        else {

            System.out.print("\nYour PowerBall ticket did not win.\n\n");
        }
    }

    public static boolean numbers_all_match(int[] num_arr1, int[] num_arr2)
    {
        boolean flag = true;

        for (int i = 0; i < num_arr1.length; i++) {
            if (num_arr1[i] != num_arr2[i]) {
                return false;
            }
        }

        return flag;
    }
}


class Ticket {

    // Ticket member variables:
    // ticket_length is only determined by the object parameters, not manipulated by the user in any way:
    private int ticket_length;

    private final char letter1;
    private char letter2;
    private int[] ticket_nums = new int[ticket_length];

    // CONSTRUCTORS ------------------------------------------ //

    // Ticket constructor : Size of 3 and above
    public Ticket(char ch1, char ch2, int[] nums) {
        this.ticket_length = 2 + nums.length;
        this.letter1 = ch1;
        this.letter2 = ch2;
        this.ticket_nums = nums;
    }

    // Ticket constructor : Size of 2
    public Ticket(char ch1, char ch2) {
        this.ticket_length = 2;
        this.letter1 = ch1;
        this.letter2 = ch2;
    }

    // Ticket constructor : Size of 1
    public Ticket(char ch1) {
        this.ticket_length = 1;
        this.letter1 = ch1;
    }

    // ------------------------------------------------------- //

    // Getters:
    public char getLetter1()        {  return letter1; }
    public char getLetter2()        {  return letter2; }
    public int[] getTicketNums()    {  return ticket_nums;  }

    // Setters:
    // I don't want to use setters here because I don't want tickets to be modifiable...
    // If a user wants a new ticket, they'll need to generate a new one.

    // Other methods:
    public void print() {
        StringBuilder ticket = new StringBuilder();

        if (ticket_length > 0)          // Ticket length is at least 1
            ticket.append(letter1);
        if (ticket_length > 1)          // Ticket length is at least 2
            ticket .append(letter2);
        if (ticket_length > 2)          // Ticket length is 3 and greater
            for (int i = 0; i < (ticket_length - 2); i++) {
                ticket.append(" ").append(ticket_nums[i]);
            }

        System.out.printf(String.valueOf(ticket));
    }
}
