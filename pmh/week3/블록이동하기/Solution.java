package week3.블록이동하기;
import java.util.*;

class Solution {
    class Robot {
        int r1, c1, r2, c2, dist;

        Robot(int r1, int c1, int r2, int c2, int dist) {
            // 좌표를 항상 정렬된 상태로 유지 (방문 체크를 위해)
            if (r1 < r2 || (r1 == r2 && c1 < c2)) {
                this.r1 = r1; this.c1 = c1;
                this.r2 = r2; this.c2 = c2;
            } else {
                this.r1 = r2; this.c1 = c2;
                this.r2 = r1; this.c2 = c1;
            }
            this.dist = dist;
        }

        // 방문 체크를 위해 equals와 hashCode 재정의
        @Override
        public boolean equals(Object o) {
            Robot robot = (Robot) o;
            return r1 == robot.r1 && c1 == robot.c1 && r2 == robot.r2 && c2 == robot.c2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r1, c1, r2, c2);
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        // 외곽에 벽(1)을 둘러서 경계 검사를 생략함
        int[][] newBoard = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) Arrays.fill(newBoard[i], 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i + 1][j + 1] = board[i][j];
            }
        }

        return bfs(newBoard, n);
    }

    public int bfs(int[][] board, int n) {
        Queue<Robot> q = new LinkedList<>();
        Set<Robot> visited = new HashSet<>();

        Robot start = new Robot(1, 1, 1, 2, 0);
        q.add(start);
        visited.add(start);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            Robot cur = q.poll();

            // 목표 도달 확인
            if ((cur.r1 == n && cur.c1 == n) || (cur.r2 == n && cur.c2 == n)) {
                return cur.dist;
            }

            // 1. 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nr1 = cur.r1 + dr[i], nc1 = cur.c1 + dc[i];
                int nr2 = cur.r2 + dr[i], nc2 = cur.c2 + dc[i];

                if (board[nr1][nc1] == 0 && board[nr2][nc2] == 0) {
                    addNext(new Robot(nr1, nc1, nr2, nc2, cur.dist + 1), q, visited);
                }
            }

            // 2. 회전
            if (cur.r1 == cur.r2) { // 가로 방향일 때
                for (int i : new int[]{-1, 1}) { // 위(-1), 아래(1) 체크
                    if (board[cur.r1 + i][cur.c1] == 0 && board[cur.r2 + i][cur.c2] == 0) {
                        addNext(new Robot(cur.r1, cur.c1, cur.r1 + i, cur.c1, cur.dist + 1), q, visited);
                        addNext(new Robot(cur.r2, cur.c2, cur.r2 + i, cur.c2, cur.dist + 1), q, visited);
                    }
                }
            } else { // 세로 방향일 때
                for (int i : new int[]{-1, 1}) { // 왼쪽(-1), 오른쪽(1) 체크
                    if (board[cur.r1][cur.c1 + i] == 0 && board[cur.r2][cur.c2 + i] == 0) {
                        addNext(new Robot(cur.r1, cur.c1, cur.r1, cur.c1 + i, cur.dist + 1), q, visited);
                        addNext(new Robot(cur.r2, cur.c2, cur.r2, cur.c2 + i, cur.dist + 1), q, visited);
                    }
                }
            }
        }
        return 0;
    }

    private void addNext(Robot next, Queue<Robot> q, Set<Robot> visited) {
        if (!visited.contains(next)) {
            q.add(next);
            visited.add(next);
        }
    }
}