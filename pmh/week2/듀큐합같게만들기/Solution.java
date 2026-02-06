package week2.듀큐합같게만들기;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.*;
class Solution {
    /*
    하나의 배열로 만들어서
    투포인트 사용해서 수가 큐 합의 절반 보다 크면 strat+1 작으면 end+1 하는식으로 푼다

    */
    public int solution(int[] queue1, int[] queue2) {
        long totalSum =0;
        long currentSum =0;

        int n = queue1.length;
        int[] combined = new int [n*2];

        for(int i=0;i<n;i++){
            combined[i] =queue1[i];
            combined[i+n] = queue2[i];
            currentSum += queue1[i];
            totalSum += (long)queue1[i] + queue2[i];
        }

        if (totalSum % 2 != 0) return -1;
        long half = totalSum / 2;

        int start =0;
        int end =n-1; //queue1의 끝지점
        int count=0;

        int maxCount = n*4;
        while(count<=maxCount){
            if (currentSum == half) return count;

            if (currentSum < half) {
                // 합이 작으면 end를 뒤로 밀어서 더함
                end++;
                if (end == n * 2) end = 0; // 원형 큐처럼 동작
                currentSum += combined[end];
            } else {
                // 합이 크면 start를 밀어서 뺌
                currentSum -= combined[start];
                start++;
                if (start == n * 2) start = 0;
            }
            count++;
        }



        return -1;
    }
}