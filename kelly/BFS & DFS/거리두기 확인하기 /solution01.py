from collections import deque

def solution(places):
    # 메서드
    def check(place):
        visited = [[False] * len(place[0]) for _ in range(len(place))]
        for row in range(len(place)):
            for col in range(len(place[row])):
                if place[row][col] == "P" and not visited[row][col]:
                    if not bfs(place, visited, row, col):
                        return False

        return True

    def bfs(place, visited, s_row, s_col):
        dq = deque([(s_row, s_col, "P")])
        visited[s_row][s_col] = True

        while dq:
            c_row, c_col, c_obj = dq.popleft()
            exist_person = False

            for dr, dc in [(-1, 0), (0, 1), (0, -1), (1, 0)]:
                n_row, n_col = c_row + dr, c_col + dc

                if not is_safe(place, visited, n_row, n_col):
                    continue

                n_obj = place[n_row][n_col]

                if n_obj == "P":
                    if c_obj == "P" or exist_person:
                        return False

                    exist_person = True

                if not visited[n_row][n_col]:
                    dq.append((n_row, n_col, n_obj))
                    visited[n_row][n_col] = True

        return True

    def is_safe(place, visited, row, col):
        return 0 <= row < len(place) and 0 <= col < len(place[row]) and place[row][col] != "X"

    # 메인 로직
    answer = []
    for i in range(len(places)):
        if check(places[i]):
            answer.append(1)
        else:
            answer.append(0)

    return answer