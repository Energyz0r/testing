/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package agechecker;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class AgeChecker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int age;
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter your age: ");
        age = sc.nextInt();
        
        if (age < 18) {
            System.out.println("You are not an adult !");
        } else {
            System.out.println("You are an adult !");
        }
        sc.close();
    }
    
}
