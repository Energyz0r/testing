
package stringsasmatullah;
/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.*;
/**
 *
 * @author 44785
 */
public class StringsAsmatUllah {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //scanner
       Scanner sc = new Scanner ( System.in);
       
       //variables
       String firstName = "";
       String secondName = "";
       //user inputs
        System.out.print("Please enter your first name: ");
        firstName = sc.nextLine();
        System.out.print("Please enter your second name: ");
        secondName = sc.nextLine();
        //returns
        // *** substring 
        //tested
        System.out.println("I entered " + firstName.toUpperCase() + " " + secondName.toUpperCase());
        System.out.println("Hello " + firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase() + " " + secondName.substring(0, 1).toUpperCase() + secondName.substring(1).toLowerCase() + ",you are a great coder!");
        
        System.out.println("Hello "+ sanitizeName(firstName)+ " " +sanitizeName(secondName) +" ,you are a great coder!");
        System.out.println("Your initials are: " + firstName.substring(0,1).toUpperCase() + secondName.substring(0,1).toUpperCase());
        
        int strlFirst;
        int strlSecond;
        
        strlFirst = firstName.length();
        strlSecond = secondName.length();
        int strlTotal = firstName.length() + secondName.length();
   
        System.out.println("Length for First Name / Second Name / Total : " + strlFirst + "/" + strlSecond + "/" + (strlFirst + strlSecond));

        //reverse 
        String reversed = new StringBuilder(secondName + firstName ).reverse().toString().toLowerCase();
        //String reversed2 = 
        System.out.println("Reversed : " + reversed.substring(0,1).toUpperCase() + reversed.substring(1,reversed.length() -1 ) + reversed.substring(reversed.length() -1).toUpperCase() );
        
       // tamsa hallu     T amsahll U
        
        
    }
    
    public static String sanitizeName(String name) { 
        name = name.toLowerCase();
        return name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase(); 
    } 
    
    
};
        
        