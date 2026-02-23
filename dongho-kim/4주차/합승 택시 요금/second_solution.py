from heapq import heappush, heappop

def solution(n, s, a, b, fares):
    # 연결 그래프 생성
    graph = [[] for _ in range(n + 1)]
    for fare in fares:
        c, d, w = fare
        graph[c].append((d, w))
        graph[d].append((c, w))

    def dijkstra(start):
        dist = [float('inf')] * (n + 1)
        dist[start] = 0
        q = []
        q.append((0, start))

        while q:
            w, curr = heappop(q)

            if dist[curr] < w:
                continue

            for nxt, weight in graph[curr]:
                if dist[curr] + weight < dist[nxt]:
                    dist[nxt] = dist[curr] + weight
                    heappush(q, (dist[nxt], nxt))
        return dist

    dist_from_s = dijkstra(s)
    dist_from_a = dijkstra(a)
    dist_from_b = dijkstra(b)

    # 모든 노드에 대해서 합승 지점임을 계산한다.
    answer = 1e9
    for k in range(1, n + 1):
        # 총 거리 = s에서 k까지의 최단 경로 + k에서 a까지의 최단 경로 + k에서 b까지의 최단 경로
        total = dist_from_s[k] + dist_from_a[k] + dist_from_b[k]
        answer = min(answer, total)
    return answer
