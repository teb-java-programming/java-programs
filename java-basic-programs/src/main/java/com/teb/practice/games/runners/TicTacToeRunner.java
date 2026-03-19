package com.teb.practice.games.runners;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

import com.teb.practice.games.TicTacToe;

public class TicTacToeRunner {

    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();

        String currentPlayer = "X";
        int moveCount = 0;

        out.println("Welcome to Tic Tac Toe!");
        out.println("Enter your move as: row column (e.g., 0 1)");
        out.println("Rows and columns are 0, 1, 2.\n");

        while (true) {
            int row;
            int column;

            out.printf("Player %s, your move: ", currentPlayer);

            try {
                row = SCAN.nextInt();
                column = SCAN.nextInt();
            } catch (Exception e) {
                out.println("Invalid input. Please enter two numbers (row and column).\n");
                SCAN.nextLine();
                continue;
            }

            boolean moveMade = game.makeMove(currentPlayer, row, column);

            if (!moveMade) {
                out.println("Invalid move. Cell is either occupied or out of bounds.\n");
                continue;
            }

            moveCount++;
            out.println("\n" + game.boardToString());

            if (game.hasWinner()) {
                out.printf("Congratulations! Player %s wins!\n", currentPlayer);
                break;
            }

            if (moveCount == 9) {
                out.println("Game ended in a draw.");
                break;
            }

            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }
}
