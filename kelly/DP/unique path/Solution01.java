class Solution {

    int m;
    int n;
    int[][] memo;

    public int uniquePaths(int m, int n) {
        this.m = m;
        this.n = n;
        this.memo = new int[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                memo[r][c] = -1;
            }
        }

        memo[0][0] = 0;

        for (int r = 0; r < m; r++) {
            memo[r][0] = 1;
        }

        for (int c = 0; c < n; c++) {
            memo[0][c] = 1;
        }

        // 메인 로직
        return dp(m - 1, n - 1);
    }

    private int dp(int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return 0;
        }

        if (memo[r][c] == -1) {
            memo[r][c] = dp(r - 1, c) + dp(r, c - 1);
        }

        return memo[r][c];
    }
}