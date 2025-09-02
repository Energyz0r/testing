/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package evenorodd;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class EvenOrOdd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Scanner userInput = new Scanner (System.in);
        int numInput = 0;
        System.out.println("Please enter a number: ");
        numInput = userInput.nextInt();
        
        System.out.println("Your number is: " + numInput);
        
        //create funtion to check if number is even or odd
        // we modul by % 2 and if equals to 0 is even if else is false.
        
        if (numInput % 2 == 0){
            System.out.println("Number is Even!");
        } else {
            System.out.println("Number is Odd !");
        }
        userInput.close();
    }
    
}
