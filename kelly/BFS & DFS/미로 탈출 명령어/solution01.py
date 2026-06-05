from collections import deque

def solution(n, m, x, y, r, c, k):
    dir = ((1, 0, "d"), (0, -1, "l"), (0, 1, "r"), (-1, 0, "u"))

    board = [[0] * (m + 2) for _ in range((n + 2))]
    for row in range(1, n + 1):
        for col in range(1, m + 1):
            board[row][col] = 1

    # 메서드
    def manhattan(row, col):
        return abs(r - row) + abs(c - col)

    def is_safe(row, col, step):
        if manhattan(row, col) > k - len(step) - 1:
            return False

        if board[row][col] != 1:
            return False

        return True

    # 메인 로직
    if manhattan(x, y) > k:
        return "impossible"

    dq = deque([(x, y, "")])
    while dq:
        cur_r, cur_c, cur_s = dq.popleft()

        if (cur_r, cur_c) == (r, c):
            if len(cur_s) == k:
                return cur_s

            if (k - len(cur_s)) % 2:
                return "impossible"

        for dr, dc, ds in dir:
            next_r, next_c = cur_r + dr, cur_c + dc

            if not is_safe(next_r, next_c, cur_s):
                continue

            dq.append((next_r, next_c, cur_s + ds))
            break

    return "impossible"