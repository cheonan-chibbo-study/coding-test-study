package study1.week5.LongestConsecutiveSequence;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution2 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }
        int max =0;
        for (int num : set) {
            if(!set.contains(num-1)){
                int cur = 1;
                while(set.contains(num+1)){
                    cur++;
                    num++;
                    max = Math.max(cur, max);
                }
            }
        }
        return max;
    }
}