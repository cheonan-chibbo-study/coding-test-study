class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        # 전역 데이터
        costs = [[None] * n for _ in range(m)]
        for r in range(m):
            costs[r][0] = 1
        for c in range(n):
            costs[0][c] = 1

        # 메인 로직
        for r in range(1, m):
            for c in range(1, n):
                costs[r][c] = costs[r-1][c] + costs[r][c-1]

        return costs[m-1][n-1]