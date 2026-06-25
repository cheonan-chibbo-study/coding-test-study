class Solution:
    def longestValidParentheses(self, s: str) -> int:
        stack = [-1]
        answer = 0

        for i, v in enumerate(s):
            if v == '(':
                stack.append(i)
            else:
                stack.pop()

                if stack:
                    answer = max(answer, i - stack[-1])
                else:
                    stack.append(i)

        return answer