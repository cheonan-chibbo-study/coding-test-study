package study2.week4.금과은을운반하기;

import java.util.*;
class Solution {
    /*
    도시를 짓기위해 -> 금 a 은 b 가 필요
    각 도시에 번호가 매겨짐
    i 번 도시에 금 g[i] 은 s[i] 그리고 트럭한대가 있음
    i 번 도시 트럭은 i 번 <-> 새도시 사이만 왕복가능
    편도 이도이 t[i] 시간이 걸림 최대 w[i] 광물 운반가능
    새로운 도시를 건설 위해 금은 전달할수있는 가장 빠른시간


    */

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        //T 시간 동안 왕복가능한수  T / t[i] -> 운반 가능 횟수
        //운반 가능횟수 x w[i] 옮길수있는 최대 양 , g[i] + s[i] 중 최솟값이
        //T 시간 동안 도시가 옮길수있는 최대양

        long right = 1_000_000_000_000_000L;
        long left =0;

        long answer=0;
        while(left<= right){
            long mid =(right + left) /2 ;
            if(can(mid, a, b, g, s, w, t)){
                right = mid-1;
                answer =mid;
            }else{
                left = mid +1;

            }


        }


        return answer;
    }
    private boolean can(long time, int a, int b,
                        int[] g, int[] s, int[] w, int[] t){
        long gold=0;
        long silver =0;
        long total =0;
        for(int i=0;i<g.length;i++){
            //운반 횟수
            long cnt = time / (2L *t[i]);
            //마지막 편도 가능 여부
            if(time % (2L *t[i]) >=t[i])cnt++;

            //해당 도시에 실제로 운반가능한 총무게
            long move = Math.min(cnt * w[i],(long)g[i] + s[i]);
            gold += Math.min(move,(long)g[i]);
            silver += Math.min(move,(long)s[i]);
            total += move;


        }

        return gold >=a && silver >= b
                && total >= (long) a+b;

    }
}