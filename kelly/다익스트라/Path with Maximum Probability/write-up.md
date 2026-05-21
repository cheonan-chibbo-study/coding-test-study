## 👀 제한 시간 안에 어디까지 해냈는가?

`30분`안에 문제를 해결하지 못했다. 풀이법도 떠올렸고 코드도 전부 작성했지만 딱 하나의 수학적 오류 코드로 테스트 케이스에서 실패했다.

마지막으로 작성한 코드는 아래와 같다.

```python
from heapq import heapify, heappush, heappop

class Solution:
    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start_node: int, end_node: int) -> float:
        # 전역 데이터
        graph = [[] for _ in range(n)]
        for i in range(len(edges)):
            v1, v2 = edges[i]
            graph[v1].append((v2, succProb[i]))
            graph[v2].append((v1, succProb[i]))

        # 메인 로직
        costs = [0] * n
        pq = []
        heappush(pq, (0.0, start_node))

        while pq:
            cur_cost, cur_v = heappop(pq)
            cur_cost = -cur_cost

            if cur_cost < costs[cur_v]:
                continue
            
            for next_v, cost in graph[cur_v]:
                next_cost = cur_cost * cost

                if next_cost > costs[next_v]:
                    costs[next_v] = next_cost
                    heappush(pq, (-next_cost, next_v))

        return costs[end_node]

```

P & J 트레이닝

- Java로 1차 풀이를 시도하여 `18분 53초`만에 문제를 해결했다.
    - 다만 문법이 몇개 생각나지 않아서 결국 서치를 했다…
- Python으로 2차 풀이를 시도하여 `12분`만에 문제를 풀었다.
    - 사실 에러가 많이 발생해서 완전히 혼자 풀지는 않고 잼미니의 도움을 받았다…
    - 확실히 Java와 Python의 방식이 달라서 서로 헷갈리는 부분이 발생하는거 같다. 조심하자.

---

## 🧑‍🔬 문제 분석

무방향 가중치 그래프 정보와 출발지, 목적지 정보가 주어졌을 때 출발지에서 목적지까지 갈 수 있는 가장 최대 확률을 구해 반환하는 문제이다. 만약 목적지까지 도달할 수 없다면 0을 반환한다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `2 <= n <= 10^4`
- `0 <= start, end < n`
- `start != end`
- `0 <= a, b < n`
- `a != b`
- `0 <= succProb.length == edges.length <= 2*10^4`
- `0 <= succProb[i] <= 1`
- There is at most one edge between every two nodes.

---

## 🤔 풀이 고민

이 문제는 전형적인 다익스트라 알고리즘 연습 문제이다. 다만 이전에 풀었던 문제와의 차이는 최대 힙을 활용해 우선 순위 큐를 만들어야 한다는 점이다.

### 결론

- 최대힙을 활용한 다익스트라 알고리즘으로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 틀린 부분 정정

위에 기록한 처음 내가 작성한 코드는 진짜 숫자 하나만 변경하면 코드가 정상 작동한다.

`heappush(pq, (0.0, start_node))` → `heappush(pq, (-1.0, start_node))`

- pq 생성 후 시작 노드 값을 초기화 하는 단계에서 초기 cost로 0이 아니라 -1.0을 넣어야 한다…
- 가중치 확률 계산이므로 노드 방문마다 가중치를 곱해서 누적해야 하기 때문에 당연히 초기 값이 1이어야 한다… 문제는 내가 멍청하게 초기 값을 0으로 잡아서 누적 데이터가 계속 0으로 유지되어 테스트 케이스를 틀리게 되었다.
- 참고로 `-1.0` 을 넣는 이유는 파이썬은 기본적으로 최대 힙을 지원하지 않기 때문에 최대 힙 구현을 위해서 꼼수를 사용한 것이다.
    - 당연히 pq에서 꺼낸 후에는 바로 -1을 곱해 양수로 변경해줘야한다.

틀린 부분을 정정한 전체 코드는 다음과 같다. 이 코드는 최종 정답 처리를 받는다.

```python
from heapq import heapify, heappush, heappop

class Solution:
    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start_node: int, end_node: int) -> float:
        # 전역 데이터
        graph = [[] for _ in range(n)]
        for i in range(len(edges)):
            v1, v2 = edges[i]
            graph[v1].append((v2, succProb[i]))
            graph[v2].append((v1, succProb[i]))

        # 메인 로직
        costs = [0] * n
        pq = []
        heappush(pq, (-1.0, start_node))

        while pq:
            cur_cost, cur_v = heappop(pq)
            cur_cost = -cur_cost

            if cur_cost < costs[cur_v]:
                continue
            
            for next_v, cost in graph[cur_v]:
                next_cost = cur_cost * cost

                if next_cost > costs[next_v]:
                    costs[next_v] = next_cost
                    heappush(pq, (-next_cost, next_v))

        return costs[end_node]

```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from heapq import heapify, heappush, heappop
    
    class Solution:
        def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start_node: int, end_node: int) -> float:
            graph = {}
            for i in range(len(edges)):
                edge = edges[i]
                graph.setdefault(edge[0], []).append((edge[1], succProb[i]))
                graph.setdefault(edge[1], []).append((edge[0], succProb[i]))
            
            cost = [float('-inf')] * n
            
            # 메인 로직
            pq = [(-1.0, start_node)]
            cost[start_node] = 1.0
    
            while pq:
                c_total_cost, c_node = heappop(pq)
                c_total_cost *= -1.0
    
                if c_node == end_node:
                    return c_total_cost
                
                if c_node not in graph:
                    continue
                
                for n_node, n_cost in graph[c_node]:
                    n_total_cost = c_total_cost * n_cost
    
                    if n_total_cost <= cost[n_node]:
                        continue
                    
                    heappush(pq, (-n_total_cost, n_node))
                    cost[n_node] = n_total_cost
            
            return 0
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
            Map<Integer, List<Node>> graph = new HashMap<>();
            for (int i = 0; i < edges.length; i++) {
                int[] edge = edges[i];
                graph.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(new Node(edge[1], succProb[i]));
                graph.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(new Node(edge[0], succProb[i]));
            }
    
            double[] cost = new double[n];
            for (int i = 0; i < n; i++) {
                cost[i] = -Double.MAX_VALUE;
            }
    
            // 메인 로직
            PriorityQueue<Node> pq = new PriorityQueue<>((v1, v2) -> {
                return Double.compare(v2.cost, v1.cost);
            });
    
            pq.offer(new Node(start_node, 1.0));
            cost[start_node] = 1.0;
    
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
    
                if (cur.n == end_node) {
                    return cur.cost;
                }
    
                if (!graph.containsKey(cur.n)) {
                    continue;
                }
    
                for (Node next : graph.get(cur.n)) {
                    Node nextStatus = new Node(next.n, cur.cost * next.cost);
                    if (nextStatus.cost <= cost[nextStatus.n]) {
                        continue;
                    }
    
                    pq.offer(nextStatus);
                    cost[nextStatus.n] = nextStatus.cost;
                }
            }
    
            return 0.0;
        }
    
        class Node {
            int n;
            double cost;
    
            public Node(int n, double cost) {
                this.n = n;
                this.cost = cost;
            }
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 너무 어이없는 실수로 문제를 풀지 못해 기분이 너무 좋지 않다. 실제 코테 였으면 진짜 미친짓이다. 이런 실수를 하지 않도록 앞으로 주의하자.