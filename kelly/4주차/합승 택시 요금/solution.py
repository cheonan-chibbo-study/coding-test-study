from collections import defaultdict
from heapq import heappush, heappop

def solution(n, s, a, b, fares):
    # 메인 로직
    graph = defaultdict(list)
    for c, d, f in fares:
        graph[c].append((f, d))
        graph[d].append((f, c))

    answer = float('inf')

    #✅ s, a, b 3개의 노드에서 모든 노드까지 도달하는 최소 비용을 저장한다.
    costs = [[float('inf') for _ in range(n + 1)] for _ in range(3)]

    #✅ s, a, b 3개의 노드에서 각각 다익스트라 알고리즘을 수행한다.
    for i, start_v in enumerate([s, a, b]):
        pq = []
        heappush(pq, (0, start_v))
        costs[i][start_v] = 0

        while pq:
            cur_cost, cur_v = heappop(pq)

            for cost, next_v in graph[cur_v]:
                next_cost = cur_cost + cost

                if costs[i][next_v] > next_cost:
                    costs[i][next_v] = next_cost
                    heappush(pq, (next_cost, next_v))

    #✅ 모든 노드를 순회하며 cost(s->x) + cost(x->a) + cost(x->b)의 최소비용을 반환한다.
    for i in range(1, n + 1):
        answer = min(answer, costs[0][i] + costs[1][i] + costs[2][i])

    return answer