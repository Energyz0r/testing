/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package switchoperatedtextmenu;
import java.util.Scanner;


/**
 *
 * @author 44785
 */
public class SwitchOperatedTextMenu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        for (int i = 1; i <= 5; i++ )
            System.out.println("Option " +i);
        System.out.println("0. Quit");
        
        boolean quit = false;
        int menuItem;
        do {
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch ( menuItem ) {
                case 1:
                    System.out.println("Item 1");
                break;
                case 2:
                    System.out.println("Item 2");
                break;
                case 3:
                    System.out.println("Item 3");
                break;
                case 4:  
                    System.out.println("Item 4");
                break;
                case 5:
                    System.out.println("Item 5");
                break;
                case 0:
                    System.out.println("Quit");
                    quit = true; 
                    break;
            }
        } while (!quit);
    }
    
}
