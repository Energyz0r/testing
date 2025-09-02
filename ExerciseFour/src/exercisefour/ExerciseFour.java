/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercisefour;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class ExerciseFour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // link scanner to input
        Scanner userInput = new Scanner (System.in);
        
        // declaring total starts from 0
        double total = 0;
        //for loop until 6
        for (int i = 0; i <=5; i++){
                System.out.print("Enter a number: ");
                total = total + userInput.nextInt(); // calculate total = whatever is in total + next int (user input)
        }
        //calculate average and print
        double average = total/6;
        System.out.print("Your average is: " + average);
        
        userInput.close();
    }
    
}
