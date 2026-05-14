package study1.week5.SubstringwithConcatenationofAllWords;
import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return ans;

        int wordLen = words[0].length();
        int wordsCount = words.length;
        int totalLen = wordLen * wordsCount;
        if (s.length() < totalLen) return ans;

        // 1) need map
        Map<String, Integer> need = new HashMap<>();
        for (String w : words) {
            need.put(w, need.getOrDefault(w, 0) + 1);
        }

        // 2) offset 별로 슬라이딩 (0 ~ wordLen-1)
        for (int offset = 0; offset < wordLen; offset++) {
            Map<String, Integer> window = new HashMap<>();
            int left = offset;
            int right = offset;
            int used = 0; // 현재 윈도우에 들어온 "단어 개수" (word 단위)

            while (right + wordLen <= s.length()) {
                String w = s.substring(right, right + wordLen);
                right += wordLen;

                // 단어가 need에 없다면: 윈도우 초기화
                if (!need.containsKey(w)) {
                    window.clear();
                    used = 0;
                    left = right;
                    continue;
                }

                // 단어 추가
                window.put(w, window.getOrDefault(w, 0) + 1);
                used++;

                // 개수가 초과되면 left를 줄여서 정상 범위로
                while (window.get(w) > need.get(w)) {
                    String lw = s.substring(left, left + wordLen);
                    window.put(lw, window.get(lw) - 1);
                    used--;
                    left += wordLen;
                }

                // 정확히 wordsCount개면 정답
                if (used == wordsCount) {
                    ans.add(left);

                    // 다음 후보를 위해 left 한 칸 줄이기
                    String lw = s.substring(left, left + wordLen);
                    window.put(lw, window.get(lw) - 1);
                    used--;
                    left += wordLen;
                }
            }
        }

        return ans;
    }
}