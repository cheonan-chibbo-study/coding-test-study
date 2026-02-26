package week5.GroupAnagrams;
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {


            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);


            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(s);
        }


        return new ArrayList<>(map.values());
    }
}