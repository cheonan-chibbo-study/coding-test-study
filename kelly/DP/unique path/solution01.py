class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        memo = [[None] * n for _ in range(m)]
        memo[0][0] = 0

        for r in range(m):
            memo[r][0] = 1

        for c in range(n):
            memo[0][c] = 1

        # 메서드
        def dp(r, c):
            if r < 0 or r >= m or c < 0 or c >= n:
                return 0

            if memo[r][c] is None:
                memo[r][c] = dp(r - 1, c) + dp(r, c - 1)

            return memo[r][c]

        # 메인 로직
        return dp(m - 1, n - 1)