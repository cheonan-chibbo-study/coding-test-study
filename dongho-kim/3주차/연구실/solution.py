import sys
from collections import deque

input = sys.stdin.readline

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

EMPTY = 0
WALL = 1
VIRUS = 2


# 테스트 케이스
# 3 3
# 2 2 0
# 0 0 0
# 0 0 0
# 4

# 3 3
# 2 0 0
# 0 2 0
# 0 0 0
# 2

# 3 3
# 0 0 0
# 2 2 0
# 0 0 0
# 2

# 접근 방법
# 1. 격자에 정확히 3개의 벽을 세우는 모든 경우를 따진다. 다 세웠다면 해당 격자로 바이러스가 퍼진 경우를 시뮬레이션하고 안전 구역 개수를 센다.

def main():
    answer = 0
    n, m = map(int, input().strip().split())
    arr = [list(map(int, input().strip().split())) for _ in range(n)]

    def recurse(x, y, cnt):
        nonlocal answer

        if cnt == 3:
            copied = [row[:] for row in arr]
            result = simulate(copied)
            answer = max(answer, result)
            return

        if y == m:
            x += 1
            y = 0
        if x == n:
            return

        # (x, y)에 벽을 설치하는 경우
        if arr[x][y] == EMPTY: # 대신 EMTPY인 경우에만
            arr[x][y] = WALL
            recurse(x, y + 1, cnt + 1)
            arr[x][y] = EMPTY

        # (x, y)에 벽을 설치하지 않고 다음 행을 탐색하는 경우
        recurse(x, y + 1, cnt)


    def simulate(arr):
        q = deque()
        for x in range(n):
            for y in range(m):
                if arr[x][y] == VIRUS:
                    q.append((x, y))

        while q:
            x, y = q.popleft()
            for d in range(4):
                nx, ny = x + dx[d], y + dy[d]
                if in_array(nx, ny) and arr[nx][ny] == EMPTY:
                    arr[nx][ny] = VIRUS
                    q.append((nx, ny))

        result = 0
        for x in range(n):
            for y in range(m):
                if arr[x][y] == EMPTY:
                    result += 1
        return result


    def in_array(x, y):
        return 0 <= x < n and 0 <= y < m


    recurse(0, 0, 0)
    print(answer)


main()
