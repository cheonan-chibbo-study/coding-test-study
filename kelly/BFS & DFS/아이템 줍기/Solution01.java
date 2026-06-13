import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        int graphSize = 104;
        int[][] graph = new int[graphSize][graphSize];

        for (int[] r : rectangle) {
            int x1 = r[0] * 2;
            int y1 = r[1] * 2;
            int x2 = r[2] * 2;
            int y2 = r[3] * 2;

            for (int row = y1; row <= y2; row++) {
                for (int col = x1; col <= x2; col++) {
                    if ((row > y1 && row < y2) && (col > x1 && col < x2)) {
                        graph[row][col] = -1;
                    } else if (graph[row][col] == 0) {
                        graph[row][col] = 1;
                    }
                }
            }
        }

        // 메인 로직
        int startR = characterY * 2;
        int startC = characterX * 2;
        int targetR = itemY * 2;
        int targetC = itemX * 2;

        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[graphSize][graphSize];

        dq.offer(new int[]{startR, startC, 0});
        visited[startR][startC] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();

            if (cur[0] == targetR && cur[1] == targetC) {
                return cur[2] / 2;
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