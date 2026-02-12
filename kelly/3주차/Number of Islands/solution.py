from collections import deque

class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        # 전역 데이터
        dir = [[-1, 0], [0, 1], [0, -1], [1, 0]]
        m = len(grid)
        n = len(grid[0])
        visited = [[False for _ in range(n)] for _ in range(m)]

        # 메서드
        def bfs(row, col):
            dq = deque([[row, col]])
            visited[row][col] = True

            while dq:
                cur_r, cur_c = dq.popleft()
                for dr, dc in dir:
                    next_r = cur_r + dr
                    next_c = cur_c + dc
                    if not is_safe(next_r, next_c) or visited[next_r][next_c] or grid[next_r][next_c] == '0':
                        continue

                    dq.append([next_r, next_c])
                    visited[next_r][next_c] = True

        def is_safe(r, c):
            return r >= 0 and r < m and c >= 0 and c < n

        # 메인 로직
        answer = 0
        for row in range(m):
            for col in range(n):
                if grid[row][col] == "1" and not visited[row][col]:
                    bfs(row, col)
                    answer += 1

        return answer