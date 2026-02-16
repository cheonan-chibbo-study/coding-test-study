package week3.구슬탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    // 4차원 방문 배열: [redY][redX][blueY][blueX]
    static boolean[][][][] visited;

    // 두 구슬의 상태를 한꺼번에 관리할 클래스
    static class State {
        int ry, rx, by, bx, cnt;
        State(int ry, int rx, int by, int bx, int cnt) {
            this.ry = ry; this.rx = rx;
            this.by = by; this.bx = bx;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        int sry = 0, srx = 0, sby = 0, sbx = 0;

        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') { sry = i; srx = j; }
                else if (map[i][j] == 'B') { sby = i; sbx = j; }
            }
        }

        System.out.println(bfs(sry, srx, sby, sbx));
    }

    public static int bfs(int sry, int srx, int sby, int sbx) {
        Queue<State> q = new LinkedList<>();
        q.add(new State(sry, srx, sby, sbx, 0));
        visited[sry][srx][sby][sbx] = true;

        while (!q.isEmpty()) {
            State now = q.poll();

            // 10번 넘게 시도하면 실패
            if (now.cnt >= 10) return 0;

            for (int i = 0; i < 4; i++) {
                // 1. 파란 공 먼저 굴려보기 (벽이나 구멍까지)
                int nbY = now.by, nbX = now.bx;
                while (map[nbY + dy[i]][nbX + dx[i]] != '#') {
                    nbY += dy[i];
                    nbX += dx[i];
                    if (map[nbY][nbX] == 'O') break;
                }

                // 2. 빨간 공 굴리기
                int nrY = now.ry, nrX = now.rx;
                while (map[nrY + dy[i]][nrX + dx[i]] != '#') {
                    nrY += dy[i];
                    nrX += dx[i];
                    if (map[nrY][nrX] == 'O') break;
                }

                // 파란 공이 구멍에 빠지면 실패 (이 경로는 무시)
                if (map[nbY][nbX] == 'O') continue;

                // 빨간 공만 구멍에 빠지면 성공!
                if (map[nrY][nrX] == 'O') return 1;

                // 3. 두 공이 겹쳤을 때 처리 (가장 중요)
                if (nrY == nbY && nrX == nbX) {
                    int redDist = Math.abs(nrY - now.ry) + Math.abs(nrX - now.rx);
                    int blueDist = Math.abs(nbY - now.by) + Math.abs(nbX - now.bx);

                    // 더 많이 움직인 공이 뒤에 있었던 공이므로 한 칸 뒤로 미룸
                    if (redDist > blueDist) {
                        nrY -= dy[i]; nrX -= dx[i];
                    } else {
                        nbY -= dy[i]; nbX -= dx[i];
                    }
                }

                // 4. 처음 방문하는 상태라면 큐에 삽입
                if (!visited[nrY][nrX][nbY][nbX]) {
                    visited[nrY][nrX][nbY][nbX] = true;
                    q.add(new State(nrY, nrX, nbY, nbX, now.cnt + 1));
                }
            }
        }
        return 0;
    }
}