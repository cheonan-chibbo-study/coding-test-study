package week12.전력망을둘로나누기;

import java.util.*;

class Solution {
    boolean[] visited;
    List<Integer>[] graph;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        for (int cut = 0; cut < wires.length; cut++) {
            // 그래프 생성
            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            // cut 번째 전선만 제외하고 연결
            for (int i = 0; i < wires.length; i++) {
                if (i == cut) continue;

                int a = wires[i][0];
                int b = wires[i][1];

                graph[a].add(b);
                graph[b].add(a);
            }

            visited = new boolean[n + 1];

            // 아무 노드 하나에서 시작해서 연결된 개수 세기
            int count = dfs(1);

            int other = n - count;
            answer = Math.min(answer, Math.abs(count - other));
        }

        return answer;
    }

    public int dfs(int node) {
        visited[node] = true;
        int count = 1; // 자기 자신 포함

        for (int next : graph[node]) {
            if (!visited[next]) {
                count += dfs(next);
            }
        }

        return count;
    }
}