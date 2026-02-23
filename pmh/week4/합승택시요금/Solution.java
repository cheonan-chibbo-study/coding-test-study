package week4.합승택시요금;

import java.util.*;
class Solution {
    public static int[] dig(int n ,List<int[]>[] g, int start){

        int[] fee = new int[n+1];
        Arrays.fill(fee,Integer.MAX_VALUE);
        fee[start] =0;
        PriorityQueue<int []> pq = new PriorityQueue<>(
                (one,two) -> one[1] - two[1]);

        pq.offer(new int[]{start,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curN = cur[0];
            int curW = cur[1];

            if(curW > fee[curN])continue;

            for(int[] node : g[curN]){
                int nxtNode = node[0];
                int w = node[1];
                if(fee[curN] + w < fee[nxtNode]){
                    fee[nxtNode] = fee[curN] + w;
                    pq.offer(new int[]{nxtNode,fee[nxtNode]});
                }
            }

        }
        return fee;
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        //그래프 구성
        List<int[]>[] g = new ArrayList[n+1];
        for(int i=1;i<=n;i++)g[i] = new ArrayList<>();
        for(int[] p : fares){
            int n1 = p[0], n2 = p[1], w = p[2];
            g[n1].add(new int[]{n2,w});
            g[n2].add(new int[]{n1,w});
        }


        int[] feeS = dig(n,g,s);
        int[] feeA =  dig(n,g,a);
        int[] feeB =  dig(n,g,b);


        int minAnswer=Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
            if(feeS[i] == Integer.MAX_VALUE || feeA[i] == Integer.MAX_VALUE ||feeB[i] == Integer.MAX_VALUE ){
                continue;
            }
            int sum =feeS[i]+ feeA[i] + feeB[i];
            minAnswer =Math.min (minAnswer,sum);
        }

        return  minAnswer;
    }
}