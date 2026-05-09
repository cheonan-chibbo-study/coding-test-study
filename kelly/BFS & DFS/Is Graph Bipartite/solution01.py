from collections import deque

class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        E = 0
        R = -1
        B = 1

        mark = [0] * len(graph)

        # 메서드
        def bfs(start):
            dq = deque([start])
            mark[start] = R

            while dq:
                cur = dq.popleft()
                for next in graph[cur]:
                    if mark[next] == E:
                        mark[next] = -mark[cur]
                        dq.append(next)
                        continue
                    elif mark[next] == mark[cur]:
                        return False

            return True

        # 메인 로직
        for start in range(len(graph)):
            if mark[start] != E:
                continue

            if not bfs(start):
                return False

        return True