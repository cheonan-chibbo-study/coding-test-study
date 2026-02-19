from heapq import heappush, heappop

class Solution:
    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start_node: int, end_node: int) -> float:
        graph = [[] for _ in range(n)]
        for i in range(len(edges)):
            u, v = edges[i]
            weight = succProb[i]
            graph[u].append((v, weight))
            graph[v].append((u, weight))

        dist = [-1e9] * n
        dist[start_node] = 1

        def dijkstra(start):
            q = []
            q.append((start, 1))

            while q:
                curr, w = heappop(q)

                if dist[curr] < w:
                    continue

                for nxt, weight in graph[curr]:
                    if dist[curr] * weight > dist[nxt]:
                        dist[nxt] = dist[curr] * weight
                        heappush(q, (nxt, dist[nxt]))

        dijkstra(start_node)

        if dist[end_node] == -1e9:
            return 0
        return dist[end_node]
