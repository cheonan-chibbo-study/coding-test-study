package week3.연구소3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static int[][] map;
    static List<int[]> virusList = new ArrayList<>(); // 모든 바이러스 위치
    static int[] selected; // 선택된 바이러스 인덱스
    static int minTime = Integer.MAX_VALUE;
    static int emptySpaces = 0; // 빈칸(0)의 총 개수

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        selected = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virusList.add(new int[]{i, j});
                if (map[i][j] == 0) emptySpaces++;
            }
        }

        // 빈칸이 하나도 없으면 바로 0 출력
        if (emptySpaces == 0) {
            System.out.println(0);
            return;
        }

        combination(0, 0);
        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    // 1. 바이러스 M개를 선택하는 조합
    static void combination(int start, int count) {
        if (count == M) {
            bfs();
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            selected[count] = i;
            combination(i + 1, count + 1);
        }
    }

    // 2. 시간 측정을 위한 BFS
    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[N][N];
        for (int[] row : dist) Arrays.fill(row, -1);

        for (int idx : selected) {
            int[] pos = virusList.get(idx);
            q.add(new int[]{pos[0], pos[1]});
            dist[pos[0]][pos[1]] = 0;
        }

        int filledCount = 0;
        int maxTime = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[nx][ny] != 1 && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[now[0]][now[1]] + 1;

                    if (map[nx][ny] == 0) { // 빈칸일 때만 시간 갱신 및 카운트
                        filledCount++;
                        maxTime = dist[nx][ny];
                    }
                    q.add(new int[]{nx, ny});
                }
            }
        }

        // 모든 빈칸이 채워졌다면 최소 시간 갱신
        if (filledCount == emptySpaces) {
            minTime = Math.min(minTime, maxTime);
        }
    }
}