/* 
    Alejandro Diaz
    Class: CS1150
    Due: 07/20/23
    Assignment #8

    This program serves as practice using arrays with methods. 
    The idea is to create 2 arrays in which data is stored. 
    I will write methods that uses this data to perform calculations
    & methods that simply displays the data held within the arrays.

    The second part of the program deals with objects and classes.
    I will access various different objects from main.
*/

public class DiazAlejandroAssignment8 
{
    public static void main(String[] args)
    {
        // PART 1 ------------------------------ //

        // Initializing snowfall arrays:
        String[] resorts = {"Vail", "Steamboat", "Wolf Creek", "Keystone", "Breckenridge", "Telluride", "Purgatory"};
        double[] snowAmounts = {189.2, 154.4, 430.2, 159.9, 184.6, 167.7, 260.2};

        // Snowfall local variables:
        int highestIndex = findResortWithMostSnowfall(snowAmounts);
        double average = computeAverageSnowfall(snowAmounts);

        // Snowfall logic:
        displaySnowfallAmounts(snowAmounts, resorts);
        System.out.printf("Total snowfall for all resorts is %.2f\n", computeTotalSnowfall(snowAmounts));
        System.out.printf("Average snowfall for all resorts is %.2f\n\n", average);
        System.out.printf("Ski resort with most snow is %s with %.2f inches per year\n\n", resorts[highestIndex], snowAmounts[highestIndex]);
        displayResortsWithAboveAverageSnowfall(snowAmounts, resorts, average);

        System.out.println("\n");

        // PART 2 ------------------------------ //

        // Initializing Dogs array:
        Dog[] dogArray = new Dog[5];
        Dog dog1 = new Dog("Rover", "woof woof");
        Dog dog2 = new Dog("Max", "arf arf arf");
        Dog dog3 = new Dog("Tiny", "yap yap yap");
        Dog dog4 = new Dog("Trooper", "ruff ruff ruff");
        Dog dog5 = new Dog("Magoo", "bow wow bow wow");

        // Populating dog array with newly created objects:
        dogArray[0] = dog1;
        dogArray[1] = dog2;
        dogArray[2] = dog3;
        dogArray[3] = dog4;
        dogArray[4] = dog5;

        makeDogsBark(dogArray);
    }    

    // -------------------------- METHODS -------------------------- //

    // Displays the yearly snowfall amount for each ski resort:
    public static void displaySnowfallAmounts(double[] snowAmounts, String[] resorts)
    {
        System.out.println("\n------------------------------------");
        System.out.printf("%-20s%-20s\n", "Resort", "Yearly Snowfall");
        System.out.println("------------------------------------");

        for(int i = 0; i < snowAmounts.length; i++)
        {
            System.out.printf("%-20s%-20.2f\n", resorts[i], snowAmounts[i]);
        }

        System.out.println("");
    }

    // Computes and returns the total of all snowfall amounts in the array:
    public static double computeTotalSnowfall(double[] snowAmounts)
    {
        double total = 0;
        for(int i = 0; i < snowAmounts.length; i++)
        {
            total += snowAmounts[i];
        }

        return total;
    }

    // Compputes & returns the average of the values in the array
    // Must use the method computeTotalSnowfall to help compute average:
    public static double computeAverageSnowfall(double[] snowAmounts)
    {
        double total = computeTotalSnowfall(snowAmounts);
        double average = total / snowAmounts.length;
        
        return average;
    }

    // Find index of the ski resort that gets the most amount of snow
    // Return the array index of that ski resort: 
    public static int findResortWithMostSnowfall(double[] snowAmounts)
    {
        double highestValue = 0;
        int indexForHighestValue = 0;

        for(int i = 0; i < snowAmounts.length; i++)
        {
            if(snowAmounts[i] > highestValue)
            {
                highestValue = snowAmounts[i];
                indexForHighestValue = i;
            }
        }

        return indexForHighestValue;
    }

    // Displays all ski resorts that have a snowfall amount above the avergae:
    public static void displayResortsWithAboveAverageSnowfall(double[] snowAmounts, String[] resorts, double average)
    {
        for(int i = 0; i < snowAmounts.length; i++)
        {
            if(snowAmounts[i] > average)
            {
                System.out.printf("%s gets %.2f inches which is above average\n", resorts[i], snowAmounts[i]);
            }
        }
    }

    // Make each dog in the array bark by calling each dog's bark method:
    public static void makeDogsBark(Dog[] dogArray)
    {
        System.out.println("Making the dogs bark!");
        System.out.println("-------------------------------------");

        for(int i = 0; i < dogArray.length; i++)
        {
            System.out.printf("%s barks like this: %s\n", dogArray[i].getName(), dogArray[i].getBark());
        }

        System.out.println("");
    }

    // ------------------------ END METHODS ------------------------ //
} // Assignment8

class Dog
{
    private String name;
    private String bark;

    public Dog(String name, String bark)
    {
        this.name = name;
        this.bark = bark;
    }

    public String getName() {   return name;    }
    public String getBark() {   return bark;    }
    
    public void setName(String name) {  this.name = name;    }
    public void setBark(String bark) {  this.bark = bark;   }
}
