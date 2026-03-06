class Solution:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        # 전역 데이터
        M = len(matrix)
        N = len(matrix[0])
        matrix = [[int(c) for c in r] for r in matrix]
        dp = [[0] * N for _ in range(M)]

        for r in range(0, M):
            if matrix[r][0] == 1:
                dp[r][0] = 1

        for c in range(0, N):
            if matrix[0][c] == 1:
                dp[0][c] = 1

        # 메인 로직
        for r in range(1, M):
            for c in range(1, N):
                if matrix[r][c] == 1:
                    dp[r][c] = min(dp[r][c - 1], dp[r - 1][c - 1], dp[r - 1][c]) + 1

        return max(max(r) for r in dp) ** 2