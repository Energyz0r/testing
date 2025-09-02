/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package freezingornot;
import java.util.Scanner;

/**
 *
 * @author 44785
 */
public class FreezingOrNot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        double userInput;
        
        
        System.out.print("Please insert your temperature °C: ");
        userInput = sc.nextDouble();
        
        if (userInput <= 0) {
            System.out.println("The water is freezing at " + userInput + "°C");
        } else {
            System.out.println("The water will not freeze at "+ userInput + "°C");
        }
        
        
    }
    
}
