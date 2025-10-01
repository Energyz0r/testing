/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package secondsinyears;
import java.util.*;
/**
 *
 * @author 44785
 */
public class SecondsInYears {
 static Scanner myScan = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //3600 x 24 x 365 x numberofYears
        
        int secondsinHour = 3600;
        System.out.print("Enter the number of years: ");
        int numberofYears = myScan.nextInt();
        int result = sumofNumbers() * numberofYears;
        System.out.print("There are " + result + "s" + " in " +numberofYears +" years!");
    }
    
    public static int sumofNumbers(){
    int sum = 3600 * 24 * 365;
    return sum;
    }
    
}
