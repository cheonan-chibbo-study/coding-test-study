import java.util.*;

class Solution {
    public String minWindow(String s, String t) {
        int tCount = t.length();
        Map<Character, Integer> target = new HashMap<>();
        for (char ch : t.toCharArray()) {
            target.put(ch, target.getOrDefault(ch, 0) + 1);
        }

        int l = 0;
        int r = 0;
        int minLen = Integer.MAX_VALUE;
        String answer = "";

        while (r < s.length()) {
            char curCh = s.charAt(r);

            if (target.containsKey(curCh)) {
                target.put(curCh, target.get(curCh) - 1);

                if (target.get(curCh) >= 0) {
                    tCount -= 1;
                }
            }

            while (tCount == 0) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    answer = s.substring(l, r + 1);
                }

                char lCh = s.charAt(l);
                if (target.containsKey(lCh)) {
                    target.put(lCh, target.get(lCh) + 1);

                    if (target.get(lCh) > 0) {
                        tCount += 1;
                    }
                }

                l += 1;
            }

            r += 1;
        }

        return answer;
    }
}