/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codemethodstesting;

import java.util.*;

/**
 *
 * @author 44785
 */
public class CodeMethodsTesting {

    /**
     * @param args the command line arguments
     */
    static Scanner myScan = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO code application logic here
        String[] names = new String[5];

        String n = asd(names);
        System.out.println(n);

    }

    public static String asd(String[] names) {

        String combined = "";
        for (int i = 0; i < names.length; i++) {
            System.out.println("Please enter Name #" + i);
            names[i] = myScan.nextLine();
            
            combined += "The name is : " + names[i] + " with age :" + "\n";
        }
        return combined;
    }
}
