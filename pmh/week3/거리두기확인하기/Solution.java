package week3.거리두기확인하기;

import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            char[][] room = new char[5][5];
            for (int j = 0; j < 5; j++) {
                room[j] = places[i][j].toCharArray();
            }

            boolean isSafe = true;
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (room[r][c] == 'P') {
                        // 응시자가 있는 곳마다 BFS를 돌려 주변 2칸 안에 다른 P가 있는지 확인
                        if (!bfs(room, r, c)) {
                            isSafe = false;
                            break;
                        }
                    }
                }
                if (!isSafe) break;
            }
            answer[i] = isSafe ? 1 : 0;
        }
        return answer;
    }

    public boolean bfs(char[][] room, int startR, int startC) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startR, startC, 0});

        boolean[][] visited = new boolean[5][5];
        visited[startR][startC] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];

            // 1. 거리가 0보다 크고 2 이하인데 'P'를 발견하면 거리두기 위반!
            if (dist > 0 && room[r][c] == 'P') {
                return false;
            }

            // 2. 이미 거리가 2라면, 더 이상 옆으로 퍼져나갈 필요가 없음
            if (dist >= 2) continue;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && !visited[nr][nc]) {
                    // 3. 파티션('X')이 아니어야만 통과 가능 (벽 역할)
                    if (room[nr][nc] != 'X') {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc, dist + 1});
                    }
                }
            }
        }
        return true;
    }
}