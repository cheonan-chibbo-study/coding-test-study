import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] fare : fares) {
            graph.computeIfAbsent(fare[0], key -> new ArrayList<>()).add(new int[]{fare[1], fare[2]});
            graph.computeIfAbsent(fare[1], key -> new ArrayList<>()).add(new int[]{fare[0], fare[2]});
        }

        int[][] visited = new int[3][n + 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < n + 1; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        // 메인 로직
        int[] startVertex = {s, a, b};
        for (int i = 0; i < 3; i++) {
            PriorityQueue<Node> pq = new PriorityQueue<>((v1, v2) -> {
                return Integer.compare(v1.totalCost, v2.totalCost);
            });
            pq.offer(new Node(startVertex[i], 0));
            visited[i][startVertex[i]] = 0;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (cur.totalCost > visited[i][cur.vertex]) {
                    continue;
                }

                for (int[] next : graph.computeIfAbsent(cur.vertex, key -> new ArrayList<>())) {
                    int nextTotalCost = cur.totalCost + next[1];

                    if (nextTotalCost < visited[i][next[0]]) {
                        pq.offer(new Node(next[0], nextTotalCost));
                        visited[i][next[0]] = nextTotalCost;
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            answer = Math.min(answer, visited[0][i] + visited[1][i] + visited[2][i]);
        }

        return answer;
    }

    class Node {
        int vertex;
        int totalCost;

        public Node(int vertex, int totalCost) {
            this.vertex = vertex;
            this.totalCost = totalCost;
        }
    }
}