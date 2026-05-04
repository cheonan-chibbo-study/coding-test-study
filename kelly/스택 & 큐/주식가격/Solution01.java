import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Deque<int[]> dq = new ArrayDeque<>();
        int[] answer = new int[prices.length];

        for (int day = 0; day < prices.length; day++) {
            if (!dq.isEmpty()) {
                while (!dq.isEmpty() && dq.peek()[0] > prices[day]) {
                    int[] popped = dq.pop();
                    answer[popped[1]] = day - popped[1];
                }
            }

            dq.push(new int[]{prices[day], day});
        }

        while (!dq.isEmpty()) {
            int[] popped = dq.pop();
            answer[popped[1]] = (prices.length - 1) - popped[1];
        }

        return answer;
    }
}