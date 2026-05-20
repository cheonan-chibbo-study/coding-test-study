## 👀 제한 시간 안에 어디까지 해냈는가?

이번 문제를 우선순위 큐와 heap을 연습하기 위해 따로 시간 측정을 하지 않고 풀었다. 밑에 write-up 내용을 참고하자.

P & J 트레이닝

- 다익스트라로 문제를 푸는 방법이 떠오르지 않아 복기를 위해 시간 측정 없이 문제를 풀었다.
- 코테에서 자주 만나지는 않는 유형이라고는 하지만 카카오 기출에도 다익스트라가 존재하는 만큼 복습이 중요한거 같다.

---

## 🧑‍🔬 문제 분석

가중치 방향 그래프 정보와 총 노드 개수, 시작 노드가 주어졌을 때 모든 노드를 방문할 수 있는 최단 거리를 구하는 문제이다. 만약 모든 노드를 방문할 수 없다면 -1을 반환한다. 자세한 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= k <= n <= 100`
- `1 <= times.length <= 6000`
- `times[i].length == 3`
- `1 <= ui, vi <= n`
- `ui != vi`
- `0 <= wi <= 100`
- All the pairs `(ui, vi)` are **unique**. (i.e., no multiple edges.)

---

## 🤔 풀이 고민

전형적인 우선순위 큐, heap 연습 문제이다. 최소 힙을 활용한 우선순위 큐를 활용하면 이 문제를 쉽게 해결할 수 있다.

### 결론

- 우선순위 큐, 최소 힙 연습 문제이다.

---

## 🏃 코드 작성 과정

### 틀린 부분 정정

처음 제출한 코드는 다음과 같은데 논리적으로 틀린 부분이 있었다.

```python
from heapq import heapify, heappush, heappop

class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        # 전역 데이터
        k -= 1
        graph = [[] for _ in range(n)]
        for t in times:
            graph[t[0] - 1].append((t[1] - 1, t[2]))

        # 메인 로직
        answer = -1
        costs = [100000] * n
        pq = [(0, k)]
        heapify(pq)
        costs[k] = 0

        while pq:
            cur_cost, cur_v = heappop(pq)
            answer = max(answer, cur_cost)

            if cur_cost > costs[cur_v]:
                continue
            
            for next_v, cost in graph[cur_v]:
                next_cost = cur_cost + cost

                if next_cost < costs[next_v]:
                    costs[next_v] = next_cost
                    heappush(pq, (next_cost, next_v))

        if 100000 in costs:
            return -1
        else:
            return answer
```

- 큐에서 당장 pop한 요소는 큐 내부에서의 최소 값은 맞지만 큐 내부에는 같은 v 정보의 요소가 들어있다 먼저 나간 경우가 있을 수 있다. 따라서 answer를 갱신하고 싶다면 현재 pop한 요소가 지금까지 살펴본 비용 중 가장 최저 비용이 맞는지 검사 후 갱신해야한다.

코드를 정정하면 아래와 같다. 이 코드는 최종 정답 처리를 받는다.

```python
from heapq import heapify, heappush, heappop

class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        # 전역 데이터
        k -= 1
        graph = [[] for _ in range(n)]
        for t in times:
            graph[t[0] - 1].append((t[1] - 1, t[2]))

        # 메인 로직
        answer = -1
        costs = [100000] * n
        pq = [(0, k)]
        heapify(pq)
        costs[k] = 0

        while pq:
            cur_cost, cur_v = heappop(pq)

            if cur_cost > costs[cur_v]:
                continue
            
            answer = max(answer, cur_cost)
            
            for next_v, cost in graph[cur_v]:
                next_cost = cur_cost + cost

                if next_cost < costs[next_v]:
                    costs[next_v] = next_cost
                    heappush(pq, (next_cost, next_v))

        if 100000 in costs:
            return -1
        else:
            return answer
```

### 최종 정답 코드 개선

작성한 최종 정답 코드는 다음과 같다.

```python
from heapq import heapify, heappush, heappop

class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        # 전역 데이터
        INF = float('inf')
        k -= 1
        graph = [[] for _ in range(n)]
        for t in times:
            graph[t[0] - 1].append((t[1] - 1, t[2]))

        # 메인 로직
        answer = -1
        costs = [INF] * n
        pq = [(0, k)]
        heapify(pq)
        costs[k] = 0

        while pq:
            cur_cost, cur_v = heappop(pq)

            if cur_cost > costs[cur_v]:
                continue
            
            answer = max(answer, cur_cost)
            for next_v, cost in graph[cur_v]:
                next_cost = cur_cost + cost

                if next_cost < costs[next_v]:
                    costs[next_v] = next_cost
                    heappush(pq, (next_cost, next_v))

        if INF in costs:
            return -1
        else:
            return answer
```

- 임의로 엄청 큰 수를 정의해야 할 때 처음에는 `100000`정도로 잡았는데 엄청 큰 값을 설정하고 싶으면 다음과 같이 할 수 있다.
    - `INF = float(’inf’)`

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from heapq import heappush, heappop
    
    class Solution:
        def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
            graph = {}
            for time in times:
                if time[0] in graph:
                    graph[time[0]].append((time[1], time[2]))
                else:
                    graph[time[0]] = [(time[1], time[2])]
            
            weight = [float('inf') for _ in range(n + 1)]
    
            # 메인 로직
            answer = -1
            pq = [(0, k)]
    
            while pq:
                c_weight, c_node = heappop(pq)
    
                if c_weight >= weight[c_node]:
                    continue
                else:
                    weight[c_node] = c_weight
                    answer = max(answer, c_weight)
                
                if c_node not in graph:
                    continue
                
                for n_node, cost in graph[c_node]:
                    n_weight = c_weight + cost
                    heappush(pq, (n_weight, n_node))
            
            for i in range(1, n + 1):
                if weight[i] == float('inf'):
                    return -1
            
            return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public int networkDelayTime(int[][] times, int n, int k) {
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int[] time : times) {
                graph.computeIfAbsent(time[0], key -> new ArrayList<>())
                    .add(new int[]{time[1], time[2]});
            }
    
            int[] weight = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                weight[i] = Integer.MAX_VALUE;
            }
    
            // 메인 로직
            int answer = Integer.MIN_VALUE;
            PriorityQueue<int[]> pq = new PriorityQueue<>((v1, v2) -> {
                return Integer.compare(v1[0], v2[0]);
            });
            
            pq.offer(new int[]{0, k});
    
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
    
                if (cur[0] >= weight[cur[1]]) {
                    continue;
                } else {
                    weight[cur[1]] = cur[0];
                    answer = Math.max(answer, cur[0]);
                }
    
                if (!graph.containsKey(cur[1])) {
                    continue;
                }
    
                for (int[] next : graph.get(cur[1])) {
                    int nextWeight = cur[0] + next[1];
                    pq.offer(new int[]{nextWeight, next[0]});
                }
            }
    
            for (int i = 1; i <= n; i++) {
                if (weight[i] == Integer.MAX_VALUE) {
                    return -1;
                }
            }
    
            return answer;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 파이썬에서의 우선순위 큐, 최소 힙을 연습할 수 있었다.
- 다익스트라 개념과 다익스트라 방식으로 풀이 코드를 작성하는 연습을 학습할 수 있었다.