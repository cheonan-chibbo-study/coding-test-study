package week3.연구소;

import java.util.*;
import java.io.*;

public class Solution {
    static int N, M;
    static int[][] map;
    static int maxSafeArea = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        buildWall(0);
        System.out.println(maxSafeArea);
    }

    // 1. 백트래킹을 이용해 벽 3개를 세우는 모든 조합 찾기
    static void buildWall(int count) {
        if (count == 3) {
            bfs(); // 벽이 3개 세워지면 바이러스 퍼뜨리기 시작
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1; // 벽 세우기
                    buildWall(count + 1);
                    map[i][j] = 0; // 다시 허물기 (백트래킹 핵심)
                }
            }
        }
    }

    // 2. BFS로 바이러스 확산시키기
    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        int[][] copyMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
                if (copyMap[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (copyMap[nx][ny] == 0) {
                        copyMap[nx][ny] = 2; // 바이러스 전파
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        // 3. 안전 영역 계산하기
        calculateSafe(copyMap);
    }

    static void calculateSafe(int[][] copyMap) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) count++;
            }
        }
        maxSafeArea = Math.max(maxSafeArea, count);
    }
}