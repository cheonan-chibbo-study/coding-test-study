from collections import deque

class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        RED, BLUE = 1, -1
        colors = [0] * len(graph)   # 0: 미방문, 1: RED, -1: BLUE

        def bfs(start):
            queue = deque([start])
            colors[start] = RED

            while queue:
                cur = queue.popleft()
                for nxt in graph[cur]:
                    if colors[nxt] == 0:
                        colors[nxt] = -colors[cur]
                        queue.append(nxt)
                    elif colors[nxt] == colors[cur]:
                        return False
            return True

        for i in range(len(graph)):
            if colors[i] == 0:
                if not bfs(i):
                    return False

        return True