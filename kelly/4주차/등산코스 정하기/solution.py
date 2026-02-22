from collections import defaultdict
from heapq import heappush, heappop

def solution(n, paths, gates, summits):
    summits.sort()
    summit_set = set(summits)

    graph = defaultdict(list)
    for i, j, w in paths:
        graph[i].append((w, j))
        graph[j].append((w, i))

    hq = []
    visited = [float('inf')] * (n + 1)

	#✅ 모든 출입구를 우선순위큐에 삽입한다.
    for gate in gates:
        heappush(hq, (0, gate))
        visited[gate] = 0

	#✅ intensity를 기준으로 다익스트라를 진행한다.
    while hq:
        intensity, node = heappop(hq)
        if intensity > visited[node] or node in summit_set:
            continue

        for weight, next_node in graph[node]:
            next_intensity = max(weight, intensity)
            if next_intensity < visited[next_node]:
                #✅ 다익스트라 진행 중 각 노드에 도달하는 과정의 최대 intensity값을 저장한다.
                visited[next_node] = next_intensity
                heappush(hq, (next_intensity, next_node))

	#✅ 다익스트라 완료 후 산봉우리들을 순회하며 정답을 찾는다.
    min_intensity = [0, float('inf')]
    for summit in summits:
        if min_intensity[1] > visited[summit]:
            min_intensity[0] = summit
            min_intensity[1] = visited[summit]

    return min_intensity