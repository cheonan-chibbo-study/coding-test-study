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

        arr = [[0] * (m + 1)]
        for row in matrix:
            arr.append([0] + list(map(int, row)))

        n = len(arr)
        m = len(arr[0])

        arr_sum = [[0] * m for _ in range(n)]
        for y in range(1, m):
            arr_sum[1][y] = arr_sum[1][y-1] + arr[1][y]
        for x in range(1, n):
            arr_sum[x][1] = arr_sum[x-1][1] + arr[x][1]
        for x in range(2, n):
            for y in range(2, m):
                arr_sum[x][y] = arr[x][y] + arr_sum[x-1][y] + arr_sum[x][y-1] - arr_sum[x-1][y-1]

        max_size = min(n, m)
        answer = 0
        for size in range(0, max_size + 1):
            for x1 in range(1, n):
                for y1 in range(1, m):
                    if arr[x1][y1] == 0:
                        continue

                    x2 = x1 + size
                    y2 = y1 + size

                    if (x2 >= n or y2 >= m) or (arr[x2][y2] == 0):
                        continue

                    cnt = arr_sum[x2][y2] - arr_sum[x1-1][y2] - arr_sum[x2][y1-1] + arr_sum[x1-1][y1-1]
                    area = (y2 - y1 + 1) * (x2 - x1 + 1)

                    if cnt == area:
                        answer = max(answer, area)

        return answer
