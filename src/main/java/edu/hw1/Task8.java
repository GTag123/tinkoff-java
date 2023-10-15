package edu.hw1;

public class Task8 {
    private static final int BOARD_SIZE = 8;

    private Task8() {
    }

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] board) {
        if (board.length != BOARD_SIZE || board[0].length != BOARD_SIZE) {
            return false;
        }

        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 1 && isAttacked(board, i, j, dx, dy)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isAttacked(int[][] board, int x, int y, int[] dx, int[] dy) {
        for (int k = 0; k < BOARD_SIZE; k++) {
            int newX = x + dx[k];
            int newY = y + dy[k];
            if (isValidPosition(newX, newY) && board[newX][newY] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }
}
