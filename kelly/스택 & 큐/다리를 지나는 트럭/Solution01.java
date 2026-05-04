import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Deque<Integer> ready = new ArrayDeque<>();
        for (int t : truck_weights) {
            ready.offer(t);
        }

        Deque<Integer> bridge = new ArrayDeque<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        int totalW = 0;
        int time = 0;

        while (!ready.isEmpty()) {
            time += 1;
            totalW -= bridge.poll();

            if ((weight - totalW) >= ready.peek()) {
                int cur = ready.poll();
                totalW += cur;
                bridge.offer(cur);
            } else {
                bridge.offer(0);
            }
        }

        return time + bridge_length;
    }
}