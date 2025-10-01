/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dicegame;

import java.util.*;

/**
 *
 * @author 44785
 */
public class DiceGame {

    static Scanner myScan = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Enter number of dices to roll");
        int rolls = myScan.nextInt();

        int big = biggestDice(rolls);
        System.out.println(big);

    }

    public static int biggestDice(int rolls) {

        int[] bigNum = new int[rolls];
        
        int maxVar;
        maxVar = bigNum[0];
        
        String numbersString= "";
        for (int i = 0; i < rolls; i++) {
           bigNum[i] = dice();
        }
                
        for (int i = 0; i < bigNum.length; i++) {
             numbersString += bigNum[i] + "-";
        }
        
        System.out.println("");
        System.out.print(numbersString.substring(0,rolls *2 -1));
        System.out.println("");
        
        for (int i = 0; i < bigNum.length; i++) {
  
            if (bigNum[i] > maxVar) {
                maxVar = bigNum[i];
            }
            if (maxVar == bigNum[i]) {
                System.out.println("Dice " + (i + 1));
                break;
            }
            
        }
        return maxVar;
    }

    public static int dice() {
        int rand = (int) (Math.random() * 6) + 1;
        return rand;
    }
}
