import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();

        int[] answer = new int[temperatures.length];
        for (int curr_idx = 0; curr_idx < temperatures.length; curr_idx++) {
            int curr_temp = temperatures[curr_idx];

            while (!stack.isEmpty() && curr_temp > temperatures[stack.peek()]) {
                int prev_idx = stack.pop();
                int dist = curr_idx - prev_idx;
                answer[prev_idx] = dist;
            }

            stack.push(curr_idx);
        }

        return answer;
    }
}
