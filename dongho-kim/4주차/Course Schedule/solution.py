class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        n = numCourses

        graph = [[] for _ in range(n)]
        degree = [0] * n

        for a, b in prerequisites:
            graph[b].append(a)
            degree[a] += 1

        # 진입 차수가 0인 노드를 큐에 삽입
        q = deque([i for i in range(n) if degree[i] == 0])

        count = 0 # 수강 횟수
        while q:
            curr = q.popleft()
            count += 1

            for nxt in graph[curr]:
                degree[nxt] -= 1
                if degree[nxt] == 0:
                    q.append(nxt)

        return count == numCourses
