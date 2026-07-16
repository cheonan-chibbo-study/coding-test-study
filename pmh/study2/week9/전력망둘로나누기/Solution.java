package study2.week9.전력망둘로나누기;
import java.util.*;

class Solution {
    boolean[] visited;
    List<Integer>[] g;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        // 노드 번호가 1부터 n까지이므로 n + 1 크기로 생성
        g = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        // 간선 연결
        for (int[] wire : wires) {
            int u = wire[0];
            int v = wire[1];

            g[u].add(v);
            g[v].add(u);
        }

        // 간선을 하나씩 끊어 보기
        for (int[] wire : wires) {
            int u = wire[0];
            int v = wire[1];

            // 간선 제거
            g[u].remove(Integer.valueOf(v));
            g[v].remove(Integer.valueOf(u));

            visited = new boolean[n + 1];

            // 한쪽 전력망의 송전탑 개수
            int count = dfs(1);
            int otherCount = n - count;

            answer = Math.min(
                    answer,
                    Math.abs(count - otherCount)
            );

            // 간선 복구
            g[u].add(v);
            g[v].add(u);
        }

        return answer;
    }

    private int dfs(int node) {
        visited[node] = true;

        int count = 1;

        for (int next : g[node]) {
            if (!visited[next]) {
                count += dfs(next);
            }
        }

        return count;
    }
}