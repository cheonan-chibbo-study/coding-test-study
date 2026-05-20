package week1.연속부분수열합의개수;
import java.util.*;
class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        int[] arr = new int[n*2]; // 원형 구현을위해 2배로 만들기
        int idx=0;
        for(int i=0; i< n*2;i++){
            arr[i] = elements[idx++];
            if(idx == n){
                idx=0;
            }
        }
        Set<Integer> set =new HashSet();
        // 1 2 3 4 5 1 2 3 4 5
        /*
        0
        5
        0
        0 + 1
        0 + 1 + 2
        0 + 1 + 2 + 3
        0 + 1 + 2 + 3 + 4

        1
        5
        1
        1 + 2
        1 + 2 + 3
        1 + 2 + 3 + 4
        1 + 2 + 3 + 4 + 5

        2
        5
        2
        2 + 3
        2 + 3 + 4
        2 + 3 + 4 + 5
        2 + 3 + 4 + 5 + 6
        .
        .
        .
        4
        5
        4
        4 + 5
        4 + 5 + 6
        4 + 5 + 6 + 7
        4 + 5 + 6 + 7 + 8
        5   1   2   3   4
        */
        for(int i=0; i< n;i++){
            int sum=0;
            for(int len =0;len<n ; len++)    {
                sum += arr[i+len];
                set.add(sum);

            }
        }

        return set.size();
    }
}