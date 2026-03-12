from collections import deque

def solution(board):
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

            if is_safe(next_r1, next_c1) and is_safe(next_r2, next_c2):
                next_p_list.append(((next_r1, next_c1), (next_r2, next_c2)))

        # 현재 가로 상태
        if r1 == r2:
            if is_safe(r1 - 1, c1) and is_safe(r2 - 1, c2):
                next_p_list.append(((r2 - 1, c2), (r2, c2)))
                next_p_list.append(((r1, c1), (r1 - 1, c1)))

            if is_safe(r1 + 1, c1) and is_safe(r2 + 1, c2):
                next_p_list.append(((r2 + 1, c2), (r2, c2)))
                next_p_list.append(((r1, c1), (r1 + 1, c1)))

        if c1 == c2:
            if is_safe(r1, c1 - 1) and is_safe(r2, c2 - 1):
                next_p_list.append(((r2, c2 - 1), (r2, c2)))
                next_p_list.append(((r1, c1), (r1, c1 - 1)))

            if is_safe(r1, c1 + 1) and is_safe(r2, c2 + 1):
                next_p_list.append(((r2, c2 + 1), (r2, c2)))
                next_p_list.append(((r1, c1), (r1, c1 + 1)))

        return next_p_list

    def is_safe(r, c):
        return 0 <= r < N and 0 <= c < N and board[r][c] == 0

    # 메인 로직
    N = len(board)
    desti = (N - 1, N - 1)

    visited = set()
    dq = deque()

    start_p = ((0, 0), (0, 1))
    start_m = 0
    dq.append((start_p, start_m))
    visited.add(start_p)

    while dq:
        cur_p, cur_m = dq.popleft()
        if desti in cur_p:
            return cur_m

        for next_p in get_next_p(cur_p):
            sorted = next_p if next_p[0] <= next_p[1] else (next_p[1], next_p[0])
            if sorted in visited:
                continue

            dq.append((next_p, cur_m + 1))
            visited.add(next_p)

    return -1