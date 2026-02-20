## 👀 제한 시간 안에 어디까지 해냈는가?

`12분 15초`만에 문제를 스스로 풀었다! 이전에 풀어본 [course schedule](https://www.notion.so/course-schedule-19d483e1832f81beb7c2eaf2d9d1353d?pvs=21)과 완전 유사한 문제였기 때문에 속도가 매우 빠른 코드를 스스로 작성해 최종 통과를 할 수 있었다. 제출한 코드는 아래 기록하겠다.

---

## 🧑‍🔬 문제 분석

course schedule과 완전 유사한 문제이기 때문에 course schedule의 write-up을 참고하자.

---

## 🤔 풀이 고민

course schedule과 완전 유사한 문제이기 때문에 course schedule의 write-up을 참고하자. 결론적으로 위상 정렬 알고리즘을 활용해 문제를 해결할 수 있다.

### 결론

- course schedule과 같은 위상 정렬 연습 문제이다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드 개선

최종 정답 처리를 받은 코드는 아래와 같다.

```python
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

```

---

## 🥰 배운점 & 느낀점

- course schedule를 제대로 공부했다면 쉽게 풀 수 있는 문제였다.