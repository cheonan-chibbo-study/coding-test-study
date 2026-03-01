class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if not s:
            return 0

        answer = float('-inf')
        n = len(s)
        start = 0
        counter = set()

        for i, c in enumerate(s):
            if c in counter:
                answer = max(answer, i - start)
                while c in counter:
                    counter.remove(s[start])
                    start += 1
            counter.add(c)

        answer = max(answer, n - start)
        return answer