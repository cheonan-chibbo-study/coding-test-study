import java.util.*;

class Solution {

    int n;
    List<List<String>> answer = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        this.n = n;

        // 메인 로직
        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ".";
            }
        }

        backTracking(0, board, 0);
        return answer;
    }

    private void backTracking(int count, String[][] board, int rowStart) {
        if (count == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < n; j++) {
                    sb.append(board[i][j]);
                }

                list.add(sb.toString());
            }

            answer.add(list);
            return;
        }

        for (int row = rowStart; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (!isSafe(board, row, col)) {
                    continue;
                }

                board[row][col] = "Q";
                backTracking(count + 1, board, row + 1);
                board[row][col] = ".";
            }
        }
    }

    private boolean isSafe(String[][] board, int r, int c) {
        int[][] dir = {{-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int d = 0; d < dir.length; d++) {
            int nextR = r + dir[d][0];
            int nextC = c + dir[d][1];

            while (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n) {
                if (board[nextR][nextC] == "Q") {
                    return false;
                }

                nextR += dir[d][0];
                nextC += dir[d][1];
            }
        }

        return true;
    }
}