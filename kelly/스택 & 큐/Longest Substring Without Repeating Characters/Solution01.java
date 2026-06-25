import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (!dq.contains(cur)) {
                dq.offer(cur);
                answer = Math.max(answer, dq.size());
                continue;
            }

            while (!dq.isEmpty()) {
                char popped = dq.poll();
                if (popped == cur) {
                    break;
                }
            }

            dq.offer(cur);
        }

        return answer;
    }
}