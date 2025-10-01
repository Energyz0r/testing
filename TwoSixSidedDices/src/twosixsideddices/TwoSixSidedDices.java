package twosixsideddices;
import java.util.*;

public class TwoSixSidedDices {
    static Scanner myScan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("How many times do you want to roll the dices? ");
        int diceRolls = myScan.nextInt();

        int[] rolls = new int[diceRolls];

        // roll dice N times
        
        for (int i = 0; i < diceRolls; i++) {
            rolls[i] = diceRoll(); // store total of two dice
            System.out.println("Roll " + (i+1) + ": " + rolls[i]);
            //printing roll + (current number of roll) + array current roll
        }

        // calculate and display average
        double avg = average(rolls);
        System.out.println("Average of all rolls: " + avg);
    }
    
    // method to roll 2 dice and return their sum
    public static int diceRoll() {
        Double diceRandom = Math.random() * 6;
        int dice1 = diceRandom.intValue() + 1; // 1–6
        int dice2 = (int)(Math.random() * 6) + 1;
        return dice1 + dice2;
    }

    // method to calculate average of an int array
    public static double average(int[] nums) {
        int sum = 0;
        for (int n : nums) { // n stores each int input from rolls
            sum += n;
        }
        return (double) sum / nums.length;
    }
}
