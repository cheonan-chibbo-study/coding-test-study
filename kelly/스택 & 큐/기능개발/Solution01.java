import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            dq.offer(i);
        }

        List<Integer> answer = new ArrayList<>();
        while (!dq.isEmpty()) {
            int popped = dq.poll();
            int count = 1;
            int needDay = 0;
            if (progresses[popped] < 100) {
                needDay = (int) Math.ceil((100.0 - progresses[popped]) / speeds[popped]);
            }

            for (int i = popped + 1; i < progresses.length; i++) {
                progresses[i] += (needDay * speeds[i]);
            }

            while (!dq.isEmpty() && progresses[dq.peek()] >= 100) {
                dq.poll();
                count++;
            }

            answer.add(count);
        }

        int[] arrAnswer = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            arrAnswer[i] = answer.get(i);
        }

        return arrAnswer;
    }
}