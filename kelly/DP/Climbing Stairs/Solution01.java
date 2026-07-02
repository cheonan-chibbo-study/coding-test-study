class Solution {

    int[] memo;

    public int climbStairs(int n) {
        this.memo = new int[n + 1];

        // 메인 로직
        return dp(n);
    }

    private int dp(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (memo[n] == 0) {
            memo[n] = dp(n - 1) + dp(n - 2);
        }

        return memo[n];
    }
}