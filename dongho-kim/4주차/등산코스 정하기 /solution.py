from heapq import heappush, heappop

def solution(n, paths, gates, summits):
    graph = [[] for _ in range(n + 1)]
    for i, j, w in paths:
        graph[i].append((j, w))
        graph[j].append((i, w))

    summits_set = set(summits)
    dist = [float('inf')] * (n + 1)
    q = []

    # 모든 출입구를 동시에 시작점으로 설정 (다중 출발 다익스트라)
    for gate in gates:
        dist[gate] = 0
        heappush(q, (0, gate))

    while q:
        curr_weight, curr_node = heappop(q)

        # 현재 intensity가 이미 기록된 최소값보다 크면 무시
        if curr_weight > dist[curr_node]:
            continue

        # 현재 노드가 산봉우리라면 더 이상 이동하지 않음 (문제 조건)
        if curr_node in summits_set:
            continue

        for nxt_node, weight in graph[curr_node]:
            # nxt_node로 갈 때의 새로운 weight (현재까지 중 최대값)
            new_weight = max(curr_weight, weight)

            # 더 작은 weight로 도달 가능한 경우만 갱신
            if new_weight < dist[nxt_node]:
                dist[nxt_node] = new_weight
                heappush(q, (new_weight, nxt_node))

    # 산봉우리들 중 최소 intensity를 가진 것 찾기
    result = [-1, float('inf')]
    # 산봉우리 번호가 낮은 순으로 확인하기 위해 정렬
    for summit in sorted(summits):
        if dist[summit] < result[1]:
            result = [summit, dist[summit]]

    return result
