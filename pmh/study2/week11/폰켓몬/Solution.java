package study2.week11.폰켓몬;

import java.util.*;
class Solution {
    public int solution(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);

        }

        int n = nums.length/2;
        if(map.size() > n){
            return n;
        }else{
            return map.size();
        }





    }
}