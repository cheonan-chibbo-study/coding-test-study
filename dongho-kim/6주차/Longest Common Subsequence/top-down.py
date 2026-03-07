class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        n1 = len(text1)
        n2 = len(text2)

        memo = [[-float('inf')] * n2 for _ in range(n1)]

        # text1의 포인터 i, text2의 포인터 j가 있을 때
        # text1[i:] 와 text2[j:] 의 최장 공통 부분 수열(LCS)의 길이를 계산하여 반환하는 메서드
        def recurse(i: int, j: int) -> int:
            if i == n1 or j == n2:
                return 0
            if memo[i][j] != -float('inf'):
                return memo[i][j]

            result = 0
            # 두 문자가 같은 경우
            if text1[i] == text2[j]:
                # 현재 문자가 공통 부분 수열에 포함되므로,
                # 두 포인터를 모두 다음으로 이동시킨 부분 문제의 결과에 1을 더한다.
                result = max(result, recurse(i + 1, j + 1) + 1)
            else:
                # 현재 문자가 다르므로, text1의 포인터만 이동한 경우(i+1, j)와
                # text2의 포인터만 이동한 경우(i, j+1) 중 더 긴 길이를 선택한다.
                result = max(result, recurse(i + 1, j), recurse(i, j + 1))
            memo[i][j] = result
            return result

        return recurse(0, 0)
