package week6.MaximumSubarray;
import java.util.*;
class Solution {

    int[] memo;

    public int maxSubArray(int[] nums) {

        memo = new int[nums.length];
        Arrays.fill(memo, Integer.MIN_VALUE);

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, dfs(nums, i));
        }

        return max;
    }

    public int dfs(int[] nums, int i) {

        if (i == 0) return nums[0];

        if (memo[i] != Integer.MIN_VALUE) return memo[i];

        memo[i] = Math.max(nums[i], nums[i] + dfs(nums, i - 1));

        return memo[i];
    }
}