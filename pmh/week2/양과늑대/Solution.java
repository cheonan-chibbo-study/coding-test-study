package week2.양과늑대;

import java.util.*;
class Solution {
    /*
    2진 트리 각 노드에 늑대 라 양
    루트 노드에 출발해 양을 모음
    노드를 방문할때마다 양 과 늑대가 나를 따라옴
    이때 늑대는 야을 잡아먹으려함 양 > 늑대 이여야 안잡아먹음
    0은 양 1은 늑대


    */
    int maxSheep =0;

    public int solution(int[] info, int[][] edges) {

        List<Integer>[] tree = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) tree[i] = new ArrayList<>();

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]); // 부모 -> 자식 방향만 저장
        }
        backtrace(0,0,0,info,new ArrayList<>(),tree);
        return maxSheep;
    }
    public void backtrace(int index,int w,int s ,int[] info,List<Integer> nextNodes,List<Integer>[] tree){
        if(info[index] == 0)s++;
        else w++;

        if(w>=s) return;

        maxSheep = Math.max(maxSheep,s);

        List<Integer>  list = new ArrayList<>(nextNodes);
        list.remove(Integer.valueOf(index)); // 현재 노드 제외

        if(tree[index] !=null ){
            for(int child : tree[index]){
                list.add(child);//현재 노드의 자식들 추가
            }
        }

        //모든 후보지에대한 탐색 반복
        for(int next:list){
            backtrace(next,w,s,info,list,tree);
        }


    }


}
