package week7.블록이동하기;
import java.util.*;

class Solution {

    class Robot {
        int r1, c1, r2, c2, dist;

        public Robot(int r1, int c1, int r2, int c2, int dist) {
            // 두 좌표를 항상 정렬해서 같은 상태를 하나로 관리
            if (r1 < r2 || (r1 == r2 && c1 < c2)) {
                this.r1 = r1;
                this.c1 = c1;
                this.r2 = r2;
                this.c2 = c2;
            } else {
                this.r1 = r2;
                this.c1 = c2;
                this.r2 = r1;
                this.c2 = c1;
            }
            this.dist = dist;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;

        // 바깥을 벽(1)으로 감싼 새 보드
        int[][] newBoard = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(newBoard[i], 1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i + 1][j + 1] = board[i][j];
            }
        }

        return bfs(newBoard, n);
    }

    public int bfs(int[][] board, int n) {
        Queue<Robot> q = new LinkedList<>();

        // 좌표는 1 ~ n 을 쓰므로 visited도 n+1 이상 필요
        boolean[][][][] visited = new boolean[n + 1][n + 1][n + 1][n + 1];

        Robot start = new Robot(1, 1, 1, 2, 0);
        q.offer(start);
        visited[start.r1][start.c1][start.r2][start.c2] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            Robot cur = q.poll();

            // 두 칸 중 하나라도 (n,n)에 도착하면 끝
            if ((cur.r1 == n && cur.c1 == n) || (cur.r2 == n && cur.c2 == n)) {
                return cur.dist;
            }

            // 1. 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nr1 = cur.r1 + dr[i];
                int nc1 = cur.c1 + dc[i];
                int nr2 = cur.r2 + dr[i];
                int nc2 = cur.c2 + dc[i];

                if (board[nr1][nc1] == 0 && board[nr2][nc2] == 0) {
                    Robot next = new Robot(nr1, nc1, nr2, nc2, cur.dist + 1);

                    if (!visited[next.r1][next.c1][next.r2][next.c2]) {
                        visited[next.r1][next.c1][next.r2][next.c2] = true;
                        q.offer(next);
                    }
                }
            }

            // 2. 회전
            if (cur.r1 == cur.r2) {
                // 가로 상태
                for (int d : new int[]{-1, 1}) {
                    // 위쪽 또는 아래쪽 두 칸이 비어 있어야 회전 가능
                    if (board[cur.r1 + d][cur.c1] == 0 && board[cur.r2 + d][cur.c2] == 0) {
                        // 왼쪽 칸 기준 회전
                        Robot next1 = new Robot(cur.r1, cur.c1, cur.r1 + d, cur.c1, cur.dist + 1);
                        if (!visited[next1.r1][next1.c1][next1.r2][next1.c2]) {
                            visited[next1.r1][next1.c1][next1.r2][next1.c2] = true;
                            q.offer(next1);
                        }

                        // 오른쪽 칸 기준 회전
                        Robot next2 = new Robot(cur.r2, cur.c2, cur.r2 + d, cur.c2, cur.dist + 1);
                        if (!visited[next2.r1][next2.c1][next2.r2][next2.c2]) {
                            visited[next2.r1][next2.c1][next2.r2][next2.c2] = true;
                            q.offer(next2);
                        }
                    }
                }
            } else {
                // 세로 상태
                for (int d : new int[]{-1, 1}) {
                    // 왼쪽 또는 오른쪽 두 칸이 비어 있어야 회전 가능
                    if (board[cur.r1][cur.c1 + d] == 0 && board[cur.r2][cur.c2 + d] == 0) {
                        // 위쪽 칸 기준 회전
                        Robot next1 = new Robot(cur.r1, cur.c1, cur.r1, cur.c1 + d, cur.dist + 1);
                        if (!visited[next1.r1][next1.c1][next1.r2][next1.c2]) {
                            visited[next1.r1][next1.c1][next1.r2][next1.c2] = true;
                            q.offer(next1);
                        }

                        // 아래쪽 칸 기준 회전
                        Robot next2 = new Robot(cur.r2, cur.c2, cur.r2, cur.c2 + d, cur.dist + 1);
                        if (!visited[next2.r1][next2.c1][next2.r2][next2.c2]) {
                            visited[next2.r1][next2.c1][next2.r2][next2.c2] = true;
                            q.offer(next2);
                        }
                    }
                }
            }
        }

        return 0;
    }
}