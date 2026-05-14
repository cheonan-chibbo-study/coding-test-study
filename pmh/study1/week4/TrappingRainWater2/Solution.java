package study1.week4.TrappingRainWater2;

import java.util.*;

class Solution {
    static class Node {
        int i, j, h;
        Node(int i, int j, int h) {
            this.i = i;
            this.j = j;
            this.h = h;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        int r = heightMap.length;
        if (r == 0) return 0;
        int c = heightMap[0].length;
        if (c == 0) return 0;

        // 1줄/1칸이면 물 못 고임
        if (r < 3 || c < 3) return 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.h, b.h)
        );
        boolean[][] visited = new boolean[r][c];

        // 경계 채우기 (좌/우)
        for (int i = 0; i < r; i++) {
            pq.offer(new Node(i, 0, heightMap[i][0]));
            pq.offer(new Node(i, c - 1, heightMap[i][c - 1]));
            visited[i][0] = true;
            visited[i][c - 1] = true;
        }

        // 경계 채우기 (상/하) - 코너 중복 방지로 1..c-2
        for (int j = 1; j < c - 1; j++) {
            pq.offer(new Node(0, j, heightMap[0][j]));
            pq.offer(new Node(r - 1, j, heightMap[r - 1][j]));
            visited[0][j] = true;
            visited[r - 1][j] = true;
        }

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        long water = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int k = 0; k < 4; k++) {
                int nr = cur.i + dr[k];
                int nc = cur.j + dc[k];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;

                int nh = heightMap[nr][nc];
                if (nh < cur.h) {
                    water += (cur.h - nh);
                }

                pq.offer(new Node(nr, nc, Math.max(cur.h, nh)));
            }
        }

        return (int) water;
    }
}