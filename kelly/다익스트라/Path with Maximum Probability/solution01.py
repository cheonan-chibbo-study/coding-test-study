from heapq import heapify, heappush, heappop

class Solution:
    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start_node: int, end_node: int) -> float:
        graph = {}
        for i in range(len(edges)):
            edge = edges[i]
            graph.setdefault(edge[0], []).append((edge[1], succProb[i]))
            graph.setdefault(edge[1], []).append((edge[0], succProb[i]))

        cost = [float('-inf')] * n

        # 메인 로직
        pq = [(-1.0, start_node)]
        cost[start_node] = 1.0

        while pq:
            c_total_cost, c_node = heappop(pq)
            c_total_cost *= -1.0

            if c_node == end_node:
                return c_total_cost

            if c_node not in graph:
                continue

            for n_node, n_cost in graph[c_node]:
                n_total_cost = c_total_cost * n_cost

                if n_total_cost <= cost[n_node]:
                    continue

                heappush(pq, (-n_total_cost, n_node))
                cost[n_node] = n_total_cost

        return 0