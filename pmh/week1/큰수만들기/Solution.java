package week1.큰수만들기;

import java.util.*;
class Solution {
    /*
    어떤 숫자에서 k 개의 수 제거시 얻을수있는 가장 큰 숫자

    숫자를 어떻게 제거할것인가 흠.... 어떻게 제거하지

    문자 자르고
    자른 모든 문자열 구하고
    left right 로 되나?

    정렬

    */
    public String solution(String number, int k) {

        Deque<Integer> dq = new ArrayDeque<>();

        for (char c : number.toCharArray()) {

            int current = c - '0';

            while (!dq.isEmpty() && k > 0 && dq.peek() < current) {
                dq.pop();
                k--;
            }

            dq.push(current);
        }

        // 아직 제거 못한 숫자 제거
        while (k > 0) {
            dq.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();

        // push 방식이라 역순 저장됨

        while (!dq.isEmpty()) {
            sb.append(dq.pollLast());
        }

        return sb.toString();
    }
}