from collections import defaultdict
from heapq import heapify, heappush, heappop

def solution(n, s, a, b, fares):
    graph = defaultdict(list)
    for v1, v2, cost in fares:
        graph[v1].append((v2, cost))
        graph[v2].append((v1, cost))

    visited = [[float('inf')] * (n + 1) for _ in range(3)]

    # 메인 로직
    for i, start_v in enumerate([s, a, b]):
        pq = [(0, start_v)]
        visited[i][start_v] = 0

        while pq:
            cur_t_cost, cur_v = heappop(pq)

            if cur_t_cost > visited[i][cur_v]:
                continue

            for next_v, cost in graph[cur_v]:
                next_t_cost = cur_t_cost + cost

                if next_t_cost < visited[i][next_v]:
                    heappush(pq, (next_t_cost, next_v))
                    visited[i][next_v] = next_t_cost

    answer = float('inf')
    for i in range(1, n + 1):
        answer = min(answer, visited[0][i] + visited[1][i] + visited[2][i])

    return answer