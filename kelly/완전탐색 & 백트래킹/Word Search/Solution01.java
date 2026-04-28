class Solution {

    int[][] dir = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
    char[][] board;
    String word;
    int rowSize;
    int colSize;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        this.rowSize = board.length;
        this.colSize = board[0].length;

        // 메인 로직
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (board[row][col] != word.charAt(0)) {
                    continue;
                }

                boolean[][] visited = new boolean[rowSize][colSize];
                visited[row][col] = true;
                if (search(row, col, visited, 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search(int sRow, int sCol, boolean[][] visited, int step) {
        if (step == word.length()) {
            return true;
        }

        for (int d = 0; d < 4; d++) {
            int nRow = sRow + dir[d][0];
            int nCol = sCol + dir[d][1];

            if (!isSafe(nRow, nCol, visited) || board[nRow][nCol] != word.charAt(step)) {
                continue;
            }

            visited[nRow][nCol] = true;
            if (search(nRow, nCol, visited, step + 1)) {
                return true;
            }

            visited[nRow][nCol] = false;
        }

        return false;
    }

    private boolean isSafe(int r, int c, boolean[][] visited) {
        return r >= 0 && r < rowSize && c >= 0 && c < colSize && !visited[r][c];
    }
}