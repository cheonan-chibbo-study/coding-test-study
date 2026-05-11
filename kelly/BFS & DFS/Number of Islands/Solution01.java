import java.util.*;

class Solution {

    char[][] grid;

    int[][] dir = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
    int answer = 0;

    public int numIslands(char[][] grid) {
        this.grid = grid;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '1') {
                    bfs(row, col);
                    answer += 1;
                }
            }
        }

        return answer;
    }

    private void bfs(int sRow, int sCol) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{sRow, sCol});
        grid[sRow][sCol] = '0';

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();

            for (int[] d : dir) {
                int nextR = cur[0] + d[0];
                int nextC = cur[1] + d[1];

                if (!isSafe(nextR, nextC)) {
                    continue;
                }

                dq.add(new int[]{nextR, nextC});
                grid[nextR][nextC] = '0';
            }
        }
    }

    private boolean isSafe(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length && grid[row][col] == '1';
    }
}