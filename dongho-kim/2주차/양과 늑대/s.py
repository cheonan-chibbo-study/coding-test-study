SHEEP = 0
WOLF = 1

# 힌트 얻고 푼 코드
def solution(info, edges):
    n = len(info)
    graph = [[] for _ in range(n)]
    for edge in edges:
        a, b = edge
        graph[a].append(b)
    answer = 0

    def dfs(visited, sc, wc):
        nonlocal answer
        answer = max(answer, sc)

        for curr in visited:
            for nxt in graph[curr]:
                if nxt in visited:
                    continue

                if info[nxt] == SHEEP:
                    visited.append(nxt)
                    dfs(visited, sc + 1, wc)
                    visited.pop()
                elif info[nxt] == WOLF:
                    if wc + 1 < sc:
                        visited.append(nxt)
                        dfs(visited, sc, wc + 1)
                        visited.pop()

    visited = [0]
    dfs(visited, 1, 0)

    return answer
