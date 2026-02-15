from collections import deque

# 전역 데이터
N, M, oil = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
cur_t_row, cur_t_col = map(int, input().split())
client = [list(map(int, input().split())) for _ in range(M)]
cur_t_row -= 1
cur_t_col -= 1

for c in client:
    c[0] -= 1
    c[1] -= 1
    c[2] -= 1
    c[3] -= 1

# 메서드
def get_next_client():
    candi = []
    minimum_dis = -1
    visited = [[False for _ in range(N)] for _ in range(N)]
    dq = deque()
    dq.append((cur_t_row, cur_t_col, 0))
    visited[cur_t_row][cur_t_col] = True

    while dq:
        cur_row, cur_col, cur_dis = dq.popleft()
        for c in client:
            if cur_row == c[0] and cur_col == c[1]:
                if minimum_dis == -1:
                    candi.append(c)
                    minimum_dis = cur_dis
                elif minimum_dis > cur_dis:
                    candi = [c]
                    minimum_dis = cur_dis
                elif minimum_dis == cur_dis:
                    candi.append(c)

        if minimum_dis != -1:
            continue

        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_row = cur_row + dr
            next_col = cur_col + dc
            next_dis = cur_dis + 1

            if not is_safe(next_row, next_col) or board[next_row][next_col] == 1 or visited[next_row][next_col] or next_dis > oil:
                continue

            dq.append((next_row, next_col, next_dis))
            visited[next_row][next_col] = True

    if not candi:
        return [None, None]

    candi.sort(key = lambda x: (x[0], x[1]))
    return [candi[0], minimum_dis]

def move_client_des(s_row, s_col, d_row, d_col):
    visited = [[False for _ in range(N)] for _ in range(N)]
    dq = deque()
    dq.append((s_row, s_col, 0))
    visited[s_row][s_col] = True

    while dq:
        cur_row, cur_col, cur_dis = dq.popleft()

        if cur_row == d_row and cur_col == d_col:
            return [True, cur_dis]

        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_row = cur_row + dr
            next_col = cur_col + dc
            next_dis = cur_dis + 1

            if not is_safe(next_row, next_col) or board[next_row][next_col] == 1 or visited[next_row][next_col] or oil < next_dis:
                continue

            dq.append((next_row, next_col, next_dis))
            visited[next_row][next_col] = True

    return [False, -1]

def is_safe(r, c):
    return r >= 0 and r < N and c >= 0 and c < N

# 메인 로직
while client:
    next_client, distance = get_next_client()
    if next_client is None:
        oil = -1
        break
    else:
        oil -= distance
        cur_t_row = next_client[0]
        cur_t_col = next_client[1]

    is_success, distance = move_client_des(*next_client)
    if not is_success:
        oil = -1
        break
    else:
        oil += distance
        cur_t_row = next_client[2]
        cur_t_col = next_client[3]
        client.remove(next_client)

print(oil)