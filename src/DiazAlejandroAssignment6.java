/* 
    Alejandro Diaz
    Class: CS1150
    Due: 07/06/23
    Assignment #6

    This program intends to strengthen skills when working with loops.
    The idea is to simulate a simple vending machine, where the user
    is given snack options to pick from. The user will be allowed to 
    purchase 1 to 3 snacks in one transaction, then a receipt is 
    displayed for each individual snack purchase. Entering the correct
    escape sequence and password will print a report of total 
    purchases and exit the program.
*/

import java.util.Scanner;

public class DiazAlejandroAssignment6 
{
    static final String SNACKS[] = {"Pop Tarts", "Pretzels", "Funyuns "};
    static final double PRICES[] = {0.90, 0.70, 1.50};

    static final int SHUTDOWN_NUM = 999;
    static final String SHUTDOWN_PASS = "COOKIES";

    public static void main(String[] args)
    {
        // Declare constants:
        final int MIN_SNACKS = 1;
        final int MAX_SNACKS = 3;

        // Initialize local variables:
        Scanner input = new Scanner(System.in);
        boolean inService = true;

        double totalSales = 0;
        int totalSnacks = 0;
        int amounts_of_each[] = {0, 0, 0};

        while(inService)
        {
            printMenu();
            System.out.printf("How many snacks would you like?    Limit is %d: ", MAX_SNACKS);
            int numSnacks = validateInt(input, MIN_SNACKS, MAX_SNACKS);

            if(numSnacks == SHUTDOWN_NUM)
            {
                // Prompt for shutdown password:
                System.out.print("Enter shutdown password: ");
                String password = input.nextLine();

                if(password.equals(SHUTDOWN_PASS))
                {
                    inService = false;
                }
                else
                {
                    System.out.println("Invalid password - returning to snack machine...");
                }
            }
            else
            {
                for(int i = 0; i < numSnacks; i++)
                {
                    System.out.print("\nEnter snack selection: ");
                    String selection = getVendingSelection(input, SNACKS.length, PRICES.length);

                    // Printing receipt for selection made & calculating totals:
                    System.out.println("\n------------------------------------------");
                    System.out.printf("-------------- Selection %s --------------\n", selection);
                    System.out.println("------------------------------------------");

                    for(int j = 0; j < SNACKS.length; j++)
                    {
                        for(int k = 0; k < PRICES.length; k++)
                        {
                            if( (Character.getNumericValue(selection.charAt(0)) == (j + 1)) && ((char)(k + 65) == selection.charAt(1)) )
                            {
                                totalSales += PRICES[j];
                                totalSnacks++;
                                amounts_of_each[j]++;

                                System.out.printf("\tSnack Item:\t%s\n", SNACKS[j]);
                                System.out.printf("\tSnack Price:\t$%.2f\n", PRICES[j]);
                            }
                        }
                    }
                }

                System.out.println("\n------------------------------------------");
                System.out.println("\tThank you for your business!");
                System.out.println("------------------------------------------");
            }
        }

        // Printing Report:
        printReport(totalSales, totalSnacks, SNACKS, amounts_of_each);

        input.close();
    }

    public static void printMenu()
    {
        System.out.print("\n*******************************************\n");
        System.out.print("\t\tSnack Machine\n");
        System.out.print("*******************************************\n");

        for(int i = 0; i < SNACKS.length; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.printf("%s\t", SNACKS[i]);
            }
            System.out.print("\n");

            for(int k = 0; k < 3; k++)
            {
                System.out.printf("%d%c $%.2f\t", i + 1, (char)(k + 65), PRICES[i]);
            }
            System.out.print("\n");

            System.out.println("-------------------------------------------");
        }

        System.out.println("");
    }

    public static void printReport(double numSales, int numItems, String[] items, int[] amounts)
    {
        System.out.printf("\nTotal Sales = $%.2f\n", numSales);
        System.out.printf("Total Number Items Sold = %d\n", numItems);
        for(int i = 0; i < items.length; i++)
        {
            System.out.printf("%d %s\n", amounts[i], items[i]);
        }
        System.out.println("");
    }

    public static int validateInt(Scanner in, int min, int max)
    {
        boolean validated = false;
        int num = 0;

        while(!validated)
        {
            String testNum = in.nextLine();

            try
            {
                num = Integer.valueOf(testNum);
                if((num >= min && num <= max) || (num == SHUTDOWN_NUM))
                {
                    validated = true;
                }
                else
                {
                    System.out.printf("Invalid entry. Enter a number between %d and %d: ", min, max);
                }
            }
            catch (NumberFormatException ex)
            {
                System.out.printf("Invalid entry. Enter a number between %d and %d: ", min, max);
            }
        }

        return num;
    }

    public static String getVendingSelection(Scanner in, int numItems, int numPrices)
    {
        String result = "NULL";

        boolean validSelection = false;
        int row = 0;
        char col = '0';

        // Validate the selection made:
        do
        {
            result = in.nextLine().toUpperCase();

            if(result.length() == 2)
            {
                row = Character.getNumericValue(result.charAt(0));
                col = result.charAt(result.length() - 1);
            }

            if( (row > numItems || row <= 0) || ((int)col > (65 + numPrices - 1) || (int)col < 65))
            {
                System.out.print("Invalid entry. Enter 1A-1C, 2A-2C or 3A-3C: ");
            }
            else
            {
                validSelection = true;
            }   
        }
        while(!validSelection);

        return result;
    }
}
