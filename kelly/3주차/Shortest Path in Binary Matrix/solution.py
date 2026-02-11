from collections import deque

class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        # 전역 데이터
        dir = [[-1, 0], [0, 1], [0, -1], [1, 0], [-1, 1], [-1, -1], [1, 1], [1, -1]]
        n = len(grid)
        visited = [[False for _ in range(n)] for _ in range(n)]

        # 메서드
        def bfs(r, c):
            dq = deque([[r, c, 1]])
            visited[r][c] = True

            while dq:
                cur_r, cur_c, cur_m = dq.popleft()
                if cur_r == n -1 and cur_c == n - 1 and grid[cur_r][cur_c] == 0:
                    return cur_m

                for dr, dc in dir:
                    next_r = cur_r + dr
                    next_c = cur_c + dc
                    next_m = cur_m + 1

                    if not is_safe(next_r, next_c) or visited[next_r][next_c]:
                        continue

                    dq.append([next_r, next_c, next_m])
                    visited[next_r][next_c] = True

            return -1

        def is_safe(r, c):
            return r >= 0 and r < n and c >= 0 and c < n and grid[r][c] == 0

        # 메인 로직
        if grid[0][0] == 1 or grid[n - 1][n - 1] == 1:
            return -1

        return bfs(0, 0)