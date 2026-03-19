package com.teb.practice.games;

import static com.teb.practice.constants.Constants.WORDS;

import static java.lang.Character.toUpperCase;
import static java.lang.Math.random;
import static java.util.Objects.isNull;

import java.io.PrintStream;
import java.util.List;

public class Hangman {

    private static final String DISPLAY_MESSAGE = "%nYour word is: %s%n";

    private int errorCounter;
    private String completeWord;
    private String displayedWord;

    public Hangman() {}

    public Hangman(String testWord) {
        this.completeWord = testWord.toUpperCase();
        this.displayedWord = "_".repeat(testWord.length());
        this.errorCounter = 0;
    }

    public void playHangman() {

        this.errorCounter = 0;

        if (isNull(this.completeWord)) {
            generateWord();
        } else {
            this.displayedWord = "_".repeat(this.completeWord.length());
        }
    }

    public String getDisplayedWord() {

        return displayedWord;
    }

    public String getCompleteWord() {

        return completeWord;
    }

    public int getErrorCounter() {

        return errorCounter;
    }

    public boolean guess(char input) {

        input = toUpperCase(input);
        boolean correct = false;

        for (int i = 0; i < completeWord.length(); i++) {
            char letter = completeWord.charAt(i);
            if (input == letter && displayedWord.charAt(i) == '_') {
                StringBuilder sb = new StringBuilder(displayedWord);

                sb.setCharAt(i, letter);
                displayedWord = sb.toString();
                correct = true;
            }
        }

        // Increment errorCounter only if the guess does not match unrevealed letters
        if (!correct) {
            boolean alreadyRevealed = displayedWord.indexOf(input) != -1;

            if (!alreadyRevealed) {
                errorCounter++;
            }
        }

        return correct;
    }

    public boolean isGameWon() {

        return completeWord.equals(displayedWord);
    }

    public boolean isGameLost() {

        return errorCounter >= 9;
    }

    public String renderHangman() {

        return switch (errorCounter) {
            case 0 ->
                    """
                    |
                    |
                    |
                    |
                    |
                    |____________""";
            case 1 ->
                    """
                    |________
                    |
                    |
                    |
                    |
                    |____________""";
            case 2 ->
                    """
                    |________
                    |    |
                    |
                    |
                    |
                    |____________""";
            case 3 ->
                    """
                    |________
                    |    |
                    |    O
                    |
                    |
                    |____________""";
            case 4 ->
                    """
                    |________
                    |    |
                    |    O
                    |    |
                    |
                    |____________""";
            case 5 ->
                    """
                    |________
                    |    |
                    |  _ O
                    |    |
                    |
                    |____________""";
            case 6 ->
                    """
                    |________
                    |    |
                    |  _ O _
                    |    |
                    |
                    |____________""";
            case 7 ->
                    """
                    |________
                    |    |
                    |  _ O _
                    |    |
                    |   /
                    |____________""";
            case 8 ->
                    """
                    |________
                    |    |
                    |  _ O _
                    |    |
                    |   / \\
                    |____________""";
            case 9 ->
                    """
                    |________
                    |    |
                    |    O
                    |   /|\\
                    |   / \\
                    |____________""";
            default -> throw new IllegalStateException("Invalid counter: " + errorCounter);
        };
    }

    private void generateWord() {

        List<String> words = WORDS;

        this.completeWord = words.get((int) (random() * words.size())).toUpperCase();
        this.displayedWord = "_".repeat(completeWord.length());
    }

    protected void runGame(List<Character> guesses, PrintStream out) {

        out.printf("Welcome to Hangman!%n%n");
        out.printf(
                "Rules:%n"
                        + "A random word will appear on your screen that can have 5-8 blank characters.%n"
                        + "You have 9 attempts to guess the word and save the man from hanging.%n%n");

        playHangman();

        for (char guessChar : guesses) {
            boolean correct = guess(guessChar);

            out.println(renderHangman());
            out.printf(DISPLAY_MESSAGE, displayedWord);
            out.printf("Error counter: %d%n", errorCounter);

            out.println(correct ? "Correct guess!" : "Wrong guess!");

            if (isGameWon()) {
                out.printf(DISPLAY_MESSAGE, displayedWord);
                out.println("Game over! You won!");
                break;
            }

            if (isGameLost()) {
                out.println(renderHangman());
                out.println("Game over! You lost!");
                out.printf("Your word was: %s%n", completeWord);
                break;
            }
        }
    }
}
