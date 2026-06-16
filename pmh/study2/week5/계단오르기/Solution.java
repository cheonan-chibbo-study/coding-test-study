package study2.week5.계단오르기;

import java.util.Arrays;

class Solution {
    int[] memo;

    public int climbStairs(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return dfs(n);
    }

    private int dfs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        memo[n] = dfs(n - 1) + dfs(n - 2);
        return memo[n];
    }
}