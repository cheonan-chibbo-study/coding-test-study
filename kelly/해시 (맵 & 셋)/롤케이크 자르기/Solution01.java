import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Set<Integer> aTopping = new HashSet<>();
        Map<Integer, Integer> bTopping = new HashMap<>();
        for (int t : topping) {
            bTopping.put(t, bTopping.getOrDefault(t, 0) + 1);
        }

        // 메인 로직
        int answer = 0;
        for (int t : topping) {
            aTopping.add(t);
            bTopping.put(t, bTopping.get(t) - 1);

            if (bTopping.get(t) == 0) {
                bTopping.remove(t);
            }

            if (aTopping.size() == bTopping.size()) {
                answer += 1;
            }
        }

        return answer;
    }
}