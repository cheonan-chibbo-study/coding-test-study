from collections import deque

class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        # 전역 데이터
        #✅ 주어진 입력을 사용하기 편한 형태로 변경하기 => 방향 그래프로 변경
        visited = []
        graph = [[] for _ in range(numCourses)]
        indegree = [0] * numCourses

        # 이 문제에서 prerequisites의 원소 [v, u]는 u->v 의 방향을 가진 edge를 뜻한다.
        for v, u in prerequisites:
            graph[u].append(v)
            #✅ 그 과정 중 indegree를 기록한다.
            indegree[v] += 1

        # 메인 로직
        #✅ 위상정렬을 수행한다.
        # indegree == 0 인 정점부터 탐색이 시작된다.
        dq = deque()
        for v in range(numCourses):
            if indegree[v] == 0:
                dq.append(v)

        while dq:
            cur_v = dq.popleft()
            # cur_v에 해당하는 과목을 수강한 것.
            visited.append(cur_v)

            for next_v in graph[cur_v]:
                indegree[next_v] -= 1

                if indegree[next_v] == 0:
                    dq.append(next_v)

        return len(visited) == numCourses