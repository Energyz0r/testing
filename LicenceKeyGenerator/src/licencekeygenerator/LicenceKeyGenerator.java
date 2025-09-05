package licencekeygenerator;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.*;
/**
 *
 * @author 44785
 */
public class LicenceKeyGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String firstName, lastName, orgName;
        Scanner sc = new Scanner (System.in);
        System.out.println("Please insert your first name: ");
        firstName = sc.nextLine();
        System.out.println("Please insert your last name: ");
        lastName = sc.nextLine();
        System.out.println("Please insert your organization name: ");
        orgName = sc.nextLine();
        //StringBuilder(string).reverse().toString() -reverse and convert .toString() as my variable is string
        String firstNameRev = new StringBuilder(firstName).reverse().toString(); 
        
         // replace( what i want to replace, with what to replace )
        int totalLength = (firstName + lastName + orgName).replace(" ", "").length();
        
        
        Random rand = new Random ();
        int randomNumb = rand.nextInt(600)+200 * totalLength;
        
       
        String orgNameRev = new StringBuilder(orgName.replace(" ","")).reverse().toString(); 
        StringBuilder evenSlot = new StringBuilder();
        
        if(orgNameRev.length() % 2 != 0) {
            orgNameRev = orgNameRev + "X";
        }
        
        //System.out.println(orgNameRev);
        
       
        for(int i = 0; i < orgNameRev.length(); i++) {
            
            if(i % 2 != 0) {
                evenSlot.append(orgNameRev.charAt(i));
            }
        }
        
        //System.out.println(evenSlot.toString().toLowerCase()); // ALLWAYS .toString() when using StringBuilder
        
        
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        
        String licenseKey = firstNameRev + "-" + totalLength + "-" + randomNumb + "-" + evenSlot.toString().toLowerCase() + "-" + uuidAsString;
        
        System.out.println("Your Licence Key is : " + licenseKey );
    }
    
}
