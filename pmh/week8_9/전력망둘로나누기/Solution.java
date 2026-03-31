package week8_9.전력망둘로나누기;
import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        // 전선 하나씩 끊어본다
        for (int cut = 0; cut < wires.length; cut++) {

            // 그래프 새로 만들기
            List<Integer>[] g = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                g[i] = new ArrayList<>();
            }

            // cut 번째 전선만 제외하고 연결
            for (int i = 0; i < wires.length; i++) {
                if (i == cut) continue;

                int u = wires[i][0];
                int v = wires[i][1];

                g[u].add(v);
                g[v].add(u);
            }

            // 한쪽 전력망의 노드 수 세기
            int count = bfs(1, g, n);

            // 다른 쪽은 전체에서 빼면 됨
            int other = n - count;

            answer = Math.min(answer, Math.abs(count - other));
        }

        return answer;
    }

    private int bfs(int start, List<Integer>[] g, int n) {
        boolean[] visited = new boolean[n + 1];
        Deque<Integer> q = new ArrayDeque<>();

        q.offer(start);
        visited[start] = true;

        int count = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : g[cur]) {
                if (visited[next]) continue;
                visited[next] = true;
                q.offer(next);
                count++;
            }
        }

        return count;
    }
}