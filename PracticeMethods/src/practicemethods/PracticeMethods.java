package practicemethods;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author 44785
 */
public class PracticeMethods {
static Scanner myScan = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        multiply();
       
    }
    
    public static int multiply(){
        System.out.print("Enter first number: ");
    int x = myScan.nextInt();
        System.out.print("Enter second number: ");
    int y = myScan.nextInt();
    int multiply = x * y;
            System.out.println(multiply);
    return multiply;
    }
}
