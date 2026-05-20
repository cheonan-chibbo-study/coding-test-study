import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], key -> new ArrayList<>())
                .add(new int[]{time[1], time[2]});
        }

        int[] weight = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            weight[i] = Integer.MAX_VALUE;
        }

        // 메인 로직
        int answer = Integer.MIN_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>((v1, v2) -> {
            return Integer.compare(v1[0], v2[0]);
        });

        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] >= weight[cur[1]]) {
                continue;
            } else {
                weight[cur[1]] = cur[0];
                answer = Math.max(answer, cur[0]);
            }

            if (!graph.containsKey(cur[1])) {
                continue;
            }

            for (int[] next : graph.get(cur[1])) {
                int nextWeight = cur[0] + next[1];
                pq.offer(new int[]{nextWeight, next[0]});
            }
        }

        for (int i = 1; i <= n; i++) {
            if (weight[i] == Integer.MAX_VALUE) {
                return -1;
            }
        }

        return answer;
    }
}