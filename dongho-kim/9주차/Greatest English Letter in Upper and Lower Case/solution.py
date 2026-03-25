class Solution:
    def greatestLetter(self, s: str) -> str:
        lower_cases = [False] * 26
        upper_cases = [False] * 26

        for ch in s:
            if ch.isupper():
                idx = ord(ch) - ord('A')
                upper_cases[idx] = True
            else:
                idx = ord(ch) - ord('a')
                lower_cases[idx] = True

        for i in range(26 - 1, -1, -1):
            if lower_cases[i] and upper_cases[i]:
                return chr(ord('A') + i)
        return ""
