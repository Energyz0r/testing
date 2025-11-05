/*
Software Development - Assessment Task 2 - Outcome 2
Name: Marinache Doru Cristian
Date: 30.10.2025
Description: 
This Java program is a simple menu-driven console application that performs two main functions for a user:

Generate a Username
The program asks the user to input their first and last name (it can also handle multiple middle names).
It then creates a username made from the first letter of the first name (capitalized) and the full last name (also capitalized).
If the input does not contain at least two words, it displays an error message and returns to the menu.

Calculate a Factorial
The program prompts the user to enter a positive whole number.
It then calculates and displays the factorial of that number using BigInteger (so it can handle very large results without overflow).
If the user enters a non-integer or a number less than or equal to zero, the program displays a validation message asking for a valid positive whole number.

Quit Option

The user can choose to exit the program from the main menu.

***Libraries***
scanner -- java.util.Scanner; * imports everything from java.util.*
biginteger -- java.math.BigInteger; imports big integer used for large number calculations
 */
package assessmentjava;
//imports

import java.util.*;
import java.math.BigInteger;

public class AssessmentJava {

    // declaring myScan as a global variables because multiple uses during the execution
    static Scanner myScan = new Scanner(System.in);
    //declaring global variables
    static String me;

    /*main method - Asks the user to enter their name, stores it in the global variable 'me', 
   and then calls the menu() method to start the main program loop. */
    public static void main(String[] args) {
        // TODO code application logic here
        //calling menu method
        System.out.print("Enter your name: ");
        me = myScan.nextLine();
        menu();

    }

    /*
The menu method displays the main menu options and handles user choices. It repeatedly prompts the user to select
    an option and calls the appropriate method: option 1 runs string_function to generate a username, option 2 runs 
    number_function to calculate a factorial, and option 3 exits the program. The loop continues until the user chooses to quit.
     */
    public static void menu() {

     
        //choice menu variable
        int choice;

        do {//print menu
            System.out.println("Option 1: Generate Username");
            System.out.println("Option 2: Calculate factorial");
            System.out.println("Option 3: Quit");
            System.out.print("Pick Option (1, 2, or 3): ");

            try {
                choice = myScan.nextInt();
                myScan.nextLine(); // consume the newline

                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid option. Please enter 1, 2, or 3.\n");
                    continue; // re-prompt
                }

                switch (choice) {
                    case 1:
                        string_function();
                        break;
                    case 2:
                        number_function();
                        break;
                    case 3:
                        System.out.println("Exiting ...");
                        System.exit(0);
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number: 1, 2, or 3.\n");
                myScan.nextLine(); // clear the invalid input
            }

        } while (true);
    }

    /*
The string_function method handles option 1 from the menu. It greets the user by name, 
asks for their first and last name, and sends this input to the create_name method 
to generate and display a username.
     */
    public static void string_function() {

        System.out.printf("Welcome %s\n", me);
        System.out.println("This is Option 1\n");
        System.out.println("Enter Firstname and Lastname");
        //get all input tockens
        String user = myScan.nextLine();
        create_name(user);

    }

    /*
The number_function method handles option 2 from the menu. It asks the user to enter a positive whole number, 
calculates the factorial using BigInteger to handle very large numbers, and displays the result. It includes
validation to ensure the input is positive and catches InputMismatchException if the input is not a whole number. 
It then returns to the main menu.
     */
    public static void number_function() {
        //declare local variables
        int number = 0;
        //use big integers from library java.math.BigInteger to calculate bigger integers ( int was too small )
        BigInteger factorial = BigInteger.ONE;
        int count;
        //prints
        System.out.printf("Welcome %s\n", me);
        System.out.println("This is Option 2\n");
        System.out.print("Choose a number to calculate factorial: ");
        //catch error when the number entered is not a whole number ( integer )
        try {
            //take the number input
            number = myScan.nextInt();
            //print number
            System.out.println("Your number is: " + number);
            //check if the number is bigger than 0
            if (number > 0) {
                for (count = number; count >= 1; count--) {

                    factorial = factorial.multiply(BigInteger.valueOf(count));
                    System.out.print(count);
                    if (count > 1) {
                        System.out.print(" x ");
                    }

                }
                System.out.print(" = " + factorial + "\n");

                //check if number is whole 
            } else if (number <= 0) {
                System.out.println("Enter a positive whole number greater than 0");
            }

            //check if number is greater or smaller than 0
        } catch (InputMismatchException e) {
            System.out.println("Enter a whole number");
            myScan.nextLine();// address a new line - menu was not printing
        }
        menu();
    }

    /* 
The create_name method generates a username based on the user’s full name. It takes the first letter of the first name
and concatenates it with the last name, both capitalized. For example, “Pepe Bunny Rabbit” becomes “PRabbit”. 
If the user does not enter both a first and last name, it shows an error and returns to the menu.
     */
    public static void create_name(String username) {
        int pos = username.lastIndexOf(" ");
        // if pos is equal to -1 print invalid input and return to menu
        if (pos == -1) {
            System.out.println("Invalid input. Please include both first and last name.\n");
            menu();
            return;
        }
        //extract username first letter uppercase
        String String1 = username.substring(0, 1).toUpperCase();
        //get the name after the last space " "
        String String2 = username.substring(pos + 1);
        String2 = String2.substring(0, 1).toUpperCase() + String2.substring(1); // capitalize first letter from second word 

        //concat first letter and lastName
        username = String1.concat(String2);
        //call menu
        System.out.println("Your username is: " + username + "\n");
        menu();// call menu at the end
    }
}
