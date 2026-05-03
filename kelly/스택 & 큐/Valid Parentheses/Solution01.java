import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Deque<String> dq = new ArrayDeque<>();
        Map<String, String> item = Map.of(
            "(", ")",
            "{", "}",
            "[", "]"
        );

        for (int i = 0; i < s.length(); i++) {
            String cur = String.valueOf(s.charAt(i));

            if (item.containsKey(cur)) {
                dq.push(item.get(cur));
            } else {
                if (dq.isEmpty() || !dq.peek().equals(cur)) {
                    return false;
                }

                dq.pop();
            }
        }

        return dq.isEmpty();
    }
}