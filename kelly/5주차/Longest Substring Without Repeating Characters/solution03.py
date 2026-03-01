class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if not s:
            return 0

        answer = float('-inf')
        start = 0
        substr = {}

        for end in range(len(s)):
            if s[end] in substr:
                start = max(start, substr[s[end]] + 1)
            substr[s[end]] = end
            answer = max(end - start + 1, answer)

        return answer