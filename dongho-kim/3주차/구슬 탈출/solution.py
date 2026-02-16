import sys
from collections import deque

input = sys.stdin.readline

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

n, m = map(int, input().strip().split())
arr = [list(input().strip()) for _ in range(n)]

R = (0, 0)
B = (0, 0)
O = (0, 0)
for x in range(n):
    for y in range(m):
        if arr[x][y] == 'R':
            R = (x, y)
        elif arr[x][y] == 'B':
            B = (x, y)
        elif arr[x][y] == 'O':
            O = (x, y)

def in_array(x, y):
    return 0 <= x < n and 0 <= y < m

# 벽에 부딪히기 전까지 혹은 구멍에 빠질 때까지 d 방향으로 이동한다.
def move(x, y, d):
    move_count = 0
    while True:
        if x == O[0] and y == O[1]:
            break

        nx, ny = x + dx[d], y + dy[d]
        if not in_array(nx, ny):
            break
        if arr[nx][ny] == '#':
            break
        move_count += 1
        x, y = nx, ny
    return x, y, move_count

flag = False
visited = [[[[False] * m for _ in range(n)] for _ in range(m)] for _ in range(n)]
q = deque()
visited[R[0]][R[1]][B[0]][B[1]] = True
q.append((R[0], R[1], B[0], B[1], 0))
while q:
    rx, ry, bx, by, depth = q.popleft()

    if depth >= 10: break

    for d in range(4):
        # 빨간 구슬, 파란 구슬 각각 이동
        nrx, nry, r_dist = move(rx, ry, d)
        nbx, nby, b_dist = move(bx, by, d)

        if nbx == O[0] and nby == O[1]:
            continue
        if nrx == O[0] and nry == O[1]:
            flag = True
            break

        # 겹쳤을 때 이동 거리에 따른 좌표 보정
        if nrx == nbx and nry == nby:
            if r_dist > b_dist:
                nrx -= dx[d]
                nry -= dy[d]
            else:
                nbx -= dx[d]
                nby -= dy[d]

        if visited[nrx][nry][nbx][nby]:
            continue

        visited[nrx][nry][nbx][nby] = True
        q.append((nrx, nry, nbx, nby, depth + 1))

    if flag: break

print(int(flag))
