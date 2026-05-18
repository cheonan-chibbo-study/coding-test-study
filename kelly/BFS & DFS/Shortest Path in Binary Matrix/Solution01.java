import java.util.*;

class Solution {

    int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    int[][] grid;
    boolean[][] visited;

    public int shortestPathBinaryMatrix(int[][] grid) {
        this.grid = grid;

        if (grid[0][0] == 1) {
            return -1;
        }

        Deque<int[]> dq = new ArrayDeque<>();
        visited = new boolean[grid.length][grid[0].length];
        dq.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();

            if (cur[0] == grid.length - 1 && cur[1] == grid[cur[0]].length - 1) {
                return cur[2];
            }

            for (int[] d : dir) {
                int nRow = cur[0] + d[0];
                int nCol = cur[1] + d[1];

                if (isSafe(nRow, nCol)) {
                    dq.offer(new int[]{nRow, nCol, cur[2] + 1});
                    visited[nRow][nCol] = true;
                }
            }
        }

        return -1;
    }

    private boolean isSafe(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length &&
            grid[row][col] == 0 && !visited[row][col];
    }
}