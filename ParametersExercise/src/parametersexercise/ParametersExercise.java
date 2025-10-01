package parametersexercise;
import java.util.*;

/* Asmat Ullah
This program will demonstrate the use of parameters to send information from the
enterDetails method to the displayDetails method.
07/10/16 */
//
//You will need to complete this program to use the Scanner object for input
//
public class ParametersExercise {
 static Scanner myScan = new Scanner(System.in);
    public static void main(String args[]) {
        /* This is the starting point. */
       
        enterDetails();
    }

    public static void enterDetails() {
        /* This will ask the user to input their firstname, surname, age*/
        String firstName;
        System.out.print("Enter your first name >> ");
firstName = myScan.next();// this line will use the Scanner object
System.out.print("Enter your surname >> ");
        String surName = myScan.next();
        System.out.print("Enter your age >> ");
            int myAge = myScan.nextInt();
        displayDetails(firstName,surName,myAge);
    
    }

    public static void displayDetails(String pfirstName,String psurName, int pmyAge) {
        /* This will display the information sent on screen */
 /* displays the details entered by the user*/
        System.out.println("You entered the following information \n "
                + pfirstName + " " +psurName + " " +pmyAge);
    }
}
