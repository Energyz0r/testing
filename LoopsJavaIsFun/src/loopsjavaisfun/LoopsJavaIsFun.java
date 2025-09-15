/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loopsjavaisfun;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class LoopsJavaIsFun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner (System.in);
        int javaIsFun = 20;
        
        for (int i=0; i <= javaIsFun - 1; i++ ) {
            System.out.println("Java is fun");
        }
        System.out.println("End of program");
        //starDisplay();
        //displayNames();
        javaIsFun(scanner);
    }
    
    public static void starDisplay() {
    String[] names = {"Jumping", "Jack", "Sprat"};
    String stars = "*********************";
    
        System.out.println(stars);
        for (int i =0; i < 4; i++) {
            for (int j = 0; j < names.length ; j++) {
                System.out.println(names[j]);
            }
        }
        System.out.println(stars);
    }
    
    public static void displayNames() {
        for (int i=0; i < 10; i++) {
     Scanner myScan = new Scanner (System.in);
     String inputName = "";
     
        System.out.print("Please enter name: ");
        inputName = myScan.nextLine();
        System.out.println("Your name is: " +inputName);
    }
    }
    
    public static void javaIsFun(Scanner scanner) {
    int numberOfTimes;
        System.out.println("Please enter number of times: ");
        numberOfTimes = scanner.nextInt();
        for (int i=0; i <= numberOfTimes - 1; i++ ) {
            System.out.println(+i+1 + "." + " " + "Java is fun");
        }
        System.out.println("End of program");
         
    }
    
    
    
   
}
