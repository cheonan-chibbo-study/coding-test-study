package week5.MinimumWindowSubstring;
import java.util.*;
class Solution {
    public String minWindow(String s, String t) {
        if (t.length() == 0) return "";
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // 2) 현재 윈도우에서 가진 문자 개수 (t에 있는 문자만 세면 됨)
        Map<Character, Integer> window = new HashMap<>();

        int tSize = need.size();
        int formed =0;

        int left =0;
        int bestLen = Integer.MAX_VALUE;
        int bestLeft = 0;

        for(int right =0 ;right < s.length() ;right++){
            char c = s.charAt(right);

            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0) +1);

                if(window.get(c).equals(need.get(c))){
                    formed++;
                }


            }

            while(formed == tSize){
                int len = right- left +1;
                if(len <bestLen){
                    bestLen = len;
                    bestLeft = left;
                }

                char lc = s.charAt(left);

                if(need.containsKey(lc)){
                    window.put(lc,window.get(lc)-1);

                    if(window.get(lc) < need.get(lc)){
                        formed--;
                    }
                }

                left++;

            }

        }
        if(bestLen == Integer.MAX_VALUE){
            return "";
        }
        return s.substring(bestLeft,bestLeft+bestLen);
    }
}