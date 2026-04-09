from collections import deque

def solution(n, computers):
    # 전역 데이터
    board = [[] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if i == j:
                continue

            if computers[i][j] == 1:
                board[i].append(j)
                board[j].append(i)

    # 메서드
    def bfs(start):
        dq = deque([start])
        visited[start] = True

        while dq:
            cur = dq.popleft()
            for next in board[cur]:
                if visited[next]:
                    continue

                dq.append(next)
                visited[next] = True

    # 메인 로직
    answer = 0
    visited = [False] * n

    for node in range(n):
        if visited[node]:
            continue

        bfs(node)
        answer += 1

    return answer