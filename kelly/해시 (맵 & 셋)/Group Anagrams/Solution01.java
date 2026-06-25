import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> graph = new HashMap<>();

        for (String v : strs) {
            graph.computeIfAbsent(strSort(v), key -> new ArrayList<>()).add(v);
        }

        List<List<String>> answer = new ArrayList<>();
        for (List<String> v : graph.values()) {
            answer.add(v);
        }

        return answer;
    }

    private String strSort(String v) {
        char[] cArr = v.toCharArray();
        Arrays.sort(cArr);
        return new String(cArr);
    }
}