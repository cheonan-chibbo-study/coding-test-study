# Brute Force
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        answer = ""

        n = len(s)
        for i in range(n):
            for j in range(i, n):
                substr = s[i:j+1]
                setted = set(substr)

                # 중복 문자가 포함된 경우
                if len(substr) != len(setted):
                    break

                if len(substr) >= len(answer):
                    answer = substr

        return len(answer)

