from collections import deque

class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        if grid[0][0] != 0:
            return -1

        dx = [-1, -1, 0, 1, 1, 1, 0, -1]
        dy = [0, 1, 1, 1, 0, -1, -1, -1]

        n = len(grid)
        arr = [[0] * n for _ in range(n)]
        arr[0][0] = 1

        def in_array(x, y):
            return 0 <= x < n and 0 <= y < n

        def bfs(sx, sy):
            q = deque()
            q.append([sx, sy])

            while q:
                x, y = q.popleft()

                for d in range(8):
                    nx, ny = x + dx[d], y + dy[d]
                    if not in_array(nx, ny):
                        continue
                    if grid[nx][ny] != 0:
                        continue

                    if arr[nx][ny] == 0:
                        arr[nx][ny] = arr[x][y] + 1
                        q.append([nx, ny])
                    else:
                        if arr[x][y] + 1 < arr[nx][ny]:
                            arr[nx][ny] = arr[x][y] + 1
                            q.append([nx, ny])

        bfs(0, 0)

        if arr[-1][-1] == 0:
            return -1
        return arr[-1][-1]
