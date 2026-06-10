from collections import deque, defaultdict

def solution(n, computers):
    graph = defaultdict(list)
    for node in range(n):
        for conn in range(len(computers[node])):
            if computers[node][conn] == 1:
                graph[node].append(conn)

    # 메서드
    def bfs(start):
        dq = deque([start])
        visited[start] = True

        while dq:
            cur = dq.popleft()

            for next in graph[cur]:
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