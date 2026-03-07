"""
# 요구사항
문자열 두 개가 주어질 때, 공통 최장 부분 문자열을 구한다. 없으면 0을 반환한다.
문자열의 부분열은 원본 문자열에서 일부 문자(아예 없을 수도 있음)를 삭제하고 남은 문자의 상대적 순서를 변경하지 않음으로써 생성된 새로운 문자열이다.

# 접근방법
1. 길이가 1부터 min(len(text1), len(text2)) 까지의 부분 문자열 길이를 정한다. -> 해당 길이만큼 각 문자에서 부분 문자열을 구한다. -> 두 부분 문자열을 비교한다.
"""

from itertools import combinations

class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        n1 = len(text1)
        n2 = len(text2)
        min_n = min(n1, n2)

        answer = 0
        for size in range(1, min_n + 1):
            candidates1 = list(combinations(text1, size))
            candidates2 = set(combinations(text2, size))

            for candidate1 in candidates1:
                if candidate1 in candidates2:
                    answer = max(answer, len(candidate1))

        return answer
