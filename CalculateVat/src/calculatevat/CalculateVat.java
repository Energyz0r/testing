/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculatevat;
import java.util.Scanner;
/**
 *
 * @author 44785
 */
public class CalculateVat {
static Scanner myScan = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        enterDisplay();
    }
    
    public static void enterDisplay(){
        System.out.println("Enter your total salary: ");
        double salary = myScan.nextDouble();
        System.out.println("Enter your department name: ");
        String departmentName = myScan.next();
        
        
        calculateVat( salary, departmentName); // calculateVat ( 100, Pepsi );
        
    }
    public static void calculateVat(double salary, String departmentName){
        System.out.println("Insert VAT rate:");
    int vatRate = myScan.nextInt();
    double vatAmount = vatRate * salary/100;
        //System.out.printf("Salary : %2f, %2f",salary, salary,"\nDepartment Name: " +departmentName + "\nVat Rate: " + vatRate + "\nVAT Amount: " +vatAmount);
        System.out.printf("Salary: %10.2f \nDepartment: %s\nVat Rate: %d\nVAT Amount: %10.2f\n", salary, departmentName,vatRate,vatAmount);
    }
    
    
}
