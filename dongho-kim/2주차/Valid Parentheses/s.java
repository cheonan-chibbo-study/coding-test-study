import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            char last = stack.peek();
            if (ch == ')' && last != '(') {
                return false;
            }
            if (ch == '}' && last != '{') {
                return false;
            }
            if (ch == ']' && last != '[') {
                return false;
            }
            stack.pop();
        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
}
