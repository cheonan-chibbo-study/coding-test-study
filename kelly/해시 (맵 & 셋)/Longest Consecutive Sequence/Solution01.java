import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int answer = 0;
        for (int n : set) {
            if (!set.contains(n - 1)) {
                int cnt = 1;
                int target = n + 1;

                while (set.contains(target)) {
                    cnt += 1;
                    target += 1;
                }

                answer = Math.max(answer, cnt);
            }
        }

        return answer;
    }
}