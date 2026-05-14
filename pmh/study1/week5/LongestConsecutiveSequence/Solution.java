package study1.week5.LongestConsecutiveSequence;

import java.util.Arrays;

class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length ==0)return 0;
        Arrays.sort(nums);
        int max=1;
        int cur=1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i] == nums[i-1]){
                continue;
            }else if(nums[i] == nums[i-1]+1){
                cur++;
                if(cur >max)max =cur;
            }else{
                //끊길시
                cur=1;
            }


        }
        return max;

    }
}