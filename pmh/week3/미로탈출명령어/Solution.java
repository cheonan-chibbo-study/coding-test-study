package week3.미로탈출명령어;

import java.util.*;

class SolutionBFS {
    private final char[] dir = {'d', 'l', 'r', 'u'};
    private final int[] dr = {1, 0, 0, -1};
    private final int[] dc = {0, -1, 1, 0};

    static class Node {
        int x, y, d;
        String path;
        Node(int x, int y, int d, String path) {
            this.x = x; this.y = y; this.d = d; this.path = path;
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist0 = manhattan(x, y, r, c);
        if (dist0 > k) return "impossible";
        if (((k - dist0) & 1) == 1) return "impossible";

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y, 0, ""));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int remain = k - cur.d;
            int dist = manhattan(cur.x, cur.y, r, c);

            // 가지치기
            if (dist > remain) continue;
            if ((remain - dist) % 2 == 1) continue;
            //((remain - dist) & 1) == 1 이것도 가능 비트연산
            if (cur.d == k) {
                if (cur.x == r && cur.y == c) return cur.path; // BFS상 최초 도달 = 사전순 최소
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dr[i];
                int ny = cur.y + dc[i];
                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;

                q.add(new Node(nx, ny, cur.d + 1, cur.path + dir[i]));
            }
        }

        return "impossible";
    }

    private int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
