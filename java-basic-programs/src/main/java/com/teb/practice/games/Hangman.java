package com.teb.practice.games;

import static com.teb.practice.constants.Constants.SCAN;
import static com.teb.practice.constants.Constants.WORDS;

import static java.lang.System.exit;
import static java.lang.System.out;

import java.util.List;
import java.util.Random;

public class Hangman {

    private static final Random RANDOM = new Random();
    private static final String DISPLAY_MESSAGE = "%nYour word is: %s%n";

    private static int errorCounter = 0;
    private static String completeWord = "";
    private static String displayedWord = "";

    public static void main(String[] args) {

        out.printf("Welcome to the Hangman game!%n%n");
        out.printf(
                "Rules:%n"
                        + "A random word will appear on your screen that can have 5-8 blank characters.%n"
                        + "You have 9 attempts to guess the word and save the man from hanging.%n%n");
        printPattern(errorCounter);
        playHangman();

        while (true) {
            out.printf("%nDo you want to play again? (y/n): ");
            String option = SCAN.next();
            if (option.equals("y")) {
                errorCounter = 0;
                playHangman();
            } else if (option.equals("n")) {
                out.printf("Thank you for playing!%n");
                break;
            } else {
                exit(0);
            }
        }
    }

    private static void playHangman() {

        out.printf(DISPLAY_MESSAGE, generateWord());
        boolean verifyAlphabet = verifyAlphabet();

        while (!completeWord.equals(displayedWord)) {
            if (verifyAlphabet) {
                out.printf("Correct guess!%n");
                out.printf(DISPLAY_MESSAGE, displayedWord);
                out.printf("Error counter: %d%n", errorCounter);

                verifyAlphabet = verifyAlphabet();
            } else if (errorCounter < 9) {
                ++errorCounter;
                printPattern(errorCounter);

                out.printf("Wrong guess!%n");
                out.printf(DISPLAY_MESSAGE, displayedWord);
                out.printf("Error counter: %d%n", errorCounter);

                if (errorCounter == 9) {
                    out.printf("%nGame over! You lost!%n");
                    out.printf("Your word was: %s%n", completeWord);
                    exit(0);
                }

                verifyAlphabet = verifyAlphabet();
            }
        }
    }

    private static boolean verifyAlphabet() {

        out.printf("%nSelect an alphabet: ");
        String input = SCAN.next();
        boolean validAlphabet = false;
        for (int i = 0; i < completeWord.length(); i++) {
            char letter = completeWord.charAt(i);
            if (input.equalsIgnoreCase(String.valueOf(letter)) && displayedWord.charAt(i) == '_') {
                StringBuilder stringBuilder = new StringBuilder(displayedWord);
                stringBuilder.setCharAt(i, letter);
                displayedWord = stringBuilder.toString();
                validAlphabet = true;
            }
        }
        if (completeWord.equals(displayedWord)) {
            out.printf(DISPLAY_MESSAGE, displayedWord);
            out.printf("%nGame over! You won!");
        }
        return validAlphabet;
    }

    private static String generateWord() {

        List<String> wordGenerator = WORDS;
        completeWord = wordGenerator.get(RANDOM.nextInt(wordGenerator.size())).toUpperCase();

        displayedWord = "_".repeat(completeWord.length());
        return displayedWord;
    }

    private static void printPattern(int errorCounter) {

        switch (errorCounter) {
            case 0:
                out.printf("|%n|%n|%n|%n|%n|____________");
                break;
            case 1:
                out.printf("|________%n|%n|%n|%n|%n|____________%n");
                break;
            case 2:
                out.printf("|________%n|\t|%n|%n|%n|%n|____________%n");
                break;
            case 3:
                out.printf("|________%n|\t|%n|\tO%n|%n|%n|____________%n");
                break;
            case 4:
                out.printf("|________%n|\t|%n|\tO%n|\t|%n|%n|____________%n");
                break;
            case 5:
                out.printf("|________%n|\t  |%n|\t_ O%n|\t  |%n|%n|____________%n");
                break;
            case 6:
                out.printf("|________%n|\t  |%n|\t_ O _%n|\t  |%n|%n|____________%n");
                break;
            case 7:
                out.printf("|________%n|\t  |%n|\t_ O _%n|\t  |%n|\t /%n|____________%n");
                break;
            case 8:
                out.printf("|________%n|\t  |%n|\t_ O _%n|\t  |%n|\t / \\%n|____________%n");
                break;
            case 9:
                out.printf("|________%n|\t  |%n|\t  O%n|\t /|\\%n|\t / \\%n|____________%n");
                break;
            default:
                break;
        }
    }
}
