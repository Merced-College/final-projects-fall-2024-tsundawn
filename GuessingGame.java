/*
*    Title: untitled
*    Author: N/A (they are unnamed)
*    Date: Mar 24th, 2021
*    Code version: source code
*    Availability: https://pastebin.com/Ab11jSUA
*/

import java.util.Random; // Old Code                     // For generating random numbers
import java.util.Scanner; // Old Code                    // User input
import java.util.LinkedList; // Added Code               // Storing guesses
import java.util.Queue; // Added Code                    // Managing players' turns 
import java.util.Stack; // Added Code                    // Tracking scores for each round
import java.util.HashMap; // Added Code                  // Managing player data and scores

 
public class GuessingGame {
    // Added Code: Player class to encapsulate player-related data
     static class Player {
        String name; // Player's name
        int score; // Player's score
        
        // Constructor for initializing a new player
        Player(String name) {
            this.name = name;
            this.score = 0;
        }
        // Method to update the player's score
        public void updateScore(int roundScore) {
            this.score += roundScore;
        }
    
        // Override toString for easy display of player details
        @Override
        public String toString() {
            return "Player: " + name + ", Score: " + score;
        }
    }
    // Added Code: Data Structures
    private static Queue<Player> playersQueue = new LinkedList<>(); // Queue to manage players
    private static LinkedList<Integer> guessesList = new LinkedList<>(); // LinkedList to store guesses
    private static Stack<Integer> roundStack = new Stack<>(); // Stack to track round scores
    private static HashMap<String, Player> playerMap = new HashMap<>(); // HashMap to track players and scores

    public static void main(String[] args) { //Old Code
 
        Scanner input = new Scanner(System.in); //Old Code

         // Added Code: Setup player queue
         System.out.println("Enter the number of players:");
         int numPlayers = input.nextInt(); // Number of players
         input.nextLine(); // Consume newline
         for (int i = 0; i < numPlayers; i++) {
             System.out.println("Enter the name of player " + (i + 1) + ":");
             String playerName = input.nextLine(); // Get player name
             Player player = new Player(playerName); // Create new player
             playersQueue.add(player); // Add player to the queue
             playerMap.put(playerName, player); // Add player to the HashMap
         }    
 
        while (!playersQueue.isEmpty()) { // Updated Code: Manage turns using a queue
            Player currentPlayer = playersQueue.poll(); // Get the next player
            System.out.println("\nIt's " + currentPlayer.name + "'s turn!");

            int roundScore = playRound(input); // Added Code: Play a round
            currentPlayer.updateScore(roundScore); // Update the player's score
            roundStack.push(roundScore); // Store the score in the stack
            System.out.println("End of " + currentPlayer.name + "'s turn. Current Score: " + currentPlayer.score);

            System.out.println("Would you like to play another round? (yes/no)");
            String continueGame = input.nextLine();
            if (continueGame.equalsIgnoreCase("yes")) {
                playersQueue.add(currentPlayer); // Add player back to the queue
            }
        }

        input.close(); // Added Code
    }

     // Added Code: Recursive method to handle a single round of gameplay
     private static int playRound(Scanner input) {
        Random random = new Random();
        System.out.println("Select difficulty level: (1) Easy (1-50), (2) Medium (1-100), (3) Hard (1-500)");
        int difficulty = input.nextInt(); // Get difficulty level
        input.nextLine(); // Consume newline

        // Default values for range and attempts
        int maxNumber = 50, maxAttempts = 4; // Old Code with adjustments
        switch (difficulty) { // Added Code: Adjust values for difficulty
            case 2: // Medium difficulty
                maxNumber = 100;
                maxAttempts = 6;
                break;
            case 3: // Hard difficulty
                maxNumber = 500;
                maxAttempts = 8;
                break; 
            default: 
                System.out.println("Easy mode selected.");
        }
        
        int number = 1 + random.nextInt(maxNumber); //Old Code
            System.out.println("Guess the number between 1 and " + maxNumber + " (Max Attempts: " + maxAttempts + ")"); //Old code with Ajustments
            guessesList.clear(); // Added Code: Clear guesses from the previous round
            return processGuess(input, number, maxAttempts, 0); // Recursive call
        }
        
        // Added Code: Recursive method to process guesses
        private static int processGuess(Scanner input, int number, int remainingAttempts, int attemptsSoFar) {
            if (remainingAttempts == 0) { // Base case: No remaining attempts
                System.out.println("You lose! The correct number was " + number); 
                return 0; // No points scored
            }
            System.out.println("Enter your guess:");
            if (!input.hasNextInt()) { // Validate input
                System.out.println("Invalid input! Please enter a number."); // Input validation
                input.next(); // Clear invalid input
                return processGuess(input, number, remainingAttempts, attemptsSoFar); // Retry the attempt
            }

            int guess = input.nextInt(); // Get the player's guess
            input.nextLine(); // Consume newline
            guessesList.add(guess); // Store guess in the LinkedList
            
            if (guess < number) { //Old Code
                System.out.println("Too low try again"); //If guess is too low         //Old code
            } else if (guess > number) { //old code
                System.out.println("Too high try again"); //If guess is to high        //Old Code
            } else {  // If guess is correct
                System.out.println("Correct! You guessed it in " + (attemptsSoFar + 1) + " attempts.");
                System.out.println("Your guesses this round: " + guessesList); // Display guesses
                return 10 - (attemptsSoFar + 1); // Calculate score
            }

              // Recursive call for the next attempt
        return processGuess(input, number, remainingAttempts - 1, attemptsSoFar + 1);
    }
}

                   