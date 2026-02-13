from collections import deque

def solution(board):
    # 전역 데이터
    N = len(board)
    new_board = [[1 for _ in range(N + 2)] for _ in range(N + 2)]
    for r in range(N):
        for c in range(N):
            new_board[r + 1][c + 1] = board[r][c]

    # 메서드
    def get_next_p(p):
        next_p_list = []
        r1, c1 = p[0]
        r2, c2 = p[1]

        dir = [[-1, 0], [0, 1], [0, -1], [1, 0]]
        for dr, dc in dir:
            next_r1 = r1 + dr
            next_c1 = c1 + dc
            next_r2 = r2 + dr
            next_c2 = c2 + dc

            if new_board[next_r1][next_c1] == 0 and new_board[next_r2][next_c2] == 0:
                next_p_list.append(((next_r1, next_c1), (next_r2, next_c2)))

        # 현재 가로 상태
        if r1 == r2:
            if new_board[r1 - 1][c1] == 0 and new_board[r2 - 1][c2] == 0:
                next_p_list.append(((r2 - 1, c2), (r2, c2)))
                next_p_list.append(((r1, c1), (r1 - 1, c1)))

            if new_board[r1 + 1][c1] == 0 and new_board[r2 + 1][c2] == 0:
                next_p_list.append(((r2 + 1, c2), (r2, c2)))
                next_p_list.append(((r1, c1), (r1 + 1, c1)))

        if c1 == c2:
            if new_board[r1][c1 - 1] == 0 and new_board[r2][c2 - 1] == 0:
                next_p_list.append(((r2, c2 - 1), (r2, c2)))
                next_p_list.append(((r1, c1), (r1, c1 - 1)))
            if new_board[r1][c1 + 1] == 0 and new_board[r2][c2 + 1] == 0:
                next_p_list.append(((r2, c2 + 1), (r2, c2)))
                next_p_list.append(((r1, c1), (r1, c1 + 1)))

        return next_p_list

    # 메인 로직
    visited = set()
    dq = deque()

    start_p = ((1, 1), (1, 2))
    start_t = 0
    dq.append((start_p, start_t))
    visited.add(start_p)

    while dq:
        cur_p, cur_t = dq.popleft()
        if (N, N) in cur_p:
            return cur_t

        for next_p in get_next_p(cur_p):
            if next_p in visited:
                continue

            dq.append((next_p, cur_t + 1))
            visited.add(next_p)

    return -1