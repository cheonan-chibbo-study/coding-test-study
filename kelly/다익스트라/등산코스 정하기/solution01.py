from heapq import heapify, heappush, heappop
from collections import defaultdict

def solution(n, paths, gates, summits):
    graph = defaultdict(list)
    for v1, v2, cost in paths:
        graph[v1].append((v2, cost))
        graph[v2].append((v1, cost))

    # 메인 로직
    pq = []
    intensities = [float('inf')] * (n + 1)

    for gate in gates:
        heappush(pq, (0, gate))
        intensities[gate] = 0

    while pq:
        cur_inten, cur_v = heappop(pq)

        if cur_inten > intensities[cur_v] or cur_v in summits:
            continue

        for next_v, cost in graph[cur_v]:
            next_inten = max(cur_inten, cost)

            if next_inten < intensities[next_v]:
                heappush(pq, (next_inten, next_v))
                intensities[next_v] = next_inten

    summits.sort()
    answer = [-1, float('inf')]
    for summit in summits:
        if intensities[summit] < answer[1]:
            answer = [summit, intensities[summit]]

    return answer