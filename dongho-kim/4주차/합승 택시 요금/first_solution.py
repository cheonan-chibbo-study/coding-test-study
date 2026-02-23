# 지점의 개수 n, 출발지점을 나타내는 s, A의 도착지점을 나타내는 a, B의 도착지점을 나타내는 b, 지점 사이의 예상 택시요금을 나타내는 fares

# 문제에서 요구하는 것: 두 사람이 s에서 출발해서 각각의 도착 지점까지 이동할 때, 최저 비용을 구하기

# 접근 방법
# 1. 다익스트라로 s를 기준으로 모든 노드까지의 최단 거리를 구한다. 그리고 s에서 a까지 이동하는 경로와 b까지 이동하는 경로를 구한다. -> 반례 존재

# 2. 합승이 효율적인 경우, 합승을 하면 전체 비용이 감소한다. 그리고 S에서 A까지 가는 모든 경로와 S에서 B까지 가는 모든 경로를 구한다. 그리고 각 두 경로로 이동한다고 할 때의 총 비용을 구한다. -> 시간 초과 -> 이동
from collections import deque

def solution(n, s, a, b, fares):
    # 연결 그래프 생성
    graph = [[] for _ in range(n + 1)]
    for fare in fares:
        c, d, f = fare
        graph[c].append((d, f))
        graph[d].append((c ,f))

    # _from부터 _to까지의 모든 경로를 구하는 메서드
    def get_route(_from, _to):
        result = []

        q = deque()
        q.append(str(_from))
        while q:
            route = q.popleft()

            if int(route[-1]) == _to:
                result.append(route)
                continue

            for nxt, weight in graph[int(route[-1])]:
                if str(nxt) in route:
                    continue
                new_route = route + str(nxt)
                q.append(new_route)

        return result


    to_a_routes = get_route(s, a)
    to_b_routes = get_route(s, b)

    # 경로를 Key로, 이동 거리를 Value로 저장하는 딕셔너리
    cache = dict()

    # 두 경로의 총 비용을 더하는 메서드
    def get_total_weight(route1, route2):
        result = 0

        i = 0
        j = 0
        while True:
            if i >= len(route1) - 1 and j >= len(route2) - 1: break

            route_a_from = -1
            route_a_to = -1
            if i < len(route1) - 1:
                route_a_from = route1[i]
                route_a_to = route1[i + 1]

            route_b_from = -1
            route_b_to = -1
            if i < len(route2) - 1:
                route_b_from = route2[j]
                route_b_to = route2[j + 1]

            if route_a_from != -1 and route_b_from == -1:
                cached_value = cache.get(route_a_from + route_a_to, -1)
                if cached_value == -1:
                    for nxt, weight in graph[int(route_a_from)]:
                        if nxt == int(route_a_to):
                            cache[route_a_from + route_a_to] = weight
                            result += weight
                            i += 1
                            break
                else:
                    result += cached_value
                    i += 1
            elif route_a_from == -1 and route_b_from == -1:
                cached_value = cache.get(route_b_from + route_b_to, -1)
                if cached_value == -1:
                    for nxt, weight in graph[int(route_b_from)]:
                        if nxt == int(route_b_to):
                            cache[route_b_from + route_b_to] = weight
                            result += weight
                            j += 1
                            break
                else:
                    result += cached_value
                    j += 1
            else:
                if route_a_from == route_b_from and route_a_to == route_b_to:
                    cached_value = cache.get(route_a_from + route_a_to, -1)
                    if cached_value == -1:
                        for nxt, weight in graph[int(route_a_from)]:
                            if nxt == int(route_a_to):
                                cache[route_a_from + route_a_to] = weight
                                result += weight
                                i += 1
                                j += 1
                                break
                    else:
                        result += cached_value
                        i += 1
                        j += 1
                else:
                    cached_value = cache.get(route_a_from + route_a_to, -1)
                    if cached_value == -1:
                        for nxt, weight in graph[int(route_a_from)]:
                            if nxt == int(route_a_to):
                                cache[route_a_from + route_a_to] = weight
                                result += weight
                                i += 1
                                break
                    else:
                        result += cached_value
                        i += 1

                    cached_value = cache.get(route_b_from + route_b_to, -1)
                    if cached_value == -1:
                        for nxt, weight in graph[int(route_b_from)]:
                            if nxt == int(route_b_to):
                                cache[route_b_from + route_b_to] = weight
                                result += weight
                                j += 1
                                break
                    else:
                        result += cached_value
                        j += 1

        return result

    answer = 1e9
    for route_a in to_a_routes:
        for route_b in to_b_routes:
            total_weight = get_total_weight(route_a, route_b)
            answer = min(answer, total_weight)
    return answer
