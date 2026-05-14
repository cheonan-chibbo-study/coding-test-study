package study1.week6.HouseRobber;
class Solution {
    int[] memo;

    public int rob(int[] nums) {
        int n = nums.length;
        memo = new int[n];
        java.util.Arrays.fill(memo, -1);
        return dfs(n - 1, nums);
    }

    public int dfs(int i, int[] nums) {
        if (i < 0) return 0;
        if (memo[i] != -1) return memo[i];

        int rob = nums[i] + dfs(i - 2, nums);
        int skip = dfs(i - 1, nums);

        return memo[i] = Math.max(rob, skip);
    }
}