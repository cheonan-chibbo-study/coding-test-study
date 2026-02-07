package week2.LongestValidParentheses;

import java.util.*;
class Solution {
    // 괄호 문제라서 스택으로 풀어야겠다고 생각함
    // 괄호가 () 인 갯수를 모두 구하는거는 성공했는데 가장긴 () 를 찾는게 어려움
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxLength =0;
        char[] c =s.toCharArray();
        stack.push(-1);
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                stack.push(i);
            } else { // c[i] == ')' 일 때 ) 이게 먼저오면 -1 을빼내야됨

                stack.pop(); // 일단 하나를 꺼냅니다.

                if (stack.isEmpty()) {
                    // 스택이 비었다면, 현재 ')'가 새로운 기준점이 됩니다.
                    stack.push(i);
                } else {
                    // 스택이 비어있지 않다면, (현재 인덱스 - 현재 top에 있는 인덱스)가 길이입니다.
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }


        return maxLength;


    }
}