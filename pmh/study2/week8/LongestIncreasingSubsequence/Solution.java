package study2.week8.LongestIncreasingSubsequence;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        //모든 원소 하나만 선택한경우
        Arrays.fill(dp,1);

        int answer =1;
        for(int i=0;i<n;i++){

            for(int j=0;j<i;j++){

                if(nums[i]  > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }

            }
            answer = Math.max(answer, dp[i]);
        }
        return answer;

    }
}