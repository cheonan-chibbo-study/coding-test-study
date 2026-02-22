package week4.등산코스정하기;

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        List<int[]>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();

        for (int[] p : paths) {
            int a = p[0], b = p[1], w = p[2];
            g[a].add(new int[]{b, w});
            g[b].add(new int[]{a, w});
        }

        boolean[] isSummit = new boolean[n + 1];
        for (int s : summits) isSummit[s] = true;
        Arrays.sort(summits);

        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[1] - b[1]
        );

        for (int gate : gates) {
            dist[gate] = 0;
            pq.offer(new int[]{gate, 0});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int curInt = cur[1];

            if (curInt > dist[v]) continue;
            if (isSummit[v]) continue;

            for (int[] edge : g[v]) {
                int nxt = edge[0];
                int nxtInt = Math.max(curInt, edge[1]);

                if (nxtInt < dist[nxt]) {
                    dist[nxt] = nxtInt;
                    pq.offer(new int[]{nxt, nxtInt});
                }
            }
        }

        int bestSummit = -1;
        int bestIntensity = INF;

        for (int s : summits) {
            if (dist[s] < bestIntensity) {
                bestIntensity = dist[s];
                bestSummit = s;
            }
        }

        return new int[]{bestSummit, bestIntensity};
    }
}