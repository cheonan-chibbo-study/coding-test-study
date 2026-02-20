from collections import deque

class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        n = numCourses

        degree = [0] * n
        graph = [[] for _ in range(n)]
        for a, b in prerequisites:
            graph[b].append(a)
            degree[a] += 1

        answer = []
        cnt = 0
        q = deque([x for x in range(n) if degree[x] == 0])
        while q:
            curr = q.popleft()
            answer.append(curr)
            cnt += 1

            for nxt in graph[curr]:
                degree[nxt] -= 1
                if degree[nxt] == 0:
                    q.append(nxt)

        if cnt == n:
            return answer
        return []
