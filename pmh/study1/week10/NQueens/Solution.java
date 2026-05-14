package study1.week10.NQueens;
import java.util.*;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        boolean[][] board = new boolean[n][n];

        dfs(0, n, new ArrayList<>(), result, board);
        return result;
    }

    public void dfs(int row, int n, List<String> current, List<List<String>> result, boolean[][] board) {
        // 퀸을 모든 행에 다 놓은 경우
        if (row == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 현재 row의 각 col에 퀸을 놓아보기
        for (int col = 0; col < n; col++) {
            if (queenCheck(row, col, board, n)) {
                board[row][col] = true;

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (i == col) sb.append("Q");
                    else sb.append(".");
                }

                current.add(sb.toString());

                dfs(row + 1, n, current, result, board);

                // 백트래킹
                current.remove(current.size() - 1);
                board[row][col] = false;
            }
        }
    }

    public boolean queenCheck(int row, int col, boolean[][] board, int n) {
        // 같은 열 위쪽 체크
        for (int i = 0; i < row; i++) {
            if (board[i][col]) return false;
        }

        // 왼쪽 위 대각선 체크
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            if (board[row - i][col - i]) return false;
        }

        // 오른쪽 위 대각선 체크
        for (int i = 1; row - i >= 0 && col + i < n; i++) {
            if (board[row - i][col + i]) return false;
        }

        return true;
    }
}