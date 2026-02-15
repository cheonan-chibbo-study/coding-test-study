package week3.스타트택시;

import java.util.*;
import java.io.*;

public class Solution {
    static int N, M, fuel;
    static int[][] map;
    static int taxiX, taxiY;
    static List<Pger> pg = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // static 추가
    public static class Pger {
        int id, startX, startY, endX, endY;
        public Pger(int id, int startX, int startY, int endX, int endY) {
            this.id = id;
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    // static 추가
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxiX = Integer.parseInt(st.nextToken());
        taxiY = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sX = Integer.parseInt(st.nextToken());
            int sY = Integer.parseInt(st.nextToken());
            int eX = Integer.parseInt(st.nextToken());
            int eY = Integer.parseInt(st.nextToken());
            pg.add(new Pger(i, sX, sY, eX, eY));
            map[sX][sY] = i + 2;
        }

        for (int i = 0; i < M; i++) {
            int[] target = taxiToPagerDistance();

            if (target == null) {
                fuel = -1;
                break;
            }

            int startX = target[0];
            int startY = target[1];
            int distToStart = target[2];
            int pId = target[3];

            if (fuel < distToStart) {
                fuel = -1;
                break;
            }
            fuel -= distToStart;

            Pger p = pg.get(pId);
            int distToDest = goToDest(p.startX, p.startY, p.endX, p.endY);

            if (distToDest == -1 || fuel < distToDest) {
                fuel = -1;
                break;
            }

            fuel -= distToDest;
            fuel += (distToDest * 2);

            map[startX][startY] = 0; // 승객 제거
            taxiX = p.endX;
            taxiY = p.endY;
        }
        System.out.println(fuel);
    }

    // static 추가 및 인덱스 범위 수정
    public static int[] taxiToPagerDistance() {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return a[2] - b[2];
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        boolean[][] visited = new boolean[N + 1][N + 1];
        q.add(new int[]{taxiX, taxiY, 0});
        visited[taxiX][taxiY] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int dis = now[2];

            if (map[x][y] >= 2) {
                return new int[]{x, y, dis, map[x][y] - 2};
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) { // 범위 수정
                    if (!visited[nx][ny] && map[nx][ny] != 1) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny, dis + 1});
                    }
                }
            }
        }
        return null;
    }

    // static 추가 및 인덱스/변수 오타 수정
    public static int goToDest(int startX, int startY, int endX, int endY) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];

        q.add(new int[]{startX, startY, 0});
        visited[startX][startY] = true; // 오타 수정

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int dis = now[2];

            if (x == endX && y == endY) return dis;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) { // 범위 수정
                    if (!visited[nx][ny] && map[nx][ny] != 1) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny, dis + 1});
                    }
                }
            }
        }
        return -1;
    }
}