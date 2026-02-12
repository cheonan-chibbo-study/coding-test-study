from collections import deque

def solution(places):
    # 전역 데이터
    dir = [[-1, 0], [0, 1], [0, -1], [1, 0]]

    # 메서드
    def has_distance(p):
        for r in range(5):
            for c in range(5):
                if p[r][c] != "P":
                    continue

                if not check_distance(p, r, c):
                    return False

        return True

    def check_distance(p, r, c):
        visited = [[False for _ in range(5)] for _ in range(5)]
        dq = deque([[r, c, 0]])
        visited[r][c] = True

        while dq:
            cur_r, cur_c, cur_d = dq.popleft()
            for dr, dc in dir:
                next_r = cur_r + dr
                next_c = cur_c + dc
                next_d = cur_d + 1

                if not is_safe(next_r, next_c) or visited[next_r][next_c] or p[next_r][next_c] == "X" or next_d > 2:
                    continue

                if p[next_r][next_c] == "P":
                    return False

                dq.append([next_r, next_c, next_d])
                visited[next_r][next_c] = True

        return True

    def is_safe(r, c):
        return r >= 0 and r < 5 and c >= 0 and c < 5

    # 메인 로직
    answer = [1] * 5
    for i in range(5):
        if not has_distance(places[i]):
            answer[i] = 0

    return answer