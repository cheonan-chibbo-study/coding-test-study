"""
로봇은 아래 또는 우측으로만 이동할 수 있다.
(0, 0)에서 시작해서 (m-1, n-1) 까지 이동할 수 있는 경우의 수는?

# 접근 방법
1. 매 칸마다 아래 또는 우측으로 이동하는 모든 경우를 따진다. -> O(2^100)
"""
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        memo = [[-1e9] * n for _ in range(m)]
        def recurse(x, y):
            if x >= m or y >= n:
                return 0
            if x == m-1 and y == n-1:
                return 1
            if memo[x][y] != -1e9:
                return memo[x][y]

            result = 0
            result += recurse(x + 1, y)
            result += recurse(x, y + 1)
            memo[x][y] = result
            return result

        return recurse(0, 0)
