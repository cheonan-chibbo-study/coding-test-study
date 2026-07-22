package study2.week10.폰켓몬;

import java.util.*;
class Solution {
    public int solution(int[] nums) {
        // n/2 만큼 마리 포켓몬 선택하는 방법중가장 많은 종류 선택하는방법
        // 최대 n/2 만큼 선택하는게 가장 많이 선택하는방법
        //
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        int num = nums.length /2;
        //map 사이즈가 num 보다 크다면 nums/2 보다 종류가 많다는거니 nums/2 전부 고르기
        // 작다면 nums/2 보다 종류가 작다는거니(중복 많음) 종류 모두 고르기
        if(map.size() >num){
            return num;
        }else{
            return map.size();
        }

    }
}