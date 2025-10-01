/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cinematicket;
import java.util.*;
/**
 *
 * @author 44785
 */
public class CinemaTicket {
static Scanner myScan = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        inputDetails();
    }
    
    public static void inputDetails(){
        System.out.println("Enter movie name: ");
        String filmTitle = myScan.next();
        System.out.println("Enter movie price: ");
        int price = myScan.nextInt();
        System.out.println("Enter number of people: ");
        int people = myScan.nextInt();
        calculateBill(filmTitle,price,people);
    
    }
    
    public static void calculateBill (String filmTitle, int price, int people){
    int totalBill = price * people;
    double discountedBill = totalBill - (0.1 * totalBill);
        System.out.printf("Film Title: %s\nPrice: %d$\nPeople: %d\nTotal bill: %d$\nDiscounted Bill: %.2f$\n",filmTitle,price,people,totalBill,discountedBill);
    }
    
    
}
