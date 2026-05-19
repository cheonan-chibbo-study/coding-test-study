import java.util.*;

class Solution {

    int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    char[][] board;
    boolean[][] visited;

    public char[][] updateBoard(char[][] board, int[] click) {
        this.board = board;
        this.visited = new boolean[board.length][board[0].length];

        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(click);
        visited[click[0]][click[1]] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            char curObj = board[cur[0]][cur[1]];

            if (curObj == 'M') {
                board[cur[0]][cur[1]] = 'X';
                break;
            }

            int mineCount = 0;
            for (int[] d : dir) {
                int nRow = cur[0] + d[0];
                int nCol = cur[1] + d[1];

                if (!isSafe(nRow, nCol) || visited[nRow][nCol]) {
                    continue;
                }

                char nextObj = board[nRow][nCol];
                if (nextObj == 'M') {
                    mineCount += 1;
                }
            }

            if (mineCount == 0) {
                board[cur[0]][cur[1]] = 'B';
            } else {
                board[cur[0]][cur[1]] = Character.forDigit(mineCount, 10);
                continue;
            }

            for (int[] d : dir) {
                int nRow = cur[0] + d[0];
                int nCol = cur[1] + d[1];

                if (!isSafe(nRow, nCol) || visited[nRow][nCol] || board[nRow][nCol] == 'M') {
                    continue;
                }

                dq.offer(new int[]{nRow, nCol});
                visited[nRow][nCol] = true;
            }
        }

        return board;
    }

    private boolean isSafe(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
    }
}