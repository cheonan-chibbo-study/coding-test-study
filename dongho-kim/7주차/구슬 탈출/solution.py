import sys
from collections import deque

input = sys.stdin.readline

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

n, m = map(int, input().strip().split())
arr = [list(input().strip()) for _ in range(n)]

red_x, red_y, blue_x, blue_y = 0, 0, 0, 0
for x in range(n):
    for y in range(m):
        if arr[x][y] == 'R':
            red_x, red_y = x, y
        elif arr[x][y] == 'B':
            blue_x, blue_y = x, y

def move(x, y, d):
    move_cnt = 0

    while True:
        if arr[x][y] == 'O': break

        nx, ny = x + dx[d], y + dy[d]
        if arr[nx][ny] == '#':
            break

        x, y = nx, ny
        move_cnt += 1

    return x, y, move_cnt

q = deque()
visited = set()

q.append((red_x, red_y, blue_x, blue_y, 0))
visited.add((red_x, red_y, blue_x, blue_y))

while q:
    cur_red_x, cur_red_y, cur_blue_x, cur_blue_y, dist = q.popleft()

    if dist == 10: continue

    for d in range(4):
        next_red_x, next_red_y, red_move_cnt = move(cur_red_x, cur_red_y, d)
        next_blue_x, next_blue_y, blue_move_cnt = move(cur_blue_x, cur_blue_y, d)

        if arr[next_blue_x][next_blue_y] == 'O':
            continue

        if arr[next_red_x][next_red_y] == 'O':
            print(1)
            exit()

        if next_red_x == next_blue_x and next_red_y == next_blue_y:
            if red_move_cnt > blue_move_cnt:
                next_red_x -= dx[d]
                next_red_y -= dy[d]
            else:
                next_blue_x -= dx[d]
                next_blue_y -= dy[d]

        if (next_red_x, next_red_y, next_blue_x, next_blue_y) in visited: continue

        visited.add((next_red_x, next_red_y, next_blue_x, next_blue_y))
        q.append((next_red_x, next_red_y, next_blue_x, next_blue_y, dist + 1))

print(-1)
