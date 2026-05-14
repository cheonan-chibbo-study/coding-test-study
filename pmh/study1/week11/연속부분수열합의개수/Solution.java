package study1.week11.연속부분수열합의개수;

import java.util.*;
class Solution {
    /*


     */
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length;
        /*int idx=0;
        for(int i=0;i<n*2;i++){
            newArr[i] =  elements[idx++];
            if(idx == n){
                idx=0;
            }

        }*/
        int[] arr = new int[n * 2];
        for (int i = 0; i < n * 2; i++) {
            arr[i] = elements[i % n];
        }

        for(int i=0;i<n;i++){
            int sum=0;
            for(int len=0;len<n;len++){
                sum+=arr[i+len];
                set.add(sum);
            }

        }


        return set.size();
    }

}