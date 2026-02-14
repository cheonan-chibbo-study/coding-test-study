import sys
from collections import deque

input = sys.stdin.readline

EMPTY = 0
WALL = 1
DISABLED = 2
ENABLED = 10

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def main():
    answer = 1e9
    n, m = map(int, input().strip().split())
    arr = [list(map(int, input().strip().split())) for _ in range(n)]

    viruses = []
    empty_count = 0
    # 1. M개 이상, 10개 이하인 바이러스의 좌표를 리스트로 저장한다.
    for x in range(n):
        for y in range(n):
            if arr[x][y] == DISABLED:
                viruses.append((x,y))
            elif arr[x][y] == EMPTY:
                empty_count += 1

    # 빈칸이 처음부터 없다면 결과는 0
    if empty_count == 0:
        print(0)
        return

    # 3. 시뮬레이션 돌려서 바이러스를 퍼트린다.
    def simulate(arr):
        empty_count = 0

        # 3.1. 시뮬레이션용 격자로 변경
        for x in range(n):
            for y in range(n):
                if arr[x][y] == EMPTY:
                    arr[x][y] = '.'
                    empty_count += 1
                elif arr[x][y] == WALL:
                    arr[x][y] = '-'
                elif arr[x][y] == DISABLED:
                    arr[x][y] = '*'
                elif arr[x][y] == ENABLED:
                    arr[x][y] = 0

        q = deque()
        visited = [[False] * n for _ in range(n)]
        for x in range(n):
            for y in range(n):
                if arr[x][y] == 0:
                    q.append((x, y))
                    visited[x][y] = True

        max_val = 0
        while q:
            x, y = q.popleft()

            for d in range(4):
                nx, ny = x + dx[d], y + dy[d]
                if in_array(nx, ny) and arr[nx][ny] == '*' and not visited[nx][ny]:
                    q.append((nx, ny))
                    visited[nx][ny] = True
                    arr[nx][ny] = arr[x][y] + 1
                if in_array(nx, ny) and arr[nx][ny] == '.' and not visited[nx][ny]:
                    q.append((nx, ny))
                    visited[nx][ny] = True
                    arr[nx][ny] = arr[x][y] + 1
                    empty_count -= 1
                    max_val = max(max_val, arr[x][y] + 1)

        if empty_count > 0:
            return 1e9
        return max_val

    def in_array(x, y):
        return 0 <= x < n and 0 <= y < n


    # 2. 비활성 바이러스 중에서 M개를 골라서 활성 상태로 만든다.
    def recurse(i, cnt):
        if cnt == m:
            copied = [row[:] for row in arr]
            result = simulate(copied)

            nonlocal answer
            answer = min(answer, result)
            return

        if i == len(viruses):
            return

        # i번 째 바이러스를 활성 상태로 만든다.
        virus = viruses[i]
        vx, vy = virus

        arr[vx][vy] = ENABLED
        recurse(i + 1, cnt + 1)
        arr[vx][vy] = DISABLED

        # i번 째 바이러스를 선택하지 않는다.
        recurse(i + 1, cnt)

    recurse(0, 0)
    if answer == 1e9:
        print(-1)
    else:
        print(answer)

main()
