/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loopstesting;

/**
 *
 * @author 44785
 */
public class LoopsTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int num = 5;

    /*    for (int i = 1; i <= num; i++) {

            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
 */
        for (int i = num; i >= 1; i--) {

            // print leading spaces
            for (int space = 0; space < (num - i); space++) {
                System.out.print("  "); // two spaces to align with "* "
            }

            // print stars
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }

            System.out.println();
        }
       /*
        for (int i = 1; i<= num; i++) {
            for (int j = 1; j<=i; j++){
            System.out.print("* ");}
              System.out.println();
        }
      */
      

    }

}
