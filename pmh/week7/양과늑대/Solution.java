package week7.양과늑대;
import java.util.*;
class Solution {
    /*
    2진 트리 각 노드에 늑대 라 양
    루트 노드에 출발해 양을 모음
    노드를 방문할때마다 양 과 늑대가 나를 따라옴
    이때 늑대는 야을 잡아먹으려함 양 > 늑대 이여야 안잡아먹음
    0은 양 1은 늑대


    */

    List<Integer>[] g ;
    int answer =0;
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        g = new ArrayList[n];
        for(int i =0; i<n;i++){
            g[i] = new ArrayList<>();
        }

        for(int[] edge : edges){
            g[edge[0]].add(edge[1]);
        }

        List<Integer> next = new ArrayList<>();
        //하상 시작은 루트
        next.add(0);

        dfs(0,0,info,next);

        return answer;

    }
    public void dfs(int sheep,int wolf,int[] info,List<Integer> nextNode){
        for(int node : nextNode){

            int ns = sheep;
            int nw = wolf;

            if(info[node] == 0)ns++;
            else nw++;

            if(ns <= nw)continue;

            answer = Math.max(ns,answer);

            List<Integer> next = new ArrayList<>(nextNode);

            next.remove(Integer.valueOf(node)); //현재 노드 제거
            next.addAll(g[node]); // 다음 노드 추가

            dfs(ns,nw,info, next);




        }




    }


}
