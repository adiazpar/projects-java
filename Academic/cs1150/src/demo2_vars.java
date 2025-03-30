/* 
    Alejandro Diaz
    Class: CS1150
    Due: 06/20/23
    Assignment #2

    This program will focus on utilizing user input and assigning that data
    to variables. We will be working with annual compound interest and
    certificates of deposit for any given bank.

    AFTER STUDY REVISIONS:
    - Added a method to fill out user data
    - Added a method to print results
    - Added functionality to create x amount of CD accounts
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class demo2_vars 
{
    // Represents number of times interest is compounded per year:
    final static int NUM_ANNUAL_COMPOUNDS = 1;

    // Data Members:
    final static byte NUM_DATA_MEMBERS = 5;

    public static void main(String[] args)
    {
        // Initializing CD list & scanner:
        Scanner input = new Scanner(System.in);
        ArrayList<double[]> CD_list = new ArrayList<>();

        // Prompting user for the bank name: ------------------------
        System.out.print("Enter name of bank: ");
        String bankName = input.nextLine();
        char response;
        byte index = 0;

        // Prompting user for CD details: -------------------------
        do {
            System.out.printf("\nCD #%d Information:\n---------------------------\n", index+1);
            // User principle, interest, number of years, interest earned, total investment
            CD_list.add(get_information(new double[NUM_DATA_MEMBERS], input));

            System.out.print("\nAdd another CD? [Y/N]: ");
            response = input.next().charAt(0);
            index += 1;

        } while(response == 'y' || response == 'Y');

        input.close();
        print_results(bankName, CD_list);
    }

    public static double[] get_information(double[] data, Scanner in)
    {
        // User principle, interest, number of years, interest earned, total investment
        System.out.print("Enter principle deposited: ");
        data[0] = in.nextDouble();

        // Interest
        System.out.print("Enter annual interest rate: ");
        data[1] = in.nextDouble();

        // Number of years
        System.out.print("Enter number of years: ");
        data[2] = in.nextDouble();

        // Calculating final investment values:
        double exponent = data[2] * NUM_ANNUAL_COMPOUNDS;
        double bracket = 1 + ((data[1]) / NUM_ANNUAL_COMPOUNDS);

        // Total Investment
        data[4] = data[0] * Math.pow(bracket, exponent);

         // Interest Earned
        data[3] = data[4] - data[0];

        return data;
    }

    public static void print_results(String bank, ArrayList<double[]> list)
    {
        // Creating one more array to store total values of all the CD's
        list.add(new double[NUM_DATA_MEMBERS]);

        // Printing information: ------------------------------------
        System.out.print("\n*****************************************************************************\n");
        System.out.printf("\t\tCertificate of Deposits\n" + "\t\t" + bank);
        System.out.print("\n*****************************************************************************\n\n");

        System.out.printf("\t\t\t%-10s\t%-4s\t%-4s\t%-15s\t\t%-12s\n", "Principle", "Rate", "Years", "Interest Earned", "Final Amount");
        System.out.println("-----------------------------------------------------------------------------");

        // Iterate through all the arrays except for the last one, since we created it to store the totals
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.printf("CD #%d\t\t$%-10.2f\t%-4.2f\t%-4d\t$%-15.2f\t$%-12.2f\n", i+1, list.get(i)[0], list.get(i)[1], (int)list.get(i)[2], list.get(i)[3], list.get(i)[4]);
            // Increment the j'th element of the current data array and store it in the final data array of the array list
            for(int j = 0; j < list.get(i).length; j++) {
                list.get(list.size()-1)[j] += list.get(i)[j];
            }
        }

        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("Total\t\t$%-10.2f\t\t\t\t\t$%-15.2f\t$%-12.2f\n\n", list.get(list.size()-1)[0], list.get(list.size()-1)[3], list.get(list.size()-1)[4]);
    }
}
