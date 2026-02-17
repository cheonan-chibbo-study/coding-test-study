from collections import deque

def solution(n, m, x, y, r, c, k):
    answer = ''

    dr = ['d', 'l', 'r', 'u']
    dx = [1, 0, 0, -1]
    dy = [0, -1, 1, 0]

    def get_dist(x1, y1, x2, y2):
        return abs(x1 - x2) + abs(y1 - y2)

    if get_dist(x, y, r, c) > k:
        return 'impossible'

    def bfs(sx, sy):
        nonlocal answer

        # (x, y) 칸에 최소 몇 번만에 도착했는지 기록하는 2차원 배열
        visited_count = [[0] * (m + 1) for _ in range(n + 1)]
        q = deque()
        q.append((sx, sy, ""))

        while q:
            x, y, route = q.popleft()

            if x == r and y == c and len(route) == k:
                answer = route
                break

            for d in range(4):
                nx, ny = x + dx[d], y + dy[d]

                # 탐색하려는 위치가 격자를 벗어나면 탐색 X
                if not (1 <= nx <= n and 1 <= ny <= m): continue

                # 탐색하려는 위치로 이동할 때, (r, c)까지 남은 거리가 더 크다면 탐색 할 필요가 없다.
                if get_dist(nx, ny, r, c) > k - len(route) + 1:
                    continue

                # 탐색하려는 위치가 이번에 탐색하는 경우가 더 긴 경로를 통해서 방문하는 경우이다.
                # 만약 visited_count[nx][ny] 가 이번에 방문하려는 경우보다 작거나 같다면, 이미 최적의 방법으로 탐색을 한 셈이기 때문이다.
                if visited_count[nx][ny] == visited_count[x][y] + 1:
                    continue

                visited_count[nx][ny] = visited_count[x][y] + 1
                q.append((nx, ny, route + dr[d]))


    # 시작 위치부터 BFS 탐색 시작
    bfs(x, y)

    if answer == '':
        return 'impossible'
    return answer
