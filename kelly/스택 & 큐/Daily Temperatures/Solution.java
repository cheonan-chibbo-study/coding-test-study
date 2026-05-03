import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Deque<int[]> dq = new ArrayDeque<>();

        for (int day = 0; day < temperatures.length; day++) {
            if (dq.isEmpty()) {
                dq.push(new int[]{day, temperatures[day]});
                continue;
            }

            while (!dq.isEmpty() && dq.peek()[1] < temperatures[day]) {
                int[] popped = dq.pop();
                answer[popped[0]] = day - popped[0];
            }

            dq.push(new int[]{day, temperatures[day]});
        }

        return answer;
    }
}