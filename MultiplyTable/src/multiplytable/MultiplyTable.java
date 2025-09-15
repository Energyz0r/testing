package multiplytable;

import java.util.*;


public class MultiplyTable {

    public static void main(String[] args) {
        showMenu();
        
        
    }//closes main

    public static void showMenu() //displays a menu and keeps displaying until user chooses the QUIT option
    {
        
        int userChoice;
        Scanner myScan = new Scanner(System.in);
        Random rand = new Random();
        do {
            System.out.println("1. Multiply");
            System.out.println("2. Practice");
            System.out.println("3. Random Multiply");
            System.out.println("4. Quit");
            System.out.print("Please enter a menu choice: ");
            userChoice = myScan.nextInt();
            switch (userChoice) {
                case 1: {
                    int multiplier;
                    int numOfRows;
                    //user inputs for multiplier and number of lines
                    System.out.print("Enter the multiplier: ");
                    multiplier = myScan.nextInt();
                    System.out.print("Enter the number of lines: ");
                    numOfRows = myScan.nextInt();
                    
                   //print statement
                    for (int i = 1; i <= numOfRows; i++) {
                        System.out.println(i + " x " + multiplier + " = " + i * multiplier);
                    }
                    
                    break;
                }
                case 2: {
                    System.out.print("Please enter multiplication table you want to be tested on: ");
                    int multiplier = myScan.nextInt();
                    int randomNumb = rand.nextInt(12);
                    System.out.println(randomNumb + " x " + multiplier + " = " + " ? ");
                    //totalOut so i can compare to userTotal
                    int totalOut;
                    totalOut = randomNumb * multiplier;
                    System.out.print("Please enter you answer: ");
                    int userTotal = myScan.nextInt();
                    if (totalOut == userTotal) {
                        System.out.println("You were correct!");
                    } else if (userTotal != totalOut) {
                        System.out.println("You are not correct!");
                    }
                    break;
                }
                case 3: {
                    //variables def

                    int answer;
                    int tryNo;
                    int increment = 0;

                    //how many tries
                    System.out.print("Enter the number of tries: ");
                    tryNo = myScan.nextInt();
                    //replace x or y or z with ?   
                    do {
                        int hide = rand.nextInt(3);
                        int x = rand.nextInt(10) + 1;
                        int y = rand.nextInt(10) + 1;
                        int z = x * y;
                        if (hide == 0) {      //hide x
                            System.out.println("?" + " x " + y + " = " + z);

                        } else if (hide == 1) {      //hide y
                            System.out.println(x + " x " + " ? " + " = " + z);

                        } else if (hide == 2) {        //hidde z
                            System.out.println(x + " x " + y + " = " + " ? ");

                        }

                        System.out.print("Please enter the answer: ");
                        answer = myScan.nextInt();

                        //correct or wrong
                        // cases 
                        if (hide == 0 && x == answer) {
                            System.out.println("Correct");
                        } else if (hide == 1 && y == answer) {
                            System.out.println("Correct");
                        } else if (hide == 2 && z == answer) {
                            System.out.println("Correct");
                        } else {
                            System.out.println("Wrong answer");
                        }
                        increment++;

                    } while (tryNo > increment);
                    break;
                }
                case 4: {
                    System.out.println(". Quit");
                    break;
                }

                default: {
                    System.out.println("not a valid choice");
                }
            }//closes switch
        } while (userChoice != 4);
        myScan.close();
        System.out.println("goodbye");
    }//closes showMenu

}//closes class
