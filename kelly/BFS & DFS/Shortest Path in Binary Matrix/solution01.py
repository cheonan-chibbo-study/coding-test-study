from collections import deque

class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        # 메서드
        def is_safe(row, col):
            return 0 <= row < len(grid) and 0 <= col < len(grid[row]) and grid[row][col] == 0 and not visited[row][col]

        # 메인 로직
        if grid[0][0] == 1:
            return -1

        dq = deque([(0, 0, 1)])
        visited = [[False] * len(grid[0]) for _ in range(len(grid[0]))]

        while dq:
            c_row, c_col, c_step = dq.popleft()

            if (c_row, c_col) == (len(grid) - 1, len(grid[0]) - 1):
                return c_step

            for dr, dc in ((-1, 0), (0, -1), (0, 1), (1, 0), (-1, -1), (-1, 1), (1, -1), (1, 1)):
                n_row, n_col = c_row + dr, c_col + dc

                if is_safe(n_row, n_col):
                    dq.append((n_row, n_col, c_step + 1))
                    visited[n_row][n_col] = True

        return -1