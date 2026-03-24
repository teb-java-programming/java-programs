package com.teb.practice.games;

import static com.teb.practice.constants.Constants.SPACE;

import java.io.PrintStream;

public class TicTacToe {

    private static final String[][] GAME_BOARD = new String[3][3];

    public TicTacToe() {
        initialiseBoard();
    }

    public boolean makeMove(String player, int row, int column) {

        if (row < 0 || row > 2 || column < 0 || column > 2) return false;
        if (!SPACE.equals(GAME_BOARD[row][column])) return false;

        GAME_BOARD[row][column] = player;

        return true;
    }

    public boolean hasWinner() {

        return checkRows() || checkColumns() || checkDiagonals();
    }

    private void initialiseBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GAME_BOARD[i][j] = SPACE;
            }
        }
    }

    private boolean checkRows() {

        for (int i = 0; i < 3; i++) {
            String a = GAME_BOARD[i][0];
            String b = GAME_BOARD[i][1];
            String c = GAME_BOARD[i][2];

            if (!SPACE.equals(a) && a.equals(b) && b.equals(c)) return true;
        }

        return false;
    }

    private boolean checkColumns() {

        for (int j = 0; j < 3; j++) {
            String a = GAME_BOARD[0][j];
            String b = GAME_BOARD[1][j];
            String c = GAME_BOARD[2][j];

            if (!SPACE.equals(a) && a.equals(b) && b.equals(c)) return true;
        }

        return false;
    }

    private boolean checkDiagonals() {

        String center = GAME_BOARD[1][1];

        return (!SPACE.equals(center)
                && ((center.equals(GAME_BOARD[0][0]) && center.equals(GAME_BOARD[2][2]))
                        || (center.equals(GAME_BOARD[0][2]) && center.equals(GAME_BOARD[2][0]))));
    }

    public String boardToString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            if (i > 0) sb.append("- - - - -\n");
            for (int j = 0; j < 3; j++) {
                sb.append(GAME_BOARD[i][j]);
                if (j < 2) sb.append(" | ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void printDrawMessage(PrintStream out) {

        if (!hasWinner()) {
            out.println("Game ended in a draw");
        }
    }

    public void playMovesWithOutput(String[][] moves, PrintStream out) {

        for (String[] move : moves) {
            String player = move[0];
            int row = Integer.parseInt(move[1]);
            int col = Integer.parseInt(move[2]);

            if (!makeMove(player, row, col)) {
                out.printf("The cell is already occupied, please select a different cell%n");
            } else {
                out.printf("%nPlayer %s moved to [%d,%d]%n", player, row, col);
                out.println(boardToString());
                if (hasWinner()) {
                    out.printf("%nCongratulations! The winner is: %s%n", player);
                    return;
                }
            }
        }
        printDrawMessage(out);
    }
}
