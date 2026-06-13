from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    graph_size = 104
    graph = [[0] * graph_size for _ in range(graph_size)]

    for x1, y1, x2, y2 in rectangle:
        x1, y1, x2, y2 = x1 * 2, y1 * 2, x2 * 2, y2 * 2

        for row in range(y1, y2 + 1):
            for col in range(x1, x2 + 1):
                if y1 < row < y2 and x1 < col < x2:
                    graph[row][col] = -1;
                elif graph[row][col] == 0:
                    graph[row][col] = 1

    # 메인 로직
    start_r, start_c, target_r, target_c = characterY * 2, characterX * 2, itemY * 2, itemX * 2

    dq = deque([(start_r, start_c, 0)])
    visited = [[False] * graph_size for _ in range(graph_size)]
    visited[start_r][start_c] = True

    while dq:
        cur_r, cur_c, step = dq.popleft()

        if (cur_r, cur_c) == (target_r, target_c):
            return step // 2

        for dr, dc in ((-1, 0), (0, -1), (0, 1), (1, 0)):
            next_r, next_c = dr + cur_r, dc + cur_c

            if graph[next_r][next_c] != 1 or visited[next_r][next_c]:
                continue

            dq.append((next_r, next_c, step + 1))
            visited[next_r][next_c] = True

    return -1