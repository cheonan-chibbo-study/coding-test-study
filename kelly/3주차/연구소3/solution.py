from collections import deque
from itertools import combinations

# 전역 데이터
N, M = map(int, input().split())
input_board = [list(map(int, input().split())) for _ in range(N)]

# 메서드
def get_candi():
    virus_pos = []
    for row in range(N):
        for col in range(N):
            if input_board[row][col] == 2:
                virus_pos.append((row, col))

    return list(combinations(virus_pos, M))

def get_new_board(candi):
    new_board = [["E" for _ in range(N)] for _ in range(N)]
    for row in range(N):
        for col in range(N):
            if input_board[row][col] == 1:
                new_board[row][col] = "-"
            elif input_board[row][col] == 2:
                if (row, col) in candi:
                    new_board[row][col] = "V"
                else:
                    new_board[row][col] = "*"

    return new_board

def search(board, candi):
    time = [[0 for _ in range(N)] for _ in range(N)]
    dq = deque()
    for row, col in candi:
        dq.append((row, col, 0))

    max_time = 0
    while dq:
        cur_r, cur_c, cur_t = dq.popleft()

        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_r = cur_r + dr
            next_c = cur_c + dc
            next_t = cur_t + 1

            if not is_safe(next_r, next_c):
                continue

            next_obj = board[next_r][next_c]
            if next_obj == "-" or next_obj == "V":
                continue
            elif next_obj == "E":
                time[next_r][next_c] = next_t
                max_time = max(max_time, next_t)
                board[next_r][next_c] = "V"
                dq.append((next_r, next_c, next_t))
            elif next_obj == "*":
                board[next_r][next_c] = "V"
                dq.append((next_r, next_c, next_t))

    for row in board:
        for col in row:
            if col == "E":
                return -1
    return max_time

def is_safe(row, col):
    return row >= 0 and row < N and col >= 0 and col < N

# 메인 로직
candi_list = get_candi()
answer = -1
for candi in candi_list:
    board = get_new_board(candi)
    time = search(board, candi)
    if time != -1:
        answer = min(answer, time) if answer != -1 else time

print(answer)