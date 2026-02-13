from itertools import combinations
from copy import deepcopy
from collections import deque

# 전역 데이터
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

# 메서드
def get_wall_combi():
    zero_p = []
    for r in range(N):
        for c in range(M):
            if board[r][c] == 0:
                zero_p.append((r, c))

    return list(combinations(zero_p, 3))

def get_new_board(combi):
    new_board = deepcopy(board)
    for c in combi:
        row, col = c
        new_board[row][col] = 1

    return new_board

def get_safe_area(board):
    visited = [[False for _ in range(M)] for _ in range(N)]
    dq = deque()
    for r in range(N):
        for c in range(M):
            if board[r][c] == 2:
                dq.append((r, c))
                visited[r][c] = True

    while dq:
        cur_r, cur_c = dq.popleft()
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_r = cur_r + dr
            next_c = cur_c + dc

            if not is_safe(next_r, next_c):
                continue

            next_o = board[next_r][next_c]
            if next_o == 1 or next_o == 2 or visited[next_r][next_c]:
                continue
            else:
                board[next_r][next_c] = 2
                visited[next_r][next_c] = True
                dq.append((next_r, next_c))

    safe_area_count = 0
    for r in board:
        for c in r:
            if c == 0:
                safe_area_count += 1

    return safe_area_count

def is_safe(r, c):
    return r >= 0 and r < N and c >= 0 and c < M

# 메인 로직
answer = -1
wall_combi = get_wall_combi()
for combi in wall_combi:
    answer = max(answer, get_safe_area(get_new_board(combi)))

print(answer)