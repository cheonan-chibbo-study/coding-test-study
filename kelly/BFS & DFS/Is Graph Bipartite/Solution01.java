import java.util.*;

class Solution {

    int[][] graph;

    int E = 0;
    int R = -1;
    int B = 1;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;

        int[] mark = new int[graph.length];
        for (int start = 0; start < graph.length; start++) {
            if (mark[start] != E) {
                continue;
            }

            if (!bfs(mark, start)) {
                return false;
            }
        }

        return true;
    }

    private boolean bfs(int[] mark, int start) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(start);
        mark[start] = R;

        while (!dq.isEmpty()) {
            int cur = dq.poll();

            for (int next : graph[cur]) {
                if (mark[next] == E) {
                    mark[next] = -mark[cur];
                    dq.offer(next);
                    continue;
                } else if (mark[next] == mark[cur]) {
                    return false;
                }
            }
        }

        return true;
    }
}