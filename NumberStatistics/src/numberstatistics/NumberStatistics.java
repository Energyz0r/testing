/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package numberstatistics;
import java.util.Scanner;
import java.lang.Math;
/**
 *
 * @author 44785
 */
public class NumberStatistics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //define variables
        int number;
        int totalNumbers = 0;
        int sumTotalNumb = 0;
        
        double maxNumb = Double.NEGATIVE_INFINITY;
        double minNumb = Double.POSITIVE_INFINITY;
        
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.print("Enter number/s:");
        number = sc.nextInt();
            System.out.println("You chose:" + number);
          if (number == -999) {
          break;
          }
          totalNumbers++;
          sumTotalNumb = sumTotalNumb + number;
          
          if (number > maxNumb) {
          maxNumb = number;
          }
          
          if (number < minNumb) {
          minNumb = number;
          }
        }
        System.out.println("You picked X numbers: " + totalNumbers);
        System.out.println("Your total is:" + sumTotalNumb);
        System.out.println("Your average is: " + sumTotalNumb / totalNumbers);
        System.out.println("Your highest is: " + maxNumb + "\nYour lowest is:" + minNumb );
        
    }
    
}
