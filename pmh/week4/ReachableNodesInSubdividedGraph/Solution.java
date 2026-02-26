package week4.ReachableNodesInSubdividedGraph;
import java.util.*;
class Solution {
    static class Edge{
        int to,w;
        Edge(int to,int w){
            this.to =to;
            this.w= w;
        }
    }
    static class State{
        int node;
        int dist;
        State(int node, int dist){
            this.node =node;
            this.dist= dist;
        }


    }
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<Edge>[] g = new ArrayList[n];
        for(int i =0 ;i<n;i++)g[i] = new ArrayList<>();

        for(int[] e: edges){
            int u=e[0] , v= e[1], cnt=e[2];
            int w= cnt+1;
            g[u].add(new Edge(v,w));
            g[v].add(new Edge(u,w));
        }
        int[] dist = new int[n];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist,INF);

        dist[0] =0;
        PriorityQueue<State> pq = new PriorityQueue<>(
                (a,b) -> Integer.compare(a.dist,b.dist)
        );

        pq.offer(new State(0,0));

        while(!pq.isEmpty()){
            State cur = pq.poll();
            int u = cur.node;
            int d = cur.dist;

            if(d != dist[u])continue;
            if( d > maxMoves)continue;

            for(Edge next : g[u]){
                int v = next.to;
                int nextd = next.w + d;

                if(nextd < dist[v]){
                    dist[v] = nextd;
                    pq.offer(new State(v,nextd));
                }
            }



        }

        int ans=0;

        for (int i = 0; i < n; i++) {
            if (dist[i] <= maxMoves) ans++;
        }

        for(int[] e :edges)    {
            int u = e[0], v = e[1], cnt = e[2];
            int a = dist[u] > maxMoves ? 0 : (maxMoves - dist[u]);
            int b = dist[v] > maxMoves ? 0 : (maxMoves - dist[v]);

            int used = Math.min(cnt, a + b);
            ans += used;
        }
        return ans;
    }
}