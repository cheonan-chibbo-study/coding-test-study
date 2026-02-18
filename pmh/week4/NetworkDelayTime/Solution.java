package week4.NetworkDelayTime;

import java.util.*;
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        Map<Integer, List<int[]>> graph  = new HashMap<>();

        for(int[] time : times){
            graph.computeIfAbsent(time[0], x-> new ArrayList<>()).add(new int[]{time[1],time[2]});
        }

        // 거래 배열 무한대로 초기화
        int [] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[k] =0;

        //다익스트라 우선순위큐 비용기준 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k,0});

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int u = now[0];
            int d = now[1];

            if(d > dist[u])continue;

            //연결된 이웃 노드 탐색
            if(graph.containsKey(u)){
                for(int[] edge : graph.get(u)){
                    int v = edge[0];
                    int weight = edge[1];
                    //더 짧은 경로를 찾은 경우 업데이트
                    if(dist[u] + weight <dist[v]){
                        dist[v] = dist[u] + weight;
                        pq.offer(new int[]{v,dist[v]});
                    }
                }
            }
        }
        //결과
        int maxDelay=0;
        for(int i=1;i<=n;i++){
            if(dist[i] == Integer.MAX_VALUE) return -1;
            maxDelay = Math.max(maxDelay,dist[i]);
        }
        return maxDelay;
    }
}