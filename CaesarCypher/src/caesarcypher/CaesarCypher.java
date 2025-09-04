/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caesarcypher;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class CaesarCypher {
    
    private static String encryption(String phrase, int keyShift) {
        String encrypted = "";

        for (int i = 0; i < phrase.length(); i++) {
            char charAt = phrase.charAt(i); // hello --- h-0 ---e-1 --- l---2 --- l-3 ---- o-4
            if (Character.isUpperCase(charAt)) {
                encrypted += (char) ((charAt + keyShift - 65) % 26 + 65); // uppercase A starts at ASCII 65- Z 90
            } else {
                encrypted += (char) ((charAt + keyShift - 97) % 26 + 97); // lowercase a97 - z122
            }
        }

        return encrypted;
    } 
    
     private static String decryption(String phraseCrypted, int keyShift) {
        String decrypted = "";

        for (int i = 0; i < phraseCrypted.length(); i++) {
            char charAt = phraseCrypted.charAt(i);
            if (Character.isUpperCase(charAt)) {
                decrypted += (char) ((charAt - keyShift - 65) % 26 + 65);
            } else {
                decrypted += (char) ((charAt - keyShift - 97) % 26 + 97);
            }
        }

        return decrypted;
    }
    
    public static void main(String[] args) {
        // list variables
        showMenuVoid();
        
        
    }
        
     public static void showMenuVoid() {
        
            int userChoice;
            int keyShift = 0;
          
            String phrase = "";
            
            Scanner sc = new Scanner (System.in);
            do
            {
                
                System.out.println("1. Crypting");
                System.out.println("2. Decrypting");
                System.out.println("3. Quit");
                System.out.print("Please enter a menu choice : ");
                userChoice = sc.nextInt();
                sc.nextLine();
                switch (userChoice)
                {
                    case 1:
                    {
                        System.out.print("Enter your Encryption Key : ");
                        keyShift = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Crypting. Insert phrase to crypt: ");
                        phrase = sc.nextLine();
                        System.out.println("Your encrypted phrase is : " + encryption(phrase, keyShift) +  "\n");
                        
                       
                       break;
                        
                    }
                    case 2:
                    {
                        System.out.print("Enter your Decryption Key :");
                        keyShift = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Decrypting. Insert phrase to decrypt: ");
                        phrase = sc.nextLine();
                        System.out.println("Your decrypted phrase is : " + decryption(phrase, keyShift) + "\n");
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Option 3: Quit");
                        break;
                    }
                    default:
                    {
                        System.out.println("not a valid choice");
                    }
                }//closes switch
            }while(userChoice!=3);

            sc.close();
            System.out.println("Goodbye!");
        }
}//closes showMenu
    
    

