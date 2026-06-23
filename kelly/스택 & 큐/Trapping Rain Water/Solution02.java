import java.util.*;

class Solution {
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] water = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty() || height[stack.peek()] >= height[i]) {
                stack.push(i);
                continue;
            }

            int lastPopped = -1;
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                lastPopped = stack.pop();
            }

            int lowWall = (stack.isEmpty()) ? lastPopped : i;
            int start = (stack.isEmpty()) ? lastPopped + 1 : stack.peek() + 1;

            for (int j = start; j < i; j++) {
                water[j] = height[lowWall] - height[j];
            }

            stack.push(i);
        }

        int answer = 0;
        for (int w : water) {
            answer += w;
        }

        return answer;
    }
}