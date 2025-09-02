/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package switchradiomenu;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class SwitchRadioMenu {

    
    /**6
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int i = 1; i <= 3; i++)
            
                System.out.println("Option:" +i );
            System.out.println("Option 0 Quit");
        boolean quit = false;
        int menuItem;
        
        do { 
            System.out.println("Choose an optione 1-3 ");
            menuItem = in.nextInt();
            
            switch (menuItem){
                case 1:
                    System.out.println("You picked one.");
                    break;
                case 2:
                    System.out.println("You picked two.");
                    break;
                case 3:
                    System.out.println("You picked three.");
                    break;
                case 0:
                    quit = true;
                    break;
            }
           
           
        } while (!quit);
    }
    
}
