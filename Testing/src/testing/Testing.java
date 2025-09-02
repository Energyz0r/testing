/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testing;

/**
 *
 * @author 44785
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // multiplication 
        double one, two, three, sum;
        
        one = 20.20;
        two = 10.20;
        three = 15.20;
        
        sum = one + two + three;
        System.out.println("Sum" + sum);
        
        // brackets
        int zet, why, ics, calc;
        zet = 50;
        why = 25;
        ics = 100;
        
        calc = (zet - why) +ics;
        
        System.out.println("Answer:" + calc);
        
        //multiplication divide brackets
        int one1, two2, three3, answer;
        one1 = 50;
        two2 = 25;
        three3 = 100;
        answer = (one1 * two2)+ (three3 / one1);
        System.out.println("Answer:" + " "+ answer);
        
    }
    
}
