class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        dx = [-1, 0, 1, 0]
        dy = [0, 1, 0, -1]

        n = len(grid)
        m = len(grid[0])
        visited = [[False] * m for _ in range(n)]

        def dfs(x, y):
            visited[x][y] = True

            for d in range(4):
                nx, ny = x + dx[d], y + dy[d]

                if not (0 <= nx < n and 0 <= ny < m):
                    continue
                if visited[nx][ny]:
                    continue
                if grid[nx][ny] != "1":
                    continue
                dfs(nx, ny)


        answer = 0
        for x in range(n):
            for y in range(m):
                if visited[x][y]:
                    continue
                if grid[x][y] == "0":
                    continue
                answer += 1
                dfs(x, y)
        return answer
