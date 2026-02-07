from collections import deque

class Solution:
    def longestValidParentheses(self, s: str) -> int:
        answer = 0
        dq = deque([-1])
        for i, v in enumerate(s):
            if v == "(":
                dq.append(i)
            else:
                dq.pop()
                if not dq:
                    dq.append(i)
                else:
                    answer = max(answer, i - dq[-1])

        return answer