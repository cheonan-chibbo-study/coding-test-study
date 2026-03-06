"""
# 요구사항
m x n 크기의 2차원 배열 matrix가 있고, 각 칸은 0 또는 1이다.
matrix에서 1을 포함하는 가장 큰 정사각형의 넓이를 구하라.

# 접근 방법
1. 4중 for문으로 두 지점을 찍고, 해당 지점의 영역을 전부 보면서 모두 1을 포함하면 넓이를 계산한다. O(N^5)
2. 정사각형의 크기를 0x0 부터 min(m, n) x min(m, n) 까지 넓히면서, 해당 정사각형을 모든 위치에 배치한다. O(N^5) -> 2차원 누적합을 계산해서 O(1) 만에 영역의 합을 구한다. O(N^3) -> Python 반복문 오버헤드
3. 바텀업 DP -> dp[x][y]: (x, y)를 우측 하단 꼭짓점으로 하는 가장 큰 정사각형의 한 변의 길이
"""
class Solution:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        n = len(matrix)
        m = len(matrix[0])

        max_side = 0
        dp = [[0] * (m + 1) for _ in range(n + 1)]
        for x in range(n):
            for y in range(m):
                if matrix[x][y] == '1':
                    dp[x][y] = min(dp[x-1][y], dp[x][y-1], dp[x-1][y-1]) + 1
                    max_side = max(max_side, dp[x][y])

        return max_side * max_side
