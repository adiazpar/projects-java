/* 
    Alejandro Diaz
    Class: CS1150
    Due: 06/27/23
    Assignment #3

    This program will focus on building logic skills for incorrect input handling.
    The idea is to create a system where customers can book a hotel for a specified
    amount of nights. Calculations will be made for the cost of the specified amount
    of nights, with added taxes and fees.
*/

import java.util.Scanner;

public class demo3_validInput 
{
    // Defining constants:
    public static final String HOTEL1_NAME = "Best Western";
    public static final String HOTEL2_NAME = "Hyatt";
    public static final String HOTEL3_NAME = "Hilton";
    public static final String HOTEL4_NAME = "Wyndham";

    public static final double HOTEL1_RATE = 134.00;
    public static final double HOTEL2_RATE = 152.00;
    public static final double HOTEL3_RATE = 254.00;
    public static final double HOTEL4_RATE = 295.00;

    public static final double TAX_RATE = 0.125;
    public static final int MIN_NIGHTS = 1;
    public static final int MAX_NIGHTS = 14;

    public static void main(String[] args)
    {
        // Declaring local variables:
        Scanner input = new Scanner(System.in);
        boolean valid = true;
        String chosen_hotel_name = "\0";
        double chosen_hotel_rate = 0;


        // Begin Program Logic:
        System.out.printf("Welcome to CS1150 Hotels\n\n");
        System.out.printf("%-6s\t%-16s\t%-10s\n", "Option", "Hotel", "Room Rate");
        System.out.println("-----------------------------------------");
        System.out.printf("%-6s\t%-16s\t$%-10.2f\n", "1", HOTEL1_NAME, HOTEL1_RATE);
        System.out.printf("%-6s\t%-16s\t$%-10.2f\n", "2", HOTEL2_NAME, HOTEL2_RATE);
        System.out.printf("%-6s\t%-16s\t$%-10.2f\n", "3", HOTEL3_NAME, HOTEL3_RATE);
        System.out.printf("%-6s\t%-16s\t$%-10.2f\n", "4", HOTEL4_NAME, HOTEL4_RATE);
    
        System.out.printf("\nHotel to book (select option 1, 2, 3, or 4): ");
        String response = input.nextLine();

        // Menu option will assign chosen variable name and rate, if invalid will set a boolean to false:
        switch(response)
        {
            case "1":
                chosen_hotel_name = HOTEL1_NAME;
                chosen_hotel_rate = HOTEL1_RATE;
                break;
            case "2":
                chosen_hotel_name = HOTEL2_NAME;
                chosen_hotel_rate = HOTEL2_RATE;
                break;
            case "3":
                chosen_hotel_name = HOTEL3_NAME;
                chosen_hotel_rate = HOTEL3_RATE;
                break;
            case "4":
                chosen_hotel_name = HOTEL4_NAME;
                chosen_hotel_rate = HOTEL4_RATE;
                break;
            default:
                valid = false;
                System.out.printf("%s is not a valid hotel menu option. Please run program again, good bye!", response);
                break;
        }

        // If valid menu option chosen, will prompt the user for number of nights and perform calculations:
        if(valid)
        {
            System.out.printf("How many nights (%d to %d)? ", MIN_NIGHTS, MAX_NIGHTS);
            int numNights = input.nextInt();
            
            if(numNights >= MIN_NIGHTS && numNights <= MAX_NIGHTS)
            {
                double priceNights = numNights * chosen_hotel_rate;
                double priceTaxes = priceNights * TAX_RATE;
                double priceTotal = priceNights + priceTaxes;

                System.out.printf("\n------------------------------\n");
                System.out.printf("%-15s\t%s\n", "Hotel", chosen_hotel_name);
                System.out.println("------------------------------");
                System.out.printf("%d %s\t$%-10.2f\n", numNights, "nights", priceNights);
                System.out.printf("%-15s\t$%-10.2f\n", "Taxes & Fees", priceTaxes);
                System.out.println("------------------------------");
                System.out.printf("%-15s\t$%-10.2f\n\n", "Total cost", priceTotal);

            }
            else
            {
                // Printing error statement if invalid number of nights chosen:
                System.out.printf("%d is not a valid number of nights. Please run program again, good bye!", numNights);
            }
        }

        // Closing scanner to avoid memory leaks:
        input.close();
    }
}
