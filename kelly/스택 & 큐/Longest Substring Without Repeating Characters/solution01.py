from collections import deque

class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        answer = 0
        dq = deque()

        for v in s:
            if v not in dq:
                dq.append(v)
                answer = max(answer, len(dq))
                continue

            while dq:
                popped = dq.popleft()
                if popped == v:
                    break

            dq.append(v)

        return answer