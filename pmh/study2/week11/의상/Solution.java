package study2.week11.의상;

import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        HashMap<String,Integer> map = new HashMap<>();

        for(int i =0 ; i<clothes.length;i++){
            map.put(clothes[i][1],map.getOrDefault(clothes[i][1],0)+1);
        }


        int answer = 1;
        Set<String> keySet = map.keySet();
        for(String key : keySet){

            answer *= (map.get(key) +1);

        }




        return answer-1;
    }



}