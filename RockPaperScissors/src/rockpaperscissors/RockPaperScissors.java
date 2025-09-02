/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rockpaperscissors;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author 44785
 */
public class RockPaperScissors {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new scanner
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to Rock, Paper, Scissors");
        int num;
        int numOfGames;
        Random rand = new Random();
        int userScore = 0;
        int compScore = 0;
        int gamesPlayed = 0;
         System.out.print("Choose number of games ( 1-9): ");
        numOfGames = userInput.nextInt();
        userInput.nextLine();
        
        do {
            String userChoice = "";
            String compChoice = "";



            System.out.print("\nPlease pick: R.ock, P.aper, S.cissors: ");

            userChoice = userInput.nextLine();

            if (userChoice.equals("R")){
                System.out.println("You chose ROCK !");
            } else if (userChoice.equals("P")) {
                System.out.println("You chose Paper !");
            } else if (userChoice.equals("S")) {
                System.out.println("You chose Scissors !");
            }


            // what computer picks . random than read
            num = rand.nextInt(3);

            if ( num == 0) {
                compChoice = "R";
            }

            else if ( num == 1) {
                compChoice = "P";
            }

             else if ( num == 2) {
                compChoice = "S";
            }


            //printing choice

            if(compChoice.equals("R")) {
                System.out.println("Computer chose Rock");
            }
            else  if(compChoice.equals("P")) {
                System.out.println("Computer chose Paper");
            }
            else  if(compChoice.equals("S")) {
                System.out.println("Computer chose Scissors");
            }

            // winner logic plus score updates
            if ( userChoice.equals("R") && compChoice.equals("S") || 
                    userChoice.equals("P") && compChoice.equals("R") || 
                    userChoice.equals("S") && compChoice.equals("P")) {

                System.out.println("User win!");
                userScore++;
            }
            else if ( compChoice.equals("R") && userChoice.equals("S") || 
                    compChoice.equals("P") && userChoice.equals("R") || 
                    compChoice.equals("S") && userChoice.equals("P")) {

                System.out.println("Comp win!");
                compScore++;
            }
             else  {

                System.out.println("It`s a tie!");
               
                
            } 
            
            gamesPlayed++;
           
           
            
            
            
            } while ( gamesPlayed < numOfGames);
        userInput.close();
        
        //print scores
        for (int i = gamesPlayed; i == numOfGames; i++){
        System.out.println("User wins: " + userScore + "." + "Comp wins: " + compScore + ".");
        }
        //print final winner logic message 
        if (compScore < userScore) {
                System.out.println("User is the final winner !");
            }
            else if (compScore > userScore){
                System.out.println("Computer is the final winner !");
               
            } else  {
            System.out.println("Tied !");}
        
    }
    
    
}
