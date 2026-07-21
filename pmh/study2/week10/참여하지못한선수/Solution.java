package study2.week10.참여하지못한선수;

import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String , Integer> map = new HashMap<>();
        for(String c : participant){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(String c : completion){
            map.put(c, map.get(c)-1);
        }
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getValue() !=0){
                return entry.getKey();
            }
        }

        return " ";
    }
}