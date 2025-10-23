/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//imports
package assessmentjava;

import java.util.*;

/**
 *
 * @author 44785
 */
public class AssessmentJava {
//declaring scanner and global variables

    static Scanner myScan = new Scanner(System.in);
    static String me;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //calling menu method
        System.out.println("Enter your name: ");
        me = myScan.nextLine();
        menu();

    }

    public static void menu() {
//print menu
        System.out.println("Option 1: Generate Username ");
        System.out.println("Option 2: Calculate factorial ");
        System.out.println("Option 3: Quit.");
        //choice menu variable
        int choice;
        do {
            //taking users choice - int 
            choice = myScan.nextInt();
            myScan.nextLine(); //
            //switch menu case
            switch (choice) {
                case 1:

                    string_function();
                    break;
                case 2:
                    number_function();
                    break;
                case 3:
                    System.out.println("Exiting ...");
                    break;
            }
        } while (choice != 3); // continue while choice is not equal to quit
    }

    public static void string_function() {

        System.out.printf("Welcome %s\n", me);
        System.out.println("This is Option 1\n");
        System.out.println("Enter Firstname and Lastname");
        String user = myScan.nextLine();
        create_name(user);

    }

    public static void number_function() {
        int number = 0;
        int factorial = 1;
        int count;
        System.out.printf("Welcome %s\n", me);
        System.out.println("This is Option 2\n");
        System.out.print("Choose a number to calculate factorial: ");

        try {

            number = myScan.nextInt();
            System.out.println("Your number is: " + number);

            if (number > 0) {
                for (count = number; count >= 1; count--) {

                    factorial *= count;
                    System.out.print(count);
                    if (count > 1) {
                        System.out.print(" * ");
                    }

                }
                System.out.print(" = " + factorial);

                //check if number is whole 
            } else if (number <= 0) {
                System.out.println("Enter a positive whole number greater than 0");
            }

            //check if number is greater or smaller than 0
        } catch (InputMismatchException e) {
            System.out.println("Enter a whole number");
            myScan.nextLine();
        }
    }
//create_name method

    public static void create_name(String username) {
        int pos = username.indexOf(" ");
        //string 1 for username first letter uppercase
        String string1 = username.substring(0, 1).toUpperCase();
        //string 2 taking the last name minus the first letter of surname 
        String string2 = username.substring(pos + 1, username.length());
        //concatenate string to match the first letter of first string and second letter of second string capitalized "AB"
        String string3 = string2.substring(0, 1).toUpperCase();
        username = string1.concat(string3 + string2.substring(1, string2.length()));
        //call menu
        System.out.println("Your username is: " + username + "\n");
        menu();
    }
}
