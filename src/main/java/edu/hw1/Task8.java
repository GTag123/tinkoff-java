package edu.hw1;

public class Task8 {
    private Task8() {
    }

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] board) {
        if (board.length != 8 || board[0].length != 8) {
            return false;
        }

        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) {
                    for (int k = 0; k < 8; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 0 && x < 8 && y >= 0 && y < 8 && board[x][y] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
