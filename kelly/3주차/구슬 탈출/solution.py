from collections import deque

# 전역 데이터
N, M = map(int, input().split())
board = [list(input()) for _ in range(N)]
DIR = [[-1, 0], [0, 1], [0, -1], [1, 0]]

# 메서드
def move(row, col, dr, dc):
    count = 0
    while board[row + dr][col + dc] != "#" and board[row][col] != "O":
        row += dr
        col += dc
        count += 1

    return row, col, count

# 메인 로직
answer = 0
dq = deque()
visited = set()

# 구슬 위치 확인
for row in range(N):
    for col in range(M):
        if board[row][col] == "R":
            rr, rc = row, col
        elif board[row][col] == "B":
            br, bc = row, col

dq.append([rr, rc, br, bc, 1])
visited.add((rr, rc, br, bc))

# 탐색 시작
while dq:
    cur_rr, cur_rc, cur_br, cur_bc, cur_move = dq.popleft()
    if cur_move > 10:
        break

    for dr, dc in [[-1,0],[1,0],[0,-1],[0,1]]:
        next_rr, next_rc, r_count = move(cur_rr, cur_rc, dr, dc)
        next_br, next_bc, b_count = move(cur_br, cur_bc, dr, dc)

        # 파란 구슬이 구멍에 들어간 경우
        if board[next_br][next_bc] == "O":
            continue

        # 중복 제거
        if (next_rr, next_rc, next_br, next_bc) in visited:
            continue

        # 빨간 구슬이 들어간 경우 (성공)
        if board[next_rr][next_rc] == "O":
            answer = 1
            break

        # 두 구슬의 위치가 같은 경우 더 멀리서 이동된 구슬을 이전 칸으로 이동시켜서 수정해야함
        if (next_rr, next_rc) == (next_br, next_bc):
            if r_count > b_count:
                next_rr -= dr
                next_rc -= dc
            else:
                next_br -= dr
                next_bc -= dc

        dq.append([next_rr, next_rc, next_br, next_bc, cur_move + 1])
        visited.add((next_rr, next_rc, next_br, next_bc))
    else:
        continue
    break

print(answer)