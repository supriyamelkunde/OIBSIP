package number.gussing.game;
import java.util.*;
import javax.swing.*;

public class NumberGussingGame {
    public static void main(String[] args) {
        int rangeStart = 1;
        int rangeEnd = 100;
        int maxAttempts = 5;
        int numRounds = 3;
        int score = 0;
        
        for(int round = 1;round<=numRounds; round++) {
            JOptionPane.showMessageDialog(null, "Round" + round);
            
            Random random = new Random();
            int randomNumber = random.nextInt( rangeEnd - rangeStart+1)+ rangeStart;
            
            boolean guessedCorrectly = false;
            int attempts = 0;
            
            while(!guessedCorrectly && attempts < maxAttempts) {
                String guessString = JOptionPane.showInputDialog("Guess a umber between "+ rangeStart+ " and "+ rangeEnd+ ":");
                
                if(guessString == null) {
                    break;
                }
                
                int guess = Integer.parseInt(guessString);
                attempts++;
                
                if (guess == randomNumber) {
                    guessedCorrectly = true;
                    score += maxAttempts - attempts + 1;
                    JOptionPane.showMessageDialog(null,"Congratulations! You guessed the number "+ attempts + " attempts. \n Your Score: "+ score);
                }
                else if (guess < randomNumber) {
                    JOptionPane.showMessageDialog(null, "Too low! Try again.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Too high! Try again.");
                }
            }
            if(!guessedCorrectly) {
                JOptionPane.showMessageDialog(null,"Sorry, you ran out of attempts!\n The number was: "+randomNumber + "\n Better luck next Time!");
            }
        }
        JOptionPane.showMessageDialog(null,"Game over! \n Final Score: "+score);
    }
}
