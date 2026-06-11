import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        int n = maps.length;
        int m = maps[0].length;

        int[][] graph = new int[n + 2][m + 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i + 1][j + 1] = maps[i][j];
            }
        }

        // 메인 로직
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[n + 2][m + 2];

        dq.offer(new int[]{1, 1, 1});
        visited[1][1] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();

            if (cur[0] == n && cur[1] == m) {
                return cur[2];
            }

            for (int[] d : dir) {
                int nextR = cur[0] + d[0];
                int nextC = cur[1] + d[1];

                if (graph[nextR][nextC] != 1 || visited[nextR][nextC]) {
                    continue;
                }

                dq.offer(new int[]{nextR, nextC, cur[2] + 1});
                visited[nextR][nextC] = true;
            }
        }

        return -1;
    }
}