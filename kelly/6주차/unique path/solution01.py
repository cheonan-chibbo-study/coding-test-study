class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        # 전역 데이터
        costs = [[None] * n for _ in range(m)]
        for r in range(m):
            costs[r][0] = 1
        for c in range(n):
            costs[0][c] = 1

        # 메서드
        def dp(r, c):
            if r < 0 or r >= m or c < 0 or c >= n:
                return 0

            if not costs[r][c]:
                costs[r][c] = dp(r - 1, c) + dp(r, c - 1)

            return costs[r][c]

        # 메인 로직
        return dp(m - 1, n - 1)