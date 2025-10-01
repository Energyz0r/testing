/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javacalculator;

import java.lang.Math;
import java.util.*;

/**
 *
 * @author 44785
 */
public class JavaCalculator {

    static Scanner myScan = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.print("Enter user choice: ");
        int userChoice;
        do {
            System.out.println("\n=== Calculator menu ===");
            System.out.println("1. Add");
            System.out.println("2. Substract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Modulus");
            System.out.println("6. Power");
            System.out.println("7. Average");
            System.out.println("8. Is Even");
            System.out.println("0. Exit");
            userChoice = myScan.nextInt();
            switch (userChoice) {
                case 1:
                    System.out.println("Enter first number: ");
                    double x1 = myScan.nextDouble();
                    System.out.println("Enter second number: ");
                    double y1 = myScan.nextDouble();
                    System.out.println("Result: " + add2(x1, y1));
                    break;
                case 2:
                    System.out.println("Enter first number: ");
                    int x2 = myScan.nextInt();
                    System.out.println("Enter second number: ");
                    int y2 = myScan.nextInt();
                    System.out.println("Result: " + sub(x2, y2));
                    break;
                case 3:
                    System.out.println("Enter first number: ");
                    int x3 = myScan.nextInt();
                    System.out.println("Enter second number: ");
                    int y3 = myScan.nextInt();
                    System.out.println("Result: " + sub(x3, y3));
                    break;

                case 4:

                    System.out.println("Enter first number: ");
                    int x4 = myScan.nextInt();
                    System.out.println("Enter second number: ");
                    int y4 = myScan.nextInt();
                    int divisionMethod = div(x4, y4);

                    if (divisionMethod == 0) {
                        System.out.println("Cannot divide by 0.");
                    } else {
                        System.out.println("Result: " + div(x4, y4));
                    }
                    break;
                case 5:
                    System.out.println("Enter first number: ");
                    int x5 = myScan.nextInt();
                    System.out.println("Enter second number: ");
                    int y5 = myScan.nextInt();
                    System.out.println("Result: " + mod(x5, y5));
                    break;
                case 6:
                    System.out.println("Enter first number: ");
                    int x6 = myScan.nextInt();
                    System.out.println("Enter second number: ");
                    int y6 = myScan.nextInt();
                    System.out.println("Result: " + power(x6, y6));
                    System.out.println("Result2: " + powerCompute(x6, y6));
                    break;
                case 7:
                    System.out.println("Enter first number: ");
                    int x7 = myScan.nextInt();
                    System.out.println("Enter second number: ");
                    int y7 = myScan.nextInt();

                    System.out.println("Result: " + average(x7, y7));
                    break;
                case 8:
                    System.out.println("Enter first number: ");
                    int x8 = myScan.nextInt();
                    System.out.println("Result: " + isEven(x8));
                    break;
                case 0:
                    System.out.println("Exiting ...");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (userChoice != 0);
        myScan.close();
    }

    //addition
    public static int add(int x1, int y1) {
        int result = x1 + y1;
        return result;
    }

    //substraction
    public static int sub(int x2, int y2) {
        int result = x2 - y2;
        return result;
    }

    //multiplication
    public static int mult(int x3, int y3) {

        int result = x3 * y3;
        return result;
    }

    //division
    public static int div(int x4, int y4) {
        int result;

        if (x4 == 0 || y4 == 0) {
            return 0;
        } else {
            result = x4 / y4;
            return result;
        }

    }

    //modulus
    public static double mod(int x5, int y5) {
        int result = x5 % y5;
        return result;
    }

    public static int power(int x6, int y6) {

        int result = (int) Math.pow(x6, y6);

        return result;
    }

    public static int powerCompute(int x6, int y6) {
        int result = 1;
        for (int i = 1; i <= y6; i++) {
            result *= x6;
        }

        return result;
    }

    public static int average(int x7, int y7) {
        int result = (x7 + y7) / 2;
        return result;
    }

    public static double add2(double x1, double y1) {
        double result = x1 + y1;
        return result;
    }

    public static boolean isEven(int x8) {
        boolean result = false;

        if (x8 % 2 == 0) {
            result = true;
        }
        return result;
    }
}
