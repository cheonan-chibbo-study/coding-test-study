package study2.week4.편안한워크숍;

import java.util.*;

public class Solution {

    static int n, k;
    static int[][] grid;
    static int[][] memo;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // (r,c)에서 시작할 때 만들 수 있는 최대 등산로 길이
    static int dfs(int r, int c, int limit) {

        if (memo[r][c] != -1) {
            return memo[r][c];
        }

        memo[r][c] = 1;

        for (int d = 0; d < 4; d++) {

            int nr = r + dx[d];
            int nc = c + dy[d];

            if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                continue;
            }

            int diff = grid[nr][nc] - grid[r][c];

            // 높이가 증가하고, 높이 차가 limit 이하인 경우만 이동
            if (diff > 0 && diff <= limit) {
                memo[r][c] = Math.max(
                        memo[r][c],
                        dfs(nr, nc, limit) + 1
                );
            }
        }

        return memo[r][c];
    }

    // limit(최대 허용 높이 차)로 길이 k 이상의 등산로가 존재하는지 확인
    static boolean check(int limit) {

        memo = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (dfs(i, j, limit) >= k) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        grid = new int[n][n];

        int maxHeight = 0;
        int minHeight = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                grid[i][j] = sc.nextInt();

                maxHeight = Math.max(maxHeight, grid[i][j]);
                minHeight = Math.min(minHeight, grid[i][j]);
            }
        }

        int left = 0;
        int right = maxHeight - minHeight;
        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (check(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}