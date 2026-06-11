from collections import deque

def solution(maps):
    n, m = len(maps), len(maps[0])
    print(n, m)
    graph = [[0] * (m + 2) for _ in range(n + 2)]
    for row in range(n):
        for col in range(m):
            graph[row + 1][col + 1] = maps[row][col]

    # 메인 로직
    dq = deque([(1, 1, 1)])
    visited = [[False] * (m + 2) for _ in range(n + 2)]
    visited[1][1] = True

    while dq:
        cur_r, cur_c, step = dq.popleft()

        if (cur_r, cur_c) == (n, m):
            return step

        for dr, dc in ((-1, 0), (0, -1), (0, 1), (1, 0)):
            next_r, next_c = dr + cur_r, dc + cur_c

            if graph[next_r][next_c] != 1 or visited[next_r][next_c]:
                continue

            dq.append((next_r, next_c, step + 1))
            visited[next_r][next_c] = True

    return -1