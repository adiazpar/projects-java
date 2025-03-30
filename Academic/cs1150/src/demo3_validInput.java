/* 
    Alejandro Diaz
    Class: CS1150
    Due: 06/27/23
    Assignment #3

    This program will focus on building logic skills for incorrect input handling.
    The idea is to create a system where customers can book a hotel for a specified
    amount of nights. Calculations will be made for the cost of the specified amount
    of nights, with added taxes and fees.

    AFTER STUDY ADDITIONS:
    - Created HOTELS and RATES arrays
    - Created new functions for error checking
*/

import java.util.Scanner;

public class demo3_validInput 
{
    // Defining constants:
    public static final String[] HOTELS = {"Best Western", "Hyatt", "Hilton", "Wyndham", "Double Tree"};
    public static final double[] RATES = {134, 152, 254, 295, 175};

    public static final double TAX_RATE = 0.125;
    public static final int MIN_NIGHTS = 1;
    public static final int MAX_NIGHTS = 14;

    public static void main(String[] args)
    {
        // Declaring local variables:
        Scanner input = new Scanner(System.in);
        boolean valid = false;

        // Begin Program Logic:
        System.out.print("Welcome to CS1150 Hotels\n\n");
        System.out.printf("%-6s\t%-16s\t%-10s\n", "Option", "Hotel", "Room Rate");
        System.out.println("-----------------------------------------");
        for(int i = 0; i < HOTELS.length; i++) {
            System.out.printf("%-6d\t%-16s\t$%-10.2f\n", i+1, HOTELS[i], RATES[i]);
        }

        System.out.print("\nHotel to book (select option #): ");
        String chosen_hotel_option = input.nextLine();

        // Checking if the user entered a valid hotel name. If not, attempt to parse the response
        // into an integer within the appropriate bounds...

        int parsed_hotel_option = isMatch(chosen_hotel_option, HOTELS);

        if (parsed_hotel_option != -1) {
            valid = true;
        }
        else {
            parsed_hotel_option = parse_response(chosen_hotel_option, 1, HOTELS.length-1) - 1;
            if (parsed_hotel_option > -1)
                valid = true;
        }

        // If valid menu option chosen, will prompt the user for number of nights and perform calculations:
        if(valid)
        {
            System.out.printf("How many nights (%d to %d)? ", MIN_NIGHTS, MAX_NIGHTS);
            int num_nights = parse_response(input.nextLine(), MIN_NIGHTS, MAX_NIGHTS);
            
            if(num_nights != -1)
            {
                double priceNights = num_nights * RATES[parsed_hotel_option];
                double priceTaxes = priceNights * TAX_RATE;
                double priceTotal = priceNights + priceTaxes;

                System.out.print("\n------------------------------\n");
                System.out.printf("%-15s\t%s\n", "Hotel", HOTELS[parsed_hotel_option]);
                System.out.println("------------------------------");
                System.out.printf("%d %s\t\t$%-10.2f\n", num_nights, "night(s)", priceNights);
                System.out.printf("%-15s\t$%-10.2f\n", "Taxes & Fees", priceTaxes);
                System.out.println("------------------------------");
                System.out.printf("%-15s\t$%-10.2f\n\n", "Total cost", priceTotal);
            }
        }

        // Closing scanner to avoid memory leaks:
        input.close();
    }

    public static int parse_response(String res, int min, int max)
    {
        if(!is_numeric_in_range(res, min, max))
        {
            System.out.printf("%s is not a valid option. Please run the program again, goodbye!", res);
            res = "-1";
        }

        return Integer.parseInt(res);
    }

    // Check to see that a string is a numeric value:
    public static boolean is_numeric_in_range(String str, int min_len, int max_len) {
        try {
            int num = Integer.parseInt(str);
            return num >= min_len && num <= max_len;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Check to see if a string matches any of the strings in an array:
    public static int isMatch(String str, String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equalsIgnoreCase(str)) {
                return i;
            }
        }
        return -1;
    }
}
