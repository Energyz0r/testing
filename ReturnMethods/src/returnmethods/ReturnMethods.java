package returnmethods;
import java.util.*;

public class ReturnMethods {
    static Scanner myScan = new Scanner(System.in);

    public static void main(String[] args) {
       /* int result = average(); // call method and store result
        System.out.println("Average = " + result);*/
        double resultDouble = averageDouble(); // call method and store result
        System.out.printf("Average : %.2f\n", resultDouble);//formating type string inside for double %.2f ( =  show only last 2 decimals )
    }
    
    public static int average() {
        int[] myNum = new int[3];
        int sum = 0;

        for (int i = 0; i < myNum.length; i++) {
            System.out.print("Enter integer number " + (i+1) + ": ");
            myNum[i] = myScan.nextInt();
            sum += myNum[i]; // keep adding to sum
        }

        return sum / myNum.length; // compute average
    }
    
    public static double averageDouble() {
    double[] myNum2 = new double[3];
    double sum = 0.00;
    for (int i = 0; i < myNum2.length; i++) {
            System.out.print("Enter integer number " + (i+1) + ": ");
            myNum2[i] = myScan.nextInt();
            sum += myNum2[i]; // keep adding to sum
        }

        return sum / myNum2.length; // compute average
    }
}
