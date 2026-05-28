import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> history = new HashMap<>();
        for (String r : report) {
            String[] rSplit = r.split(" ");
            history.computeIfAbsent(rSplit[1], key -> new HashSet<>()).add(rSplit[0]);
        }

        Map<String, Integer> callCount = new HashMap<>();
        for (String id : id_list) {
            Set<String> reqs = history.computeIfAbsent(id, key -> new HashSet<>());

            if (reqs.size() < k) {
                continue;
            }

            for (String req : reqs) {
                callCount.put(req, callCount.getOrDefault(req, 0) + 1);
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = callCount.getOrDefault(id_list[i], 0);
        }

        return answer;
    }
}