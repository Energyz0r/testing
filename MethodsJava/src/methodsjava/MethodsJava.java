
package methodsjava;
import java.util.Scanner;

public class MethodsJava {

    public static void main(String[] args) {
     Scanner myScan = new Scanner(System.in);
        System.out.print("Please insert first number: ");
     int userInputOne = myScan.nextInt();
     
      System.out.print("Please insert second number: ");
     int userInputTwo = myScan.nextInt();
     
        
        
        
        
        int multInputs = multiply(userInputOne,userInputTwo);
        System.out.println(userInputOne + " x " +userInputTwo + " = " + multInputs);
        
        myScan.nextLine();
        
        System.out.print("Please insert the name: ");
        String userName = myScan.nextLine();
        System.out.println("User: " + userName + " Inputs were: " + userInputOne + " and " + userInputTwo + " which equal to " + multInputs + ".");
}
    
    static int multiply(int userInputOne,int userInputTwo) {
    return userInputOne * userInputTwo;
    }
}