/* 
    Alejandro Diaz
    Class: CS1150
    Due: 06/20/23
    Assignment #2

    This program will focus on utilizing user input and assigning that data
    to variables. We will be working with annual compound interest and
    certificates of deposit for any given bank.
*/

import java.util.Scanner;
import java.lang.Math;

public class DiazAlejandroAssignment2 
{
    public static void main(String[] args)
    { 
        // Represents number of times interest is compounded per year:
        final int numCompoundsAnnual = 1;

        // Initializing other variables & scanner:
        Scanner input = new Scanner(System.in);
        double interest_percentage = 0;
        double exponent = 0;
        double bracket = 0;


        // Prompting user for the bank name: ------------------------
        System.out.print("Enter name of bank: ");
        String bankName = input.nextLine();
        

        // Prompting user for CD#1 details: -------------------------
        System.out.printf("\nCD #1 Information:\n---------------------------\n");
        
        System.out.print("Enter principle deposited: ");
        double principle1 = input.nextDouble();

        System.out.print("Enter annual interest rate: ");
        double interest1 = input.nextDouble();

        System.out.print("Enter number of years: ");
        int years1 = input.nextInt();

        // Calculating final investment values:
        interest_percentage = interest1/100;
        exponent = years1 * numCompoundsAnnual;
        bracket = 1 + (interest_percentage / numCompoundsAnnual);

        double investment1 = principle1 * Math.pow(bracket, exponent);
        double earnedInterest1 = investment1 - principle1;

        
        // Prompting user for CD#2 details: -------------------------
        System.out.printf("\nCD #2 Information:\n---------------------------\n");
    
        System.out.print("Enter principle deposited: ");
        double principle2 = input.nextDouble();

        System.out.print("Enter annual interest rate: ");
        double interest2 = input.nextDouble();

        System.out.print("Enter number of years: ");
        int years2 = input.nextInt();

        // Calculating final investment values:
        interest_percentage = interest2/100;
        exponent = years2 * numCompoundsAnnual;
        bracket = 1 + (interest_percentage / numCompoundsAnnual);

        double investment2 = principle2 * Math.pow(bracket, exponent);
        double earnedInterest2 = investment2 - principle2;

        input.close();


        // Printing information: ------------------------------------
        System.out.printf("\n*****************************************************************************\n");
        System.out.printf("\t\tCertificate of Deposits\n" + "\t\t" + bankName);
        System.out.printf("\n*****************************************************************************\n\n");

        System.out.printf("\t%-10s\t%-4s\t%-4s\t%-15s\t\t%-12s\n", "Principle", "Rate", "Years", "Interest Earned", "Final Amount");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("CD #1\t%-10.2f\t%-4.2f\t%-4d\t%-15.2f\t\t%-12.2f\n", principle1, interest1, years1, earnedInterest1, investment1);
        System.out.printf("CD #2\t%-10.2f\t%-4.2f\t%-4d\t%-15.2f\t\t%-12.2f\n", principle2, interest2, years2, earnedInterest2, investment2);
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("Totals\t%-10.2f\t\t\t%-15.2f\t\t%-12.2f\n\n", principle1 + principle2, earnedInterest1 + earnedInterest2, investment1 + investment2);
    }
}
