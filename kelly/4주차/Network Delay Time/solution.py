from heapq import heapify, heappush, heappop

class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        # 전역 데이터
        INF = float('inf')
        k -= 1
        graph = [[] for _ in range(n)]
        for t in times:
            graph[t[0] - 1].append((t[1] - 1, t[2]))

        # 메인 로직
        answer = -1
        costs = [INF] * n
        pq = [(0, k)]
        heapify(pq)
        costs[k] = 0

        while pq:
            cur_cost, cur_v = heappop(pq)

            if cur_cost > costs[cur_v]:
                continue

            answer = max(answer, cur_cost)
            for next_v, cost in graph[cur_v]:
                next_cost = cur_cost + cost

                if next_cost < costs[next_v]:
                    costs[next_v] = next_cost
                    heappush(pq, (next_cost, next_v))

        if INF in costs:
            return -1
        else:
            return answer