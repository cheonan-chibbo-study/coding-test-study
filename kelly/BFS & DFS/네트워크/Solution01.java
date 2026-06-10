import java.util.*;

class Solution {

    Map<Integer, List<Integer>> graph;
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        graph = new HashMap<>();
        for (int node = 0; node < n; node++) {
            for (int conn = 0; conn < computers[node].length; conn++) {
                if (computers[node][conn] == 1) {
                    graph.computeIfAbsent(node, key -> new ArrayList<>()).add(conn);
                }
            }
        }

        // 메인 로직
        int answer = 0;
        visited = new boolean[n];

        for (int node = 0; node < n; node++) {
            if (visited[node]) {
                continue;
            }

            bfs(node);
            answer++;
        }

        return answer;
    }

    private void bfs(int start) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(start);
        visited[start] = true;

        while (!dq.isEmpty()) {
            int cur = dq.poll();

            for (int next : graph.computeIfAbsent(cur, key -> new ArrayList<>())) {
                if (visited[next]) {
                    continue;
                }

                dq.offer(next);
                visited[next] = true;
            }
        }
    }
}