package chapter2;
import java.util.Scanner;

public class exampleScanner 
{
    public static void main(String[] args) 
    {
    // Create a Scanner object to read input from the user
    Scanner userInput = new Scanner (System.in);

    // Prompt the user for the number of students in the class
    System.out.print ("Enter the number of students in the class: ");
    int numberOfStudents = userInput.nextInt();

    // Do something interesting with this information
    System.out.println ("Can you believe " + numberOfStudents + " students showed up?");

    // Close the scanner
    userInput.close();
    }
}
