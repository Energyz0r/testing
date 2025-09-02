/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package passorfail;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class PassOrFail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double userInput;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter your number (0-100): ");
        userInput = sc.nextDouble();
        System.out.println("Your number is: " + userInput);
        if (userInput < 50) {
            System.out.println("You fail!");
        } else { 
            System.out.println("You WIn!");
        }
        
        sc.close();
    }
    
}
