class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int qSize = queue1.length;
        int[] dq = new int[qSize * 2];
        long q1Sum = 0;
        long q2Sum = 0;

        for (int i = 0; i < qSize; i++) {
            dq[i] = queue1[i];
            q1Sum += queue1[i];
        }

        for (int i = 0; i < qSize; i++) {
            dq[qSize + i] = queue2[i];
            q2Sum += queue2[i];
        }

        int answer = 0;
        int left = 0;
        int right = qSize;

        for (int i = 0; i < qSize * 4; i++) {
            if (q1Sum == q2Sum) {
                return answer;
            }

            if (q1Sum > q2Sum) {
                q1Sum -= dq[left];
                q2Sum += dq[left];
                left = (left + 1) % (dq.length);
            } else {
                q1Sum += dq[right];
                q2Sum -= dq[right];
                right = (right + 1) % (dq.length);
            }

            answer += 1;
        }

        return -1;
    }
}