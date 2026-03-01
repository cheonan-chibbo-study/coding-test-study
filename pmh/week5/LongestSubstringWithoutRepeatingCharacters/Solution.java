package week5.LongestSubstringWithoutRepeatingCharacters;

import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 중복이면, 중복이 사라질 때까지 left를 이동
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }

            // 이제 중복 없으니 추가
            set.add(c);

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}