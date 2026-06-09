package study2.week4.전력망둘로나누기;

import java.util.*;
class Solution {
    boolean[] visited;
    List<Integer>[] g;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        g = new ArrayList[n+1];

        for(int i=1;i<=n;i++){
            g[i] = new ArrayList<>();
        }
        for(int[] node : wires){
            int u = node[0];
            int v = node[1];

            g[u].add(v);
            g[v].add(u);
        }

        for(int i=0;i<wires.length;i++){
            int u = wires[i][0];
            int v = wires[i][1];
            //자르기 그냥 제거시 인덱스를 제거할 인티저 객체로 변경후 삭제
            g[u].remove(Integer.valueOf(v));
            g[v].remove(Integer.valueOf(u));

            visited = new boolean[n+1];
            int count = dfs(1);
            int other = n-count;
            answer = Math.min(answer,Math.abs(count-other));
            // 다시 연결
            g[u].add(v);
            g[v].add(u);

        }



        return answer;
    }
    public int dfs(int node){
        visited[node]= true;
        int count =1;

        for(int next : g[node]){
            if(!visited[next]){
                count += dfs(next);
            }
        }
        return count;


    }
}