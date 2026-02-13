from collections import deque

def solution(places):
    n = 5

    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    def bfs(place, sx, sy):
        q = deque()
        q.append([sx, sy])
        visited = [[False] * n for _ in range(n)]
        visited[sx][sy] = True

        while q:
            x, y = q.popleft()

            for d in range(4):
                nx, ny = x + dx[d], y + dy[d]

                if not (0 <= nx < n and 0 <= ny < n):
                    continue
                if visited[nx][ny]:
                    continue
                if place[nx][ny] == 'X':
                    continue

                dist = abs(sx - nx) + abs(sy - ny)

                if place[nx][ny] == 'P':
                    if dist <= 2:
                        return False
                if place[nx][ny] == 'O':
                    if dist <= 2:
                        q.append([nx, ny])
                        visited[nx][ny] = True
        return True

    answer = []
    for place in places:
        flag = True

        for x in range(n):
            if not flag:
                break

            for y in range(n):
                if place[x][y] == 'P':
                    if not bfs(place, x, y):
                        flag = False
                        break

        if flag:
            answer.append(1)
        else:
            answer.append(0)

    return answer
