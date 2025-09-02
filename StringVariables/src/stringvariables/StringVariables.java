/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package stringvariables;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class StringVariables {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // concatenation & spacing
        
        String first_name = "Jhon";
        String second_name = "Doe";
        
        System.out.println("Family Name:" + " " + first_name + " " + second_name);
        
        // char annotation
        
        char one, two;
        
        one = 'W';
        two = 'S';
        System.out.println(one + " " + two);
        
        // scanner
        
        Scanner userInput = new Scanner (System.in);
        String firstName;
        System.out.print("Enter you first name: ");
        firstName = userInput.next();
        
        String secondName;
        System.out.print("Enter your surname: ");
        secondName = userInput.next();
        System.out.print("Your name is: " + firstName + " " + secondName);
        
        
        //scanner 2
        
        String questionOne;
        System.out.println("How are you?");
        questionOne = userInput.next();
        System.out.println("No more battery. Session closed !");
        
        String answerOne;
        System.out.println("Pick Red, Blue or Green");
        System.out.println("1. Red");
        System.out.println("2. Blue");
        System.out.println("3. Green");
        answerOne = userInput.next();
    }
    
}
