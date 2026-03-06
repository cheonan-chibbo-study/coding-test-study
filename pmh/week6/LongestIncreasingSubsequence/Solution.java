package week6.LongestIncreasingSubsequence;
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int answer = 1;

        for (int i = 0; i < n; i++) {
            dp[i] = 1; // 자기 자신만 포함해도 길이 1

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}