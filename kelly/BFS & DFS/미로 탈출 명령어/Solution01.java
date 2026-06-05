import java.util.*;

class Solution {
    int r;
    int c;
    int k;
    int[][] board;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.r = r;
        this.c = c;
        this.k = k;

        List<Node> dir = List.of(
            new Node(1, 0, "d"),
            new Node(0, -1, "l"),
            new Node(0, 1, "r"),
            new Node(-1, 0, "u")
        );

        board = new int[n + 2][m + 2];
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {
                board[row][col] = 1;
            }
        }

        // 메인 로직
        if (manhattan(x, y) > k) {
            return "impossible";
        }

        Deque<Node> dq = new ArrayDeque<>();
        dq.offer(new Node(x, y, ""));

        while (!dq.isEmpty()) {
            Node cur = dq.poll();

            if (cur.isArrive()) {
                if (cur.step.length() == k) {
                    return cur.step;
                }

                if ((k - cur.step.length()) % 2 != 0) {
                    return "impossible";
                }
            }

            for (Node d : dir) {
                int nextR = cur.row + d.row;
                int nextC = cur.col + d.col;

                if (!isSafe(nextR, nextC, cur.step.length() + 1)) {
                    continue;
                }

                dq.offer(new Node(nextR, nextC, cur.step + d.step));
                break;
            }
        }

        return "impossible";
    }

    private int manhattan(int row, int col) {
        return Math.abs(r - row) + Math.abs(c - col);
    }

    private boolean isSafe(int row, int col, int step) {
        if (board[row][col] != 1) {
            return false;
        }

        if (manhattan(row, col) + step > k) {
            return false;
        }

        return true;
    }

    class Node {
        int row;
        int col;
        String step;

        public Node(int row, int col, String step) {
            this.row = row;
            this.col = col;
            this.step = step;
        }

        public boolean isArrive() {
            return row == r && col == c;
        }
    }
}