from collections import deque

class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        # 전역 데이터
        graph = [[] for _ in range(numCourses)]
        indegree = [0] * numCourses
        for a, b in prerequisites:
            if b in graph[a]:
                return []

            graph[b].append(a)
            indegree[a] += 1

        # 메인 로직
        answer = []
        dq = deque()
        for v in range(len(indegree)):
            if indegree[v] == 0:
                dq.append(v)

        while dq:
            cur_v = dq.popleft()
            answer.append(cur_v)

            for next_v in graph[cur_v]:
                indegree[next_v] -= 1
                if indegree[next_v] == 0:
                    dq.append(next_v)

        if len(answer) == numCourses:
            return answer
        else:
            return []
