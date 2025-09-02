/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package discounteligibility;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class DiscountEligibility {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double total;
        
        Scanner sc = new Scanner (System.in);
        
        System.out.println("What is your total ammount spent ?");
        
        total = sc.nextDouble();
        
        if ( total >= 100.00) {
            System.out.println("You get a discount !");
        } else {
            System.out.println("No discount !");
        }
    }
    
}
