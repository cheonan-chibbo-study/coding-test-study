package week1.SudokuSolver;
class Solution {
    public void solveSudoku(char[][] board) {
        backTrace(board, 0);
    }

    public boolean backTrace(char[][] board, int s) {
        if (s == 81) {
            return true;
        }
        int row = s/9;
        int col = s%9;

        if (board[row][col] != '.') {
           return backTrace(board, s +1);
        }

        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, col, row, c)) {

                board[row][col] = c;


                if (backTrace(board, s + 1)) {
                    return true;
                } else {
                    board[row][col] = '.';
                }


            }

        }
        return false;
    }

    public boolean isValid(char[][] board, int col, int row, char c) {
        for (int i = 0; i < 9; i++) {
            // 가로열체크
            if(board[row][i] == c) return false;
            //세로열 체크
            if(board[i][col] == c) return false;
            //3x3 체크
            if(board[3 * (row /3) +i/3][3 * (col /3) +i%3] == c) return false;


        }
        return true;


    }


}