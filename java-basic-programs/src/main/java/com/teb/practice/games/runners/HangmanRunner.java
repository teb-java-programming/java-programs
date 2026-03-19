package com.teb.practice.games.runners;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

import com.teb.practice.games.Hangman;

public class HangmanRunner {

    public static void main(String[] args) {

        Hangman game = new Hangman();

        out.println("Welcome to Hangman!\n");
        out.println("Guess one letter at a time.");
        out.println("You have 9 incorrect attempts. Good luck!\n");

        game.playHangman();

        while (true) {
            String input = SCAN.nextLine();

            out.println(game.renderHangman());
            out.printf("Word: %s%n", game.getDisplayedWord());
            out.printf("Errors: %d%n", game.getErrorCounter());
            out.print("Enter a letter: ");

            if (input == null
                    || input.isBlank()
                    || input.length() != 1
                    || !Character.isLetter(input.charAt(0))) {
                out.println("Invalid input. Please enter a single letter.\n");
                continue;
            }

            char guess = input.charAt(0);
            boolean correct = game.guess(guess);
            out.println(correct ? "Correct guess!\n" : "Wrong guess!\n");

            if (game.isGameWon()) {
                out.println(game.renderHangman());
                out.println("Game over! You won!");
                out.printf("The word is: %s%n%n", game.getDisplayedWord());
                break;
            }

            if (game.isGameLost()) {
                out.println(game.renderHangman());
                out.println("Game over! You lost!");
                out.printf("The word was: %s%n", game.getCompleteWord());
                break;
            }
        }
    }
}
