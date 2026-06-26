from collections import defaultdict

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        t_count = len(t)
        target = defaultdict(int)
        for ch in t:
            target[ch] += 1

        l, r = 0, 0
        min_len = float('inf')
        answer = ""

        while r < len(s):
            if s[r] in target:
                target[s[r]] -= 1

                if target[s[r]] >= 0:
                    t_count -= 1

            while t_count == 0:
                if r - l - 1 < min_len:
                    min_len = r - l - 1
                    answer = s[l:r + 1]

                if s[l] in target:
                    target[s[l]] += 1

                    if target[s[l]] > 0:
                        t_count += 1

                l += 1

            r += 1

        return answer