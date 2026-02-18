from heapq import heappush, heappop

class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        graph = [[] for _ in range(n + 1)]
        for time in times:
            u, v, w = time
            graph[u].append((v, w))

        dist = [1e9] * (n + 1)
        dist[k] = 0
        def dijkstra(start):
            pq = []
            pq.append((start, 0))

            while pq:
                curr, w = heappop(pq)

                if w > dist[curr]:
                    continue

                for nxt, weight in graph[curr]:
                    if dist[curr] + weight < dist[nxt]:
                        dist[nxt] = dist[curr] + weight
                        heappush(pq, (nxt, dist[nxt]))

        dijkstra(k)

        answer = -1e9
        for i in range(1, n + 1):
            if dist[i] == 1e9:
                return -1
            answer = max(answer, dist[i])
        return answer
