package com.teb.practice.games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.lang.System.out;
import static java.util.Collections.nCopies;
import static java.util.List.of;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class HangmanTest {

    private static final String CORRECT_GUESS = "Correct guess!";
    private static final String WRONG_GUESS = "Wrong guess!";

    private final ByteArrayOutputStream stream = new ByteArrayOutputStream();
    private Hangman hangman;

    static Stream<Arguments> gameScenarios() {

        return Stream.of(
                Arguments.of("NOISE", of('I', 'S', 'N', 'O', 'E'), true, false, 0),
                Arguments.of("ULTIMATE", nCopies(9, 'P'), false, true, 9));
    }

    static Stream<Arguments> gameSimulationScenarios() {

        return Stream.of(
                Arguments.of(
                        "TAPAS",
                        of('P', 'T', 'A', 'S'),
                        true,
                        false,
                        0,
                        CORRECT_GUESS,
                        "Game over! You won!"),
                Arguments.of(
                        "WHISKY",
                        nCopies(9, 'Z'),
                        false,
                        true,
                        9,
                        WRONG_GUESS,
                        "Game over! You lost!"),
                Arguments.of(
                        "VACUUM",
                        of('C', 'A', 'T', 'U', 'E', 'P', 'S', 'V', 'M'),
                        true,
                        false,
                        4,
                        CORRECT_GUESS,
                        "Game over! You won!"));
    }

    @Test
    void testPlayHangmanInitializesWord() {

        hangman = new Hangman("LUSCIOUS");
        hangman.playHangman();

        assertEquals("________", hangman.getDisplayedWord());
        assertEquals(0, hangman.getErrorCounter());
        assertEquals("LUSCIOUS", hangman.getCompleteWord());
    }

    @ParameterizedTest
    @CsvSource({"OUTSMART, O, 0", "PHOENIX, W, 1"})
    void testOutput(String word, char input, int errorCount) {

        hangman = new Hangman(word);
        hangman.playHangman();

        hangman.guess(input);

        assertEquals(errorCount, hangman.getErrorCounter());
    }

    @Test
    void testRepeatedCorrectGuessDoesNotIncrementError() {

        hangman = new Hangman("OASIS");
        hangman.playHangman();

        boolean firstGuess = hangman.guess('S');
        boolean secondGuess = hangman.guess('S');

        assertTrue(firstGuess);
        assertFalse(secondGuess);
        assertEquals('S', hangman.getDisplayedWord().charAt(2));
        assertEquals('S', hangman.getDisplayedWord().charAt(4));
        assertEquals(0, hangman.getErrorCounter());
    }

    @ParameterizedTest
    @MethodSource("gameScenarios")
    void testGameOutcome(
            String word,
            List<Character> guesses,
            boolean expectedWon,
            boolean expectedLost,
            int errorCount) {

        hangman = new Hangman(word);
        hangman.playHangman();

        for (char guess : guesses) {
            hangman.guess(guess);
        }

        assertEquals(expectedWon, hangman.isGameWon());
        assertEquals(expectedLost, hangman.isGameLost());
        assertEquals(errorCount, hangman.getErrorCounter());
    }

    @ParameterizedTest
    @MethodSource("gameSimulationScenarios")
    void testSimulatedRunGame(
            String word,
            List<Character> guesses,
            boolean expectedWon,
            boolean expectedLost,
            int errorCount,
            String expectedGuessMessage,
            String expectedResultMessage) {

        hangman = new Hangman(word);
        hangman.runGame(guesses, new PrintStream(stream));

        String output = stream.toString();

        assertTrue(output.contains("|"));
        assertTrue(output.contains(expectedGuessMessage));
        assertTrue(output.contains(expectedResultMessage));

        assertEquals(expectedWon, hangman.isGameWon());
        assertEquals(expectedLost, hangman.isGameLost());
        assertEquals(errorCount, hangman.getErrorCounter());
    }

    @Test
    void testEmptyInputDoesNotFail() {

        hangman = new Hangman("XYLEM");
        hangman.runGame(new ArrayList<>(), new PrintStream(stream));

        assertFalse(stream.toString().contains(WRONG_GUESS));
        assertEquals(0, hangman.getErrorCounter());
    }

    @Test
    void testRunGameSimulatedWinWithStagesPrinted() {

        hangman = new Hangman("ERROR");
        List<Character> guesses = of('E', 'A', 'O', 'E', 'R');

        // Pass System.out so stages are printed to console during test
        hangman.runGame(guesses, out);

        assertTrue(hangman.isGameWon());
        assertFalse(hangman.isGameLost());
    }

    @Test
    void testExceptionThrownForGenerateWordAndRenderDefault() throws Exception {

        hangman = new Hangman();
        hangman.playHangman();

        // Force default branch in renderHangman() via reflection
        Field field = Hangman.class.getDeclaredField("errorCounter");
        field.setAccessible(true);
        field.setInt(hangman, 16);

        IllegalStateException e =
                assertThrows(IllegalStateException.class, () -> hangman.renderHangman());
        assertTrue(e.getMessage().contains("Invalid counter"));
    }
}
