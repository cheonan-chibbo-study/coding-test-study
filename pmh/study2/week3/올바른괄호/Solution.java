package study2.week3.올바른괄호;

import java.util.*;
class Solution {
    boolean solution(String s) {

        Deque<Character> q = new ArrayDeque<>();
        for(char c :s.toCharArray()){

            if(c =='(' || q.isEmpty())q.push(c);
            if(c == ')'){
                if(q.pop() == ')') return false;
            }

        }

        return q.isEmpty();
    }
}