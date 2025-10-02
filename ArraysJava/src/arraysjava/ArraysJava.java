/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arraysjava;

import java.util.*;

/**
 *
 * @author 44785
 */
public class ArraysJava {

    static Scanner myScan = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int myNums[] = new int[10]; // globala
        playMenu(myNums);

    }

    public static void playMenu(int[] myNums) {
        System.out.println("==== Play Menu ====");
        System.out.println("==== 1. Enter 10 numbers ====");
        System.out.println("==== 2. Display Numbers ====");
        System.out.println("==== 3. Calculate sum  ====");
        System.out.println("==== 4. Calculate average ====");
        System.out.println("==== 5. Display lowest ====");
        System.out.println("==== 6. Display highest ====");
        System.out.println("==== 7. Quit ====");
        int userChoice;
        do {

            userChoice = myScan.nextInt();
            switch (userChoice) {
                case 1:
                    getNum(myNums);
                    break;
                case 2:
                    displayNum(myNums);
                    break;
                case 3:
                    int sum = sumNum(myNums);
                    System.out.println("The sum is : " + sum);

                    break;
                case 4:
                    int sum1 = sumNum(myNums);

                    System.out.println("Average is " + averageNum(myNums, sum1));

                    break;
                case 5:

                    System.out.println("Min value is " + displayMin(myNums));
                    break;
                case 6:
                    System.out.println("Max value is " + displayMax(myNums));
                    break;
                case 7:
                    System.out.println("Exiting ...");
                    break;
            }
        } while (userChoice != 7);

    }

    public static void getNum(int[] myNums) {

        for (int i = 0; i < myNums.length; i++) {
            System.out.print("Enter number: " + (i + 1));
            myNums[i] = myScan.nextInt();
        }
        if (myNums.length == 10) {
            playMenu(myNums);
            System.out.println("");
        }
    }

    public static void displayNum(int[] myNums) {
        for (int i = 0; i < myNums.length; i++) {
            System.out.println("Number " + (i + 1) + " is " + myNums[i]);
        }
    }

    public static int sumNum(int[] myNums) {
        int sum = 0;
        for (int i = 0; i < myNums.length; i++) {

            sum += myNums[i];

        }

        return sum;
    }

    public static double averageNum(int[] myNums, int sum) {
        double average = sum / (myNums.length);
        return average;
    }

    public static int displayMin(int[] myNums) {
        int min = myNums[0];
        for (int i = 0; i < myNums.length; i++) {
            if (myNums[i] < min) {
                min = myNums[i];
            }
        }
        return min;
    }

    public static int displayMax(int[] myNums) {
        int min = myNums[0];
        for (int i = 0; i < myNums.length; i++) {
            if (myNums[i] > min) {
                min = myNums[i];

            }
        }
        int max = min;
        return max;
    }
}
