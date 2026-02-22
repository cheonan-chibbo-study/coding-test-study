from collections import defaultdict
from heapq import heappush, heappop

def solution(n, paths, gates, summits):
    # 메인 로직
    summits.sort()
    summits_set = set(summits)

    graph = defaultdict(list)
    for i, j, w in paths:
        graph[i].append((w, j))
        graph[j].append((w, i))

    pq = []
    visited = [float('inf')] * (n + 1)
    for gate in gates:
        heappush(pq, (0, gate))
        visited[gate] = 0

    while pq:
        cur_inten, cur_v = heappop(pq)

        if cur_inten > visited[cur_v] or cur_v in summits_set:
            continue

        for weight, next_v in graph[cur_v]:
            next_inten = max(cur_inten, weight)

            if next_inten < visited[next_v]:
                visited[next_v] = next_inten
                heappush(pq, (next_inten, next_v))

    answer = [0, float('inf')]
    for s in summits:
        if visited[s] < answer[1]:
            answer = [s, visited[s]]

    return answer