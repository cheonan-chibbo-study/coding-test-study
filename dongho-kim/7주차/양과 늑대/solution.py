WOLF = 1

def solution(info, edges):
    n = len(info)

    graph = [[] for _ in range(n)]
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)

    max_sheeps = 1
    visited = [False] * n
    def dfs(sheeps, wolves):
        nonlocal max_sheeps

        if wolves >= sheeps:
            return
        max_sheeps = max(max_sheeps, sheeps)

        for i, v in enumerate(visited):
            if v:
                for nxt in graph[i]:
                    if visited[nxt]: continue

                    visited[nxt] = True
                    if info[nxt] == 1:
                        dfs(sheeps, wolves + 1)
                    else:
                        dfs(sheeps + 1, wolves)
                    visited[nxt] = False


    visited[0] = True
    dfs(1, 0)

    return max_sheeps
