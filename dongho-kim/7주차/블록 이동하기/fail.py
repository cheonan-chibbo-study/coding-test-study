"""
# 요구사항
2x1 크기의 로봇이 있다.
0과 1로 이루어진 N x N 크기의 지도에서 로봇을 움직여 (n-1, n-1) 위치까지 이동해야 한다.
두 칸 중 어느 한 칸이라도 (n - 1, n - 1)에 도달하면 종료한다.
로봇의 시작 위치는 (0, 0) 이고 가로 방향으로 놓여있다.

로봇은 상하좌우 이동 가능하고, 로봇의 한 칸을 축으로 해서 회전할 수도 있다.
이때 회전 반경에 벽(1)이 없어야 한다.
회전하는 데 걸리는 시간은 정확히 1초이다.

이때 목적지까지 가는데 필요한 최소 시간을 반환한다.

# 접근 방법
1. (0, 0), (0, 1) 부터 시작해서 상하좌우, 회전하는 네 가지 경우를 모두 탐색해본다.
"""

from collections import deque

# 상 하 좌 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

rotate_to_right_when_flat = [
    [1, 0, 1, 1], # 좌측이 우측 아래로
    [-1, 0, -1, 1] # 좌측이 우측 위로
]
rotate_to_left_when_flat = [
    [1, 0, 1, -1], # 우측이 좌측 아래로
    [-1, 0, -1, -1], # 우측이 좌측 위로
]

rotate_to_right_when_vertical = [
    [0, 1, 1, 1], # 위가 오른쪽으로
    [0, -1, 1, -1] # 위가 왼쪽으로
]
rotate_to_left_when_vertical = [
    [0, 1, -1, 1], # 아래가 오른쪽으로
    [0, -1, -1, 1] # 아래가 왼쪽으로
]

def solution(board):
    n = len(board)

    visited = set()
    q = deque()

    visited.add((0, 0, 0, 1))
    q.append((0, 0, 0, 1, 0))

    def is_finish(x, y):
        return x == n - 1 or y == n - 1

    def in_array(x, y):
        return 0 <= x < n and 0 <= y < n

    def is_wall(x, y):
        return board[x][y] == 1

    answer = 1e9
    while q:
        x1, y1, x2, y2, time = q.popleft()
        print(f'curr => ({x1}, {y1}), ({x2}, {y2}) / {time}')

        if is_finish(x1, y1) or is_finish(x2, y2):
            answer = time
            break

        # 상하좌우 이동
        for d in range(4):
            nx1, ny1, nx2, ny2 = x1 + dx[d], y1 + dy[d], x2 + dx[d], y2 + dy[d]

            if in_array(nx1, ny1) and in_array(nx2, ny2):
                if not is_wall(nx1, ny1) and not is_wall (nx2, ny2):
                    if (nx1, ny1, nx2, ny2) not in visited:
                        visited.add((nx1, ny1, nx2, ny2))
                        q.append((nx1, ny1, nx2, ny2, time + 1))
                        print(f'상하좌우 => ({nx1}, {ny1}), ({nx2}, {ny2}) / {time + 1}')

        # 우측을 기준으로 회전
        if x1 == x2:
            for dx1, dy1, dx2, dy2 in rotate_to_right_when_flat:
                # (nx1, ny1) : 회전 경로
                # (nx2, ny2) : 회전 위치
                # (x2, y2) : 축
                nx1, ny1, nx2, ny2 = x1 + dx1, y1 + dy1, x1 + dx2, y1 + dy2

                if in_array(nx1, ny1) and in_array(nx2, ny2):
                    if not is_wall(nx1, ny1) and not is_wall(nx2, ny2):
                        if (nx2, ny2, x2, y2) not in visited:
                            visited.add((nx2, ny2, x2, y2))
                            q.append((nx2, ny2, x2, y2, time + 1))
                            print(f'우측 기준 회전 => ({nx2}, {ny2}), ({x2}, {y2}) / {time + 1}')
        else:
            for dx1, dy1, dx2, dy2 in rotate_to_right_when_vertical:
                # (nx1, ny1) : 회전 경로
                # (nx2, ny2) : 회전 위치
                # (x2, y2) : 축
                nx1, ny1, nx2, ny2 = x1 + dx1, y1 + dy1, x1 + dx2, y1 + dy2

                if in_array(nx1, ny1) and in_array(nx2, ny2):
                    if not is_wall(nx1, ny1) and not is_wall(nx2, ny2):
                        if (nx2, ny2, x2, y2) not in visited:
                            visited.add((nx2, ny2, x2, y2))
                            q.append((nx2, ny2, x2, y2, time + 1))
                            print(f'우측 기준 회전 => ({nx2}, {ny2}), ({x2}, {y2}) / {time + 1}')


        # 좌측을 기준으로 회전
        if x1 == x2:
            for dx1, dy1, dx2, dy2 in rotate_to_left_when_flat:
                # (nx1, ny1) : 회전 경로
                # (nx2, ny2) : 회전 위치
                # (x1, y1) : 축
                nx1, ny1, nx2, ny2 = x2 + dx1, y2 + dy1, x2 + dx2, y2 + dy2

                if in_array(nx1, ny1) and in_array(nx2, ny2):
                    if not is_wall(nx1, ny1) and not is_wall(nx2, ny2):
                        if (x1, y1, nx2, ny2) not in visited:
                            visited.add((x1, y1, nx2, ny2))
                            q.append((x1, y1, nx2, ny2, time + 1))
                            print(f'좌측 기준 회전 => ({x1}, {y1}), ({nx2}, {ny2}) / {time + 1}')
        else:
            for dx1, dy1, dx2, dy2 in rotate_to_left_when_vertical:
                # (nx1, ny1) : 회전 경로
                # (nx2, ny2) : 회전 위치
                # (x1, y1) : 축
                nx1, ny1, nx2, ny2 = x2 + dx1, y2 + dy1, x2 + dx2, y2 + dy2

                if in_array(nx1, ny1) and in_array(nx2, ny2):
                    if not is_wall(nx1, ny1) and not is_wall(nx2, ny2):
                        if (x1, y1, nx2, ny2) not in visited:
                            visited.add((x1, y1, nx2, ny2))
                            q.append((x1, y1, nx2, ny2, time + 1))
                            print(f'좌측 기준 회전 => ({x1}, {y1}), ({nx2}, {ny2}) / {time + 1}')

        print()

    return answer
