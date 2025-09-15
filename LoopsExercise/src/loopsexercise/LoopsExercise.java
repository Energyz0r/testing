/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loopsexercise;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class LoopsExercise {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner myScan = new Scanner(System.in);
        int inputStart;
        int endNumber;
        learnToCount();
         System.out.print("Please Enter Starting Number");
        inputStart = myScan.nextInt();
        
        System.out.print("Please enter ending number: ");
        endNumber = myScan.nextInt();
        learnToCount1(inputStart, endNumber);
    }
    
    public static void learnToCount1(int inputStart, int endNumber) {
    
       
        for (int startNumber=inputStart;startNumber<=endNumber;startNumber++) {
        System.out.print(startNumber + ",");
        
    }
    }
    public static void learnToCount() {
    
    for (int count=5;count<=12;count++) {
        System.out.print(count + ",");
        
    }
      System.out.println();
    for (int count=1;count<=10;count++) {
        if (count%2 == 0 ){
        System.out.print( +count + ",");
        }
    }
    System.out.println();
    Scanner myScan = new Scanner ( System.in);
    
    String nameInput = "";
    for (int count=1;count<=10;count++) {
        System.out.print("Please Enter Name " +count + " >> ");
        nameInput = myScan.nextLine();
        
        
    }
        System.out.println("End Of Program");
        
    }
    
    
    
}
