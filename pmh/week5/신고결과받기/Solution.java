package week5.신고결과받기;
import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        int[] answer = new int[n];

        // id -> index
        Map<String, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idx.put(id_list[i], i);
        }

        // 신고받은사람-> 신고한사람 set (중복 제거)
        Map<String, Set<String>> reportedToReporters = new HashMap<>();

        for (String r : report) {
            String[] parts = r.split(" ");
            String reporter = parts[0];
            String reported = parts[1];

            reportedToReporters
                    .computeIfAbsent(reported, key -> new HashSet<>())
                    .add(reporter);
        }

        //  정지 대상(k 이상) 찾아서 신고자들에게 메일 카운트
        for (String reported : reportedToReporters.keySet()) {

            Set<String> reporters = reportedToReporters.get(reported);

            if (reporters.size() >= k) {
                for (String reporter : reporters) {
                    answer[idx.get(reporter)]++;
                }
            }
        }

        return answer;
    }
}