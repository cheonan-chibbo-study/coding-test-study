from collections import deque

def solution(maps):
    # 전역 데이터
    W = 0
    E = 1
    n = len(maps)
    m = len(maps[0])

    # 메서드
    def bfs():
        dq = deque()
        visited = [[False] * m for _ in range(n)]
        dq.append((0, 0, 1))
        visited[0][0] = True
        min_step = -1

        while dq:
            cur_r, cur_c, cur_s = dq.popleft()

            if cur_r == n - 1 and cur_c == m - 1:
                min_step = cur_s
                break

            for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
                next_r, next_c = cur_r + dr, cur_c + dc
                if not is_safe(next_r, next_c, visited):
                    continue

                dq.append((next_r, next_c, cur_s + 1))
                visited[next_r][next_c] = True

        return min_step

    def is_safe(r, c, visited):
        return 0 <= r < n and 0 <= c < m and not visited[r][c] and maps[r][c] == E

    # 메인 로직
    return bfs()