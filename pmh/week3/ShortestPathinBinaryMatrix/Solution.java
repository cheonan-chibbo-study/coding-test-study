package week3.ShortestPathinBinaryMatrix;
import java.util.*;

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // 시작점이나 끝점이 벽(1)이면 갈 수 없음
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        // 8방향 탐색 (상, 하, 좌, 우 + 대각선 4방향)
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

        Queue<int[]> queue = new LinkedList<>();
        // {행, 열, 현재까지의 거리}
        queue.offer(new int[]{0, 0, 1});
        grid[0][0] = 1; // 방문 처리 (벽으로 만들어버림)

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int distance = curr[2];

            // 목적지 도착! BFS이므로 처음 도착한 이게 무조건 최단 거리
            if (r == n - 1 && c == n - 1) return distance;

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                    grid[nr][nc] = 1; // 방문 처리
                    queue.offer(new int[]{nr, nc, distance + 1});
                }
            }
        }

        return -1; // 끝까지 못 간 경우
    }
}