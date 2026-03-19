package com.teb.practice.games;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.lang.System.out;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TicTacToeTest {

    private final ByteArrayOutputStream stream = new ByteArrayOutputStream();
    private TicTacToe game;

    @BeforeEach
    void setUp() {

        game = new TicTacToe();
    }

    @Test
    void testMakeMoveBranches() {

        assertFalse(game.makeMove("X", -1, 1));
        assertFalse(game.makeMove("O", 3, 1));
        assertFalse(game.makeMove("X", 1, -1));
        assertFalse(game.makeMove("O", 1, 3));
        assertTrue(game.makeMove("X", 0, 0));
    }

    @ParameterizedTest
    @CsvSource({"row, X", "column, O"})
    void testWinningRowOrColumn(String type, String player) {

        switch (type) {
            case "row" -> {
                for (int c = 0; c < 3; c++) {
                    game.makeMove(player, 0, c);
                }
            }
            case "column" -> {
                for (int r = 0; r < 3; r++) {
                    game.makeMove(player, r, 0);
                }
            }
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        }

        assertTrue(game.hasWinner());
    }

    @Test
    void testWinningDiagonals() {

        game.makeMove("X", 0, 0);
        game.makeMove("X", 1, 1);
        game.makeMove("X", 2, 2);

        assertTrue(game.hasWinner());

        game = new TicTacToe();
        game.makeMove("O", 0, 2);
        game.makeMove("O", 1, 1);
        game.makeMove("O", 2, 0);

        assertTrue(game.hasWinner());
    }

    @Test
    void testAntiDiagonalPartialNoWinner() {

        game.makeMove("X", 1, 1);
        game.makeMove("X", 0, 2);

        assertFalse(game.hasWinner());
    }

    @Test
    void testPartialRowsColumnsDiagonalsNoWinner() {

        for (int r = 0; r < 3; r++) {
            game = new TicTacToe();
            for (int c = 0; c < 2; c++) {
                game.makeMove("X", r, c);
            }

            assertFalse(game.hasWinner());
        }
    }

    @Test
    void testDrawGameWithOutput() {

        String[][] moves = {
            {"X", "0", "0"}, {"O", "0", "1"}, {"X", "0", "2"},
            {"X", "1", "0"}, {"O", "1", "1"}, {"O", "1", "2"},
            {"O", "2", "0"}, {"X", "2", "1"}, {"X", "2", "2"}
        };

        game.playMovesWithOutput(moves, new PrintStream(stream));

        String output = stream.toString();

        assertFalse(game.hasWinner());
        assertTrue(output.contains("Game ended in a draw."));
        assertTrue(output.contains("X") && output.contains("O"));
        assertFalse(output.contains("Congratulations!"));
    }

    @Test
    void testWinnerOutputAndOccupiedCell() {

        String[][] moves = {
            {"X", "1", "0"},
            {"O", "2", "1"},
            {"X", "1", "1"},
            {"O", "1", "2"},
            {"X", "1", "2"},
            {"X", "0", "0"},
            {"O", "2", "0"},
            {"X", "0", "2"},
            {"O", "2", "2"}
        };

        game.playMovesWithOutput(moves, new PrintStream(stream));

        out.println(stream);

        String output = stream.toString();

        assertTrue(output.contains("The cell is already occupied, please select a different cell"));
        assertTrue(output.contains("Congratulations! The winner is: O"));
        assertFalse(output.contains("Game ended in a draw."));
    }

    @Test
    void testPrintDrawMessageFalse() {

        game.makeMove("X", 0, 0);
        game.makeMove("X", 1, 1);
        game.makeMove("X", 2, 2);

        game.printDrawMessage(new PrintStream(stream));

        assertFalse(stream.toString().contains("Game ended in a draw."));
    }
}
