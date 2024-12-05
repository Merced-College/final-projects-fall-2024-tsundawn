/*
*    Title: untitled
*    Author: N/A (they are unnamed)
*    Date: Mar 24th, 2021
*    Code version: source code
*    Availability: https://pastebin.com/Ab11jSUA
*/
import java.util.Random;
import java.util.Scanner;
 
public class GuessingGame {
 
    public static void main(String[] args) {
 
        Scanner input = new Scanner(System.in);
 
        String restart = "yes";
 
        while (restart.equalsIgnoreCase("yes")) {
            int guesses = 0;
            Random random = new Random();
            int number = 1 + random.nextInt(50);
            System.out.println("Please guess the number");
 
            String repeat = "yes";
            int guess = -1;
 
            final int maxattempts = 10;
            while (repeat.equalsIgnoreCase("yes")) {
 
                while (number != guess) {
                    // for(int a = 0; a < 10; a++) {
                    guess = input.nextInt();
 
                    if (guess < number) {
                        System.out.println("Too low try again");
                    }
                    if (guess > number) {
                        System.out.println("Too high try again");
                    }
                    // if (number == guess) {
                    // System.out.println("Correct");
                    // repeat = "no";
 
                    // }
                    // }
                    guesses++;
 
                    if (guesses == maxattempts)
                        System.out.println("You lose the number was " + number + " !");
 
                }
 
                System.out.println("Correct you got it in " + guesses + " guesses!");
                repeat = "no";
 
            }
 
            System.out.println("Would you like to play again?");
            input.nextLine();
            restart = input.nextLine();
        }
 
    }
 
}