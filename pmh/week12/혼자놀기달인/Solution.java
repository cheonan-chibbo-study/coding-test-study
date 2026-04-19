package week12.혼자놀기달인;

class Solution {
    public int solution(int[] cards) {
        boolean[] visited = new boolean[cards.length];
        int first = 0;
        int second = 0;

        for (int i = 0; i < cards.length; i++) {
            if (!visited[i]) {
                int count = 0;
                int cur = i;

                while (!visited[cur]) {
                    visited[cur] = true;
                    cur = cards[cur] - 1;
                    count++;
                }

                if (count > first) {
                    second = first;
                    first = count;
                } else if (count > second) {
                    second = count;
                }
            }
        }

        return first * second;
    }
}