import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> dq1 = new ArrayDeque<>();
        Deque<Integer> dq2 = new ArrayDeque<>();

        long qSize = queue1.length;
        long q1Sum = 0;
        long q2Sum = 0;

        for (int n : queue1) {
            q1Sum += n;
            dq1.offer(n);
        }

        for (int n : queue2) {
            q2Sum += n;
            dq2.offer(n);
        }

        // 메인 로직
        int answer = 0;

        for (int i = 0; i < qSize * 4; i++) {
            if (q1Sum == q2Sum) {
                return answer;
            }

            if (q1Sum > q2Sum) {
                int popped = dq1.poll();
                dq2.offer(popped);
                q1Sum -= popped;
                q2Sum += popped;
            } else {
                int popped = dq2.poll();
                dq1.offer(popped);
                q1Sum += popped;
                q2Sum -= popped;
            }

            answer += 1;
        }

        return -1;
    }
}