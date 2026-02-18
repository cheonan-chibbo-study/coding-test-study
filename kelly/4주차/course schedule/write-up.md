## 👀 제한 시간 안에 어디까지 해냈는가?

위상 정렬 연습 문제라서 따로 시간 측정을 하고 풀지는 않았다. 밑에 write-up 내용을 참고하자.

---

## 🧑‍🔬 문제 분석

과목 총 개수, 과목별 의존성 리스트가 주어졌을 때 모든 과목을 수강할 수 있는지 여부를 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= numCourses <= 2000`
- `0 <= prerequisites.length <= 5000`
- `prerequisites[i].length == 2`
- `0 <= ai, bi < numCourses`
- All the pairs prerequisites[i] are **unique**.

---

## 🤔 풀이 고민

이 문제는 전형적인 위상 정렬 연습 문제이다. 킬캠 4주차 강의에서 배운 위상 정렬 코드를 응용해 풀이 코드를 작성하면 문제를 해결할 수 있다. (다만 내가 처음 작성한 코드는 최종 정답 처리를 받기는 했지만 시간 복잡도가 상당히 떨어진다… 킬캠에서 준 정답 코드와 정말 끝과 끝 차이다…)

### 결론

- 위상 정렬 알고리즘을 활용하여 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드 개선

혼자서 최종 정답 처리를 받는 코드를 작성했지만 시간 복잡도가 상당히 좋지않다.

```python
from heapq import heapify, heappush, heappop

class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        # 전역 데이터
        depen = [[] for _ in range(numCourses)]
        for a, b in prerequisites:
            if a in depen[b]:
                return False
            depen[a].append(b)

        # 메인 로직
        visited = [False] * numCourses
        pq = []
        for v in range(numCourses):
            if not depen[v]:
                heappush(pq, v)
                visited[v] = True

        while pq:
            cur_v = heappop(pq)
            for o_v in range(numCourses):
                if o_v == cur_v:
                    continue
                if cur_v in depen[o_v]:
                    depen[o_v].remove(cur_v)
                    if not depen[o_v]:
                        heappush(pq, o_v)
                        visited[o_v] = True

        return False not in visited
```

그리고 아래는 킬캠 풀이 코드인데 속도가 아주아주 빠르다.

```python

```

속도 차이가 나는 이유는 다음과 같다.

- 내 코드는 매번 모든 정점을 다 검사하고 있다.

    ```python
    for o_v in range(numCourses):
    ```

- `if cur_v in depen[o_v]:` 이 코드는 매번 O(K)번의 연산을 발생시킨다.
- `depen[o_v].remove(cur_v)` 역시 매번 O(K)번의 연산을 발생시킨다.
- 따라서 내 코드의 전체 복잡도는

    ```python
    정점 V개 ×
    각 정점마다 V번 반복 ×
    각 반복마다 V 탐색
    
    ==> O(V² + V * E)
    ```

- 하지만 킬캠 코드는 다음과 같은 이유 때문에 내 코드보다 훨씬 빠르다.
    - indegree만 감소시킴
    - 직접 간선 목록만 순회함
    - 불필요한 전체 탐색 없음

정리하면

![image.png](attachment:b3f54d61-2948-4b0b-b6c2-16594ca4f452:image.png)

- 핵심은 내 코드는 간선을 찾기 위해 매번 전체 정점을 검사하는 부분 때문에 느리지만, 킬캠 코드는 이미 저장된 간선만 바로 접근하기 때문에 훨씬 빠르다고 한다.

따라서 킬캠 코드를 참고해 다음과 같이 코드를 개선했더니 시간 복잡도가 훨씬 빨라졌다.

```python
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
```

- 이 문제는 heap을 사용할 이유가 없다.
- visited 처리도 리스트에 값을 추가하는 방식이니 마지막 정답 판별 때 모든 리스트를 뒤질 필요가 없어졌다.
- 이제 매 탐색마다 모든 요소를 도는 연산이 사라져서 속도도 개선된거 같다.

---

## 🥰 배운점 & 느낀점

- 위상 정렬을 연습할 수 있었고, 시간 효율을 지킬 수 있는 위상 정렬 코드 작성을 연습할 수 있었다.