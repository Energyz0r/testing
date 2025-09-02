/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercisethree;
import java.lang.Math;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class ExerciseThree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int firstNum, secondNum, thirdNum;
        
        Scanner scanner = new Scanner (System.in);
        System.out.println("Please enter three numbers: ");
        firstNum = scanner.nextInt();
        
        System.out.println("Please enter second number: ");
        secondNum = scanner.nextInt();
        
        System.out.println("Please enter third number: ");
        thirdNum = scanner.nextInt();
        
        
        System.out.println("You picked: " + firstNum + " " + secondNum + " " + thirdNum);
        
        double result = Math.max(firstNum, Math.max( secondNum, thirdNum));
        double resultTwo = Math.min(firstNum, Math.min( secondNum, thirdNum));
        
        System.out.println("The biggest number is: " + Math.max(firstNum, Math.max( secondNum, thirdNum)));
        System.out.println("The smallest number is: " + Math.min(firstNum, Math.min( secondNum, thirdNum)));
        System.out.println("The biggest number is: " + result +" and the smallest is " + resultTwo + ".");
    }
    
}
