from heapq import heappush, heappop

class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        graph = {}
        for time in times:
            if time[0] in graph:
                graph[time[0]].append((time[1], time[2]))
            else:
                graph[time[0]] = [(time[1], time[2])]

        weight = [float('inf') for _ in range(n + 1)]

        # 메인 로직
        answer = -1
        pq = [(0, k)]

        while pq:
            c_weight, c_node = heappop(pq)

            if c_weight >= weight[c_node]:
                continue
            else:
                weight[c_node] = c_weight
                answer = max(answer, c_weight)

            if c_node not in graph:
                continue

            for n_node, cost in graph[c_node]:
                n_weight = c_weight + cost
                heappush(pq, (n_weight, n_node))

        for i in range(1, n + 1):
            if weight[i] == float('inf'):
                return -1

        return answer