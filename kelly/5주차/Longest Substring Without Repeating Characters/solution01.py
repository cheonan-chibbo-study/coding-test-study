from collections import deque

class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        answer = float('-inf')
        c_set = set()
        dq = deque()

        if not s:
            return 0

        for c in s:
            if c not in c_set:
                c_set.add(c)
                dq.append(c)
                answer = max(answer, len(c_set))
            else:
                while True:
                    poped = dq.popleft()
                    if poped == c:
                        break
                dq.append(c)
                c_set = set(dq)

        return answer