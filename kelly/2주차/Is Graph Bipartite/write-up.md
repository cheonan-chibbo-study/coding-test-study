## 👀 제한 시간 안에 어디까지 해냈는가?

테스트 케이스에서 발생하는 시간 초과를 해결하지 못해 30분안에 문제를 풀지 못했다. 아래는 마지막까지 작성한 코드이다.

```python
from collections import deque

class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        # 전역 데이터
        NO = 0
        RED = 1
        BLUE = 2

        # 메인 로직
        dq = deque([0])
        visited = [False for _ in graph]
        visited[0] = True
        node_colors = [NO for _ in graph]
        node_colors[0] = RED

        while dq:
            cur = dq.popleft()
            for next in graph[cur]:
		            # 이 부분이 문제...
                if visited[next] and node_colors[cur] == node_colors[next]:
                    return False
                dq.append(next)
                visited[next] = True
                node_colors[next] = BLUE if node_colors[cur] == RED else RED

        return True 
```

- 1차 잘못은 로직 실수이다. 이미 방문한 노드에 대해 처리를 잘못해서 계속 dq에 값을 추가하기 때문에 무한 루프가 발생하여 시간 초과가 난것이다.
- 사실 위 문제를 해결했다고해도 최종 채점 케이스에서 오답을 받는다. 0번째 인덱스가 빈 배열일 경우를 고려하지 않기 때문이다.

---

## 🧑‍🔬 문제 분석

무방향 그래프 정보를 2차원 리스트 형태로 입력받아 이 그래프가 ***bipartite***인지 여부를 검사해 반환하는 문제이다.

<aside>
🔥

***[ bipartite란? ]

그래프가 이분 그래프인 경우, 정점들을 두 개의 독립 집합 A와 B로 분할할 수 있으며, 그래프의 모든 변은 집합 A의 정점과 집합 B의 정점을 연결한다.***

</aside>

문제에 주어지는 제약 조건은 다음과 같다.

**Constraints:**

- `graph.length == n`
- `1 <= n <= 100`
- `0 <= graph[u].length < n`
- `0 <= graph[u][i] <= n - 1`
- `graph[u]` does not contain `u`.
- All the values of `graph[u]` are **unique**.
- If `graph[u]` contains `v`, then `graph[v]` contains `u`.

---

## 🤔 풀이 고민

### BFS/DFS 활용

이번에도 문제를 보자마자 BFS/DFS를 떠올렸다. 주어진 그래프가 ***bipartite*** 여부를 검사해야 하는데 방법은 간단하게 떠올렸다. 특정 노드의 라벨을 red라고 가정하면 이 노드와 연결된 노드들의 라벨은 모두 red와 반대인 blue여야한다. 그래야 이분 그래프 조건을 만족한다.

이 아이디어를 BFS나 DFS를 활용해 풀이를 구현하면 문제를 해결할 수 있을것이다.

참고로 주어지는 인풋의 최대 값이 100정도로 크지 않기 때문에 충분히 BFS/DFS 풀이로 풀 수 있다.

### 결론

- BFS/DFS를 활용
- 노드들을 red/blue 라벨로 분류해 한 노드에 연결된 다른 노드들은 다른 라벨을 가지고 있음을 검사하는 방식으로 문제를 해결할 수 있음.

---

## 🏃 코드 작성 과정

### 틀린 부분 정정

결과적으로 30분안에 문제를 해결하지 못했다. 풀이법은 떠올렸지만 작성한 풀이 코드가 시간 초과를 발생시켰고 30분안에 이 문제를 해결하지 못했다. 마지막까지 작성한 코드는 다음과 같다.

```python
from collections import deque

class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        # 전역 데이터
        NO = 0
        RED = 1
        BLUE = 2

        # 메인 로직
        dq = deque([0])
        visited = [False for _ in graph]
        visited[0] = True
        node_colors = [NO for _ in graph]
        node_colors[0] = RED

        while dq:
            cur = dq.popleft()
            for next in graph[cur]:
		            # 이 부분이 문제...
                if visited[next] and node_colors[cur] == node_colors[next]:
                    return False
                dq.append(next)
                visited[next] = True
                node_colors[next] = BLUE if node_colors[cur] == RED else RED

        return True 
```

이 코드가 시간 초과를 일으키는 원인은 `if visited[next] and node_colors[cur] == node_colors[next]:` 이 로직이다. 이미 방문한 노드에 대한 검사 로직이 잘못 작성되어 이미 방문한 노드도 계속 dq에 삽입하고 있기 때문에 무한 루프를 발생시켜 시간 초과가 발생한것이다.

이 문제를 당장 해결한다면 다음과 같이 코드를 고칠 수 있다.

```python
from collections import deque

class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        # 전역 데이터
        NO = 0
        RED = 1
        BLUE = 2

        # 메인 로직
        dq = deque([0])
        visited = [False for _ in graph]
        visited[0] = True
        node_colors = [NO for _ in graph]
        node_colors[0] = RED

        while dq:
            cur = dq.popleft()
            for next in graph[cur]:
                if visited[next]:
                    if node_colors[cur] == node_colors[next]:
                        return False
                    else:
                        continue
                dq.append(next)
                visited[next] = True
                node_colors[next] = BLUE if node_colors[cur] == RED else RED

        return True 
```

- 이 코드를 실행하면 시간 초과가 발생하지 않고 테스트 케이스도 모두 통과한다.
- 하지만 최종 채점 케이스에서 오답 처리를 받는다. 주어진 그래프의 0번째 인덱스를 시작점으로 탐색하는데 0번째 인덱스가 빈 배열일 경우를 고려하지 않기 때문에 모든 그래프를 탐색하지 못하기 때문이다.
    - `0 <= graph[u].length < n` 이미 문제 제약 조건에 나와있는데… 꼼꼼하게 확인하지 못한 내 잘못이다.

이 문제도 해결한 코드는 다음과 같다.

```python
from collections import deque

class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        # 전역 데이터
        NO = 0
        RED = 1
        BLUE = 2

        visited = [False for _ in graph]
        node_colors = [NO for _ in graph]

        # 메서드
        def bfs(start):
            dq = deque([start])
            visited[start] = True
            node_colors[start] = RED

            while dq:
                cur_i = dq.popleft()
                cur_c = node_colors[cur_i]
                for next_i in graph[cur_i]:
                    if not visited[next_i]:
                        dq.append(next_i)
                        visited[next_i] = True
                        node_colors[next_i] = BLUE if cur_c == RED else RED
                        continue
                    
                    if cur_c == node_colors[next_i]:
                        return False
            return True

        # 메인 로직
        for i, v in enumerate(visited):
            if v:
                continue
            if not bfs(i):
                return False
        
        return True
```

- 이 코드를 제출하면 최종 정답 처리를 받는다.

### 최종 정답 코드 개선

GPT 피셜로 코드를 개선하면 다음과 같다.

```python
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
```

- colors를 활용하면 visited 배열 없이 방문 여부도 체크할 수 있다.
- blue, red 값을 -1, 1로 하면 - 연산으로 쉽게 색상 배정을 할 수 있다.

---

## 🥰 배운점 & 느낀점

- 제약 조건을 제대로 확인하자…
- 이번에도 논리 로직 실수가 있었다. 나 원래 이렇게 코딩 못했나…? 논리 로직을 더 신경써서 작성할 필요가 있다!!!