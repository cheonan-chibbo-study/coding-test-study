package study2.week7.MaximumSubarray;

class Solution {
    int maxSum = Integer.MIN_VALUE;
    public int maxSubArray(int[] nums) {
        dfs(nums.length-1,nums);
        return maxSum;

    }
    public int dfs(int idx , int[] nums){

        if(idx == 0){
            maxSum = Math.max(maxSum, nums[0]);
            return nums[0];
        }

        int prev = dfs(idx-1,nums);

        int cur = Math.max(prev+nums[idx], nums[idx]);

        maxSum = Math.max(maxSum,cur);

        return cur;


    }
}