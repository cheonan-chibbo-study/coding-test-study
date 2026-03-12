from collections import deque

def solution(board):
    n = len(board)

    def in_range(x, y):
        return 0 <= x < n and 0 <= y < n

    def is_free(x, y):
        return in_range(x, y) and board[x][y] == 0

    def normalize(a, b):
        return tuple(sorted([a, b]))

    def get_next(pos):
        result = []
        (x1, y1), (x2, y2) = pos

        # 평행 이동
        for dx, dy in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
            nx1, ny1 = x1 + dx, y1 + dy
            nx2, ny2 = x2 + dx, y2 + dy
            if is_free(nx1, ny1) and is_free(nx2, ny2):
                result.append(normalize((nx1, ny1), (nx2, ny2)))

        # 가로 상태
        if x1 == x2:
            for d in [-1, 1]:  # 위, 아래
                if is_free(x1 + d, y1) and is_free(x2 + d, y2):
                    result.append(normalize((x1, y1), (x1 + d, y1)))
                    result.append(normalize((x2, y2), (x2 + d, y2)))

        # 세로 상태
        else:
            for d in [-1, 1]:  # 좌, 우
                if is_free(x1, y1 + d) and is_free(x2, y2 + d):
                    result.append(normalize((x1, y1), (x1, y1 + d)))
                    result.append(normalize((x2, y2), (x2, y2 + d)))

        return result

    start = normalize((0, 0), (0, 1))
    q = deque([(start, 0)])
    visited = {start}

    while q:
        pos, dist = q.popleft()

        if (n - 1, n - 1) in pos:
            return dist

        for nxt in get_next(pos):
            if nxt not in visited:
                visited.add(nxt)
                q.append((nxt, dist + 1))
