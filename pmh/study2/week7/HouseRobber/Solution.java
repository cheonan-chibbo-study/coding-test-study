package study2.week7.HouseRobber;

import java.util.Arrays;

class Solution {
    int[] memo;

    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dfs(nums.length - 1, nums);
    }

    public int dfs(int idx, int[] nums) {
        if (idx < 0) return 0;
        if (memo[idx] != -1) return memo[idx];

        memo[idx] = Math.max(
                dfs(idx - 1, nums),
                dfs(idx - 2, nums) + nums[idx]
        );

        return memo[idx];
    }
}