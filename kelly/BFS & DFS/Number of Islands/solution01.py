from collections import deque

class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:

        # 메서드
        def bfs(s_row, s_col):
            dq = deque([[s_row, s_col]])
            grid[s_row][s_col] = "0"

            while dq:
                c_row, c_col = dq.popleft()

                for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
                    n_row, n_col = c_row + dr, c_col + dc

                    if not is_safe(n_row, n_col):
                        continue

                    dq.append([n_row, n_col])
                    grid[n_row][n_col] = "0"

        def is_safe(row, col):
            return row >= 0 and row < len(grid) and col >= 0 and col < len(grid[row]) and grid[row][col] == "1"

        # 메인 로직
        answer = 0

        for row in range(len(grid)):
            for col in range(len(grid[row])):
                if grid[row][col] == "1":
                    bfs(row, col)
                    answer += 1

        return answer