import java.util.*;

class Solution {

    char[][] board;
    List<int[]> emptyPos;

    public void solveSudoku(char[][] board) {
        this.board = board;

        this.emptyPos = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    emptyPos.add(new int[]{row, col});
                }
            }
        }

        // 메인 로직
        recursive(0);
    }

    private boolean recursive(int target) {
        if (target == emptyPos.size()) {
            return true;
        }

        int tRow = emptyPos.get(target)[0];
        int tCol = emptyPos.get(target)[1];

        for (int n = 1; n <= 9; n++) {
            if (!isSafe(tRow, tCol, (char) (n + '0'))) {
                continue;
            }

            board[tRow][tCol] = (char) (n + '0');
            if (recursive(target + 1)) {
                return true;
            }

            board[tRow][tCol] = '.';
        }

        return false;
    }

    private boolean isSafe(int r, int c, char n) {
        // 가로 체크
        for (int col = 0; col < 9; col++) {
            if (board[r][col] == n) {
                return false;
            }
        }

        // 세로 체크
        for (int row = 0; row < 9; row++) {
            if (board[row][c] == n) {
                return false;
            }
        }

        // 3x3 격자 체크
        int sRow = (r / 3) * 3;
        int sCol = (c / 3) * 3;
        for (int row = sRow; row < sRow + 3; row++) {
            for (int col = sCol; col < sCol + 3; col++) {
                if (board[row][col] == n) {
                    return false;
                }
            }
        }

        return true;
    }
}