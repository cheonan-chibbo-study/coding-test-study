from collections import deque

def solution(n, m, x, y, r, c, k):
    # 전역 데이터
    DIR = [[1, 0, "d"], [0, -1, "l"], [0, 1, "r"], [-1, 0, "u"]]
    x, y, r, c = x - 1, y - 1, r - 1, c - 1

    # 메서드
    def is_safe(t_r, t_c):
        return t_r >= 0 and t_r < n and t_c >= 0 and t_c < m

    def manhattan(row, col):
        return abs(r - row) + abs(c - col)

    # 메인 로직
    if manhattan(x, y) > k or (k - manhattan(x, y)) % 2:
        return "impossible"

    dq = deque([(x, y, "")])
    while dq:
        cur_r, cur_c, his = dq.popleft()

        if (cur_r, cur_c) == (r, c):
            if len(his) == k:
                return his
            elif (k - len(his) - manhattan(cur_r, cur_c)) % 2:
                return "impossible"

        for dr, dc, dm in DIR:
            next_r, next_c = cur_r + dr, cur_c + dc

            if not is_safe(next_r, next_c) or manhattan(next_r, next_c) + len(his) + 1 > k:
                continue

            dq.append((next_r, next_c, his + dm))
            break

    return "impossible"
