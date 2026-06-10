## 👀 제한 시간 안에 어디까지 해냈는가?

`10분 48초`만에 혼자서 문제를 해결했다.

P & J 트레이닝

- Python으로 1차 시도를 하여 `7분 59초`만에 문제를 해결했다.
- Java로 2차 시도를 하여 `6분 14초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

전체 컴퓨터 개수 n, 각 컴퓨터 별 다른 컴퓨터와의 연결 여부를 담은 리스트 computers가 주어질 때 전체 네트워크 개수를 구해 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- 컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
- 각 컴퓨터는 0부터 `n-1`인 정수로 표현합니다.
- i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
- computer[i][i]는 항상 1입니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

너무 쉬운 BFS/DFS 기본 문제이다. 주어진 computers 리스트를 다루기 쉬운 격자 데이터로 변환 후 여러번 bfs/dfs를 수행하면서 전체 군집 개수를 카운팅 후 반환하면 끝이다.

- 입력 크기 자체도 최대 200으로 매우 작다.
- 그냥 전형적인 BFS/DFS 연습 문제이다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

위에 기술한 풀이를 코드로 구현하면 다음과 같다. 그냥 기본적인 BFS/DFS 코드를 작성하면된다.

```python
from collections import deque

def solution(n, computers):
    # 전역 데이터
    board = [[] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if i == j:
                continue
            
            if computers[i][j] == 1:
                board[i].append(j)
                board[j].append(i)
    
    # 메서드
    def bfs(start):
        dq = deque([start])
        visited[start] = True
        
        while dq:
            cur = dq.popleft()
            for next in board[cur]:
                if visited[next]:
                    continue
                
                dq.append(next)
                visited[next] = True
    
    # 메인 로직
    answer = 0
    visited = [False] * n
    
    for node in range(n):
        if visited[node]:
            continue
        
        bfs(node)
        answer += 1
    
    return answer
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from collections import deque, defaultdict
    
    def solution(n, computers):
        graph = defaultdict(list)
        for node in range(n):
            for conn in range(len(computers[node])):
                if computers[node][conn] == 1:
                    graph[node].append(conn)
        
        # 메서드
        def bfs(start):
            dq = deque([start])
            visited[start] = True
            
            while dq:
                cur = dq.popleft()
                
                for next in graph[cur]:
                    if visited[next]:
                        continue
                    
                    dq.append(next)
                    visited[next] = True
        
        # 메인 로직
        answer = 0
        visited = [False] * n
        
        for node in range(n):
            if visited[node]:
                continue
            
            bfs(node)
            answer += 1
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        
        Map<Integer, List<Integer>> graph;
        boolean[] visited;
        
        public int solution(int n, int[][] computers) {
            graph = new HashMap<>();
            for (int node = 0; node < n; node++) {
                for (int conn = 0; conn < computers[node].length; conn++) {
                    if (computers[node][conn] == 1) {
                        graph.computeIfAbsent(node, key -> new ArrayList<>()).add(conn);
                    }
                }
            }
            
            // 메인 로직
            int answer = 0;
            visited = new boolean[n];
            
            for (int node = 0; node < n; node++) {
                if (visited[node]) {
                    continue;
                }
                
                bfs(node);
                answer++;
            }
            
            return answer;
        }
        
        private void bfs(int start) {
            Deque<Integer> dq = new ArrayDeque<>();
            dq.offer(start);
            visited[start] = true;
            
            while (!dq.isEmpty()) {
                int cur = dq.poll();
                
                for (int next : graph.computeIfAbsent(cur, key -> new ArrayList<>())) {
                    if (visited[next]) {
                        continue;
                    }
                    
                    dq.offer(next);
                    visited[next] = true;
                }
            }
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 솔직히 Lv.3 문제는 아닌거 같다. Lv.2에 훨씬 어려운 문제가 더 많다.
- 그냥 BFS/DFS 연습 문제인거 같다.