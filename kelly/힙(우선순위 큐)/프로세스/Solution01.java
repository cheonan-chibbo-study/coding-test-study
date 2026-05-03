import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Deque<int[]> dq = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < priorities.length; i++) {
            dq.offer(new int[]{priorities[i], i});
            pq.offer(priorities[i]);
        }

        int answer = 1;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();

            if (cur[0] == pq.peek()) {
                if (cur[1] == location) {
                    return answer;
                }

                pq.poll();
                answer++;
            } else {
                dq.offer(cur);
            }
        }

        return -1;
    }
}