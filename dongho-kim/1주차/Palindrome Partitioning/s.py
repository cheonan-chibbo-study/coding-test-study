class Solution:
    def partition(self, s: str) -> list[list[str]]:
        answer = []
        self.recurse(s, [], answer)
        return answer

    def recurse(self, s: str, result: list[str], answer: list[list[str]]):
        if len(s) == 0:
            answer.append(result[:])
            return

        for i in range(1, len(s) + 1):
            if s[:i] == s[:i][::-1]: # 부분 문자열이 팰린드롬인 경우
                result.append(''.join(s[:i]))
                self.recurse(s[i:], result, answer)
                result.pop()
