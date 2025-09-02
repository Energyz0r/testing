/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercisetwo;
import java.util.Scanner;

/**
 *
 * @author 44785
 */
public class ExerciseTwo {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int myNumOne;
        System.out.println("Enter first number: ");
        myNumOne = scanner.nextInt();
       
        int myNumTwo;
        System.out.println("Enter second number: ");
        myNumTwo = scanner.nextInt();
        
        int myNumThree;
        System.out.println("Enter second number: ");
        myNumThree = scanner.nextInt();
        
        System.out.println("You picked: " + myNumOne + " " + myNumTwo + " " + myNumThree);
        
        int answer;
        answer = myNumOne + myNumTwo + myNumThree;
        
        System.out.println("The sum of the three numbers is:" + answer);
        scanner.close();
    }
    
}
