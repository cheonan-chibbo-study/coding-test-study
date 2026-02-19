from heapq import heapify, heappush, heappop

class Solution:
    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start_node: int, end_node: int) -> float:
        # 전역 데이터
        graph = [[] for _ in range(n)]
        for i in range(len(edges)):
            v1, v2 = edges[i]
            graph[v1].append((v2, succProb[i]))
            graph[v2].append((v1, succProb[i]))

        # 메인 로직
        costs = [0] * n
        pq = []
        heappush(pq, (-1.0, start_node))

        while pq:
            cur_cost, cur_v = heappop(pq)
            cur_cost = -cur_cost

            if cur_cost < costs[cur_v]:
                continue

            for next_v, cost in graph[cur_v]:
                next_cost = cur_cost * cost

                if next_cost > costs[next_v]:
                    costs[next_v] = next_cost
                    heappush(pq, (-next_cost, next_v))

        return costs[end_node]
