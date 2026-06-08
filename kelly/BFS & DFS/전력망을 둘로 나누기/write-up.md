## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분` + 추가 시간 `14분 51초`를 사용해 거의 문제를 풀었지만… 발생한 논리 오류 2개를 혼자서 해결하지 못해 결국 제미나이의 도움을 받아 문제를 해결했더니 통과했다.

발생한 논리 오류를 먼저 언급하면 다음과 같다.

- `[[]] * n` -> 이 로직을 사용해 이차원 배열을 만들면 리스트 내부에 있는 각 리스트가 같은 주소를 가진 리스트로 생성되어 하나의 리스트만 수정해도 전체 리스트가 수정되는 비극이 발생한다.
    - 따라서 `[[] for _ in range(n)]` 이렇게 초기화를 해야한다.
- 양방향 트리인데 bfs를 위한 board 초기화 과정해서 단방향으로 설정하여 문제가 발생했다…
    - 양방향 설정을 해주면 문제가 해결된다.

2026년 6월 8일 천안 코테 오프라인 스터디에서 이 문제를 다뤘다.

- 스터디에서 Python으로 시도하여 `19분`만에 혼자 문제를 해결했다.
- Java로 2차 풀이를 시도하여 `15분 48초`만에 문제를 풀었지만...
    - Map의 키 리스트를 조회하는 메서드를 까먹어서 서치했다. (`map.keySet()`)
    - List에서 특정 값을 제거하는 방법을 몰라 서치했다. (`list.remove(Object o)`)

---

## 🧑‍🔬 문제 분석

송전탑 개수 n, 송전탑 간 연결 정보를 담은 리스트 wires가 주어졌을 때 하나의 연결을 잘라 송전탑 네트워크를 2개로 분리한 경우 가장 최소의 개수 차이를 보이는 2개의 네트워크로 나눴을 때 두 네트워크의 송전탑 개수 차이를 구해 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- n은 2 이상 100 이하인 자연수입니다.
- wires는 길이가 `n-1`인 정수형 2차원 배열입니다.
    - wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
    - 1 ≤ v1 < v2 ≤ n 입니다.
    - 전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

풀이법은 혼자서 생각해낼 수 있었고 결과적으로 올바른 풀이였다.

1. 송전탑 연결 정보 리스트를 순회하면서 각 순회 요소를 제거한 송전탑 트리를 매번 생성한다.
2. 1에서 생성한 트리를 순회하면서 각 네트워크별 송전탑 개수를 구해 그것들을 뺀 절대값을 answer와 비교하여 더 적은 값을 answer로 업데이트한다.
3. 모든 순회를 마친 answer 값을 반환한다.

주어지는 입력의 크기가 매우 작기 때문에 위 풀이로도 충분히 해결할 수 있다.

### 결론

내가 생각해낸 풀이로 코드를 작성하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

처음 작성한 코드는 다음과 같은데 계속해서 오답이 나왔다.

```python
from collections import deque

def solution(n, wires):
    # 메서드
    def divide_net(ignore):
        board = get_board(ignore)
        visited = [False] * (n + 1)
        result = []
        for pos in range(1, n + 1):
            if visited[pos]:
                continue
            
            result.append(bfs(board, visited, pos))
        
        return result
    
    def get_board(ignore):
        board = [[] for _ in range(n + 1)]
        for i in range(len(wires)):
            if i == ignore:
                continue
                
            board[wires[i][0]].append(wires[i][1])
        
        return board
    
    def bfs(board, visited, start):
        count = 1
        dq = deque()
        dq.append(start)
        visited[start] = True
        
        while dq:
            cur = dq.popleft()
            for next in board[cur]:
                if visited[next]:
                    continue
                
                dq.append(next)
                visited[next] = True
                count += 1
        
        return count
        
    # 메인 로직
    answer = float('inf')
    for ignore in range(0, len(wires)):
        result = divide_net(ignore)
        answer = min(answer, abs(result[0] - result[1]))
    
    return answer
```

- `[[]] * n` -> 이 로직을 사용해 이차원 배열을 만들면 리스트 내부에 있는 각 리스트가 같은 주소를 가진 리스트로 생성되어 하나의 리스트만 수정해도 전체 리스트가 수정되는 비극이 발생한다.
    - 따라서 `[[] for _ in range(n)]` 이렇게 초기화를 해야한다.
- 양방향 트리인데 bfs를 위한 board 초기화 과정해서 단방향으로 설정하여 문제가 발생했다…
    - 양방향 설정을 해주면 문제가 해결된다.

잘못된 부분을 수정하면 최종적으로 다음과 같은 코드가 나온다. 이 코드를 제출하면 문제가 해결된다.

```python
from collections import deque

def solution(n, wires):
    # 메서드
    def divide_net(ignore):
        board = get_board(ignore)
        visited = [False] * (n + 1)
        result = []
        for pos in range(1, n + 1):
            if visited[pos]:
                continue
            
            result.append(bfs(board, visited, pos))
        
        return result
    
    def get_board(ignore):
        board = [[] for _ in range(n + 1)]
        for i in range(len(wires)):
            if i == ignore:
                continue
                
            board[wires[i][0]].append(wires[i][1])
            board[wires[i][1]].append(wires[i][0])
        
        return board
    
    def bfs(board, visited, start):
        count = 1
        dq = deque()
        dq.append(start)
        visited[start] = True
        
        while dq:
            cur = dq.popleft()
            for next in board[cur]:
                if visited[next]:
                    continue
                
                dq.append(next)
                visited[next] = True
                count += 1
        
        return count
        
    # 메인 로직
    answer = float('inf')
    for ignore in range(0, len(wires)):
        result = divide_net(ignore)
        answer = min(answer, abs(result[0] - result[1]))
    
    return answer
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from collections import defaultdict
    
    def solution(n, wires):
        graph = defaultdict(list)
        for v1, v2 in wires:
            graph[v1].append(v2)
            graph[v2].append(v1)
        
        # 메서드
        def search(visited, start):
            result = 0
            stack = [start]
            visited.add(start)
            
            while stack:
                cur = stack.pop()
                result += 1
                
                for next in graph[cur]:
                    if next in visited:
                        continue
                    
                    stack.append(next)
                    visited.add(next)
            
            return result
        
        # 메인 로직
        answer = float('inf')
        
        for v1, v2 in wires:
            graph[v1].remove(v2)
            graph[v2].remove(v1)
            
            tree_count = []
            visited = set()
            for node in graph.keys():
                if node not in visited:
                    tree_count.append(search(visited, node))
            
            answer = min(answer, abs(tree_count[0] - tree_count[1]))
            
            graph[v1].append(v2)
            graph[v2].append(v1)
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        
        Map<Integer, List<Integer>> graph;
        
        public int solution(int n, int[][] wires) {
            graph = new HashMap<>();
            for (int[] wire : wires) {
                graph.computeIfAbsent(wire[0], k -> new ArrayList<>()).add(wire[1]);
                graph.computeIfAbsent(wire[1], k -> new ArrayList<>()).add(wire[0]);
            }
            
            // 메인 로직
            int answer = Integer.MAX_VALUE;
            
            for (int[] wire : wires) {
                graph.get(wire[0]).remove(Integer.valueOf(wire[1]));
                graph.get(wire[1]).remove(Integer.valueOf(wire[0]));
                
                List<Integer> nodeCount = new ArrayList<>();
                Set<Integer> visited = new HashSet<>();
                for (int start : graph.keySet()) {
                    if (visited.contains(start)) {
                        continue;
                    }
                    
                    nodeCount.add(search(visited, start));
                }
                
                answer = Math.min(answer, Math.abs(nodeCount.get(0) - nodeCount.get(1)));
                
                graph.get(wire[0]).add(wire[1]);
                graph.get(wire[1]).add(wire[0]);
            }
            
            return answer;
        }
        
        private int search(Set<Integer> visited, int start) {
            Deque<Integer> dq = new ArrayDeque<>();
            dq.push(start);
            visited.add(start);
            
            int result = 0;
            while (!dq.isEmpty()) {
                int cur = dq.pop();
                result++;
                
                if (!graph.containsKey(cur)) {
                    continue;
                }
                
                for (int next : graph.get(cur)) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    
                    dq.push(next);
                    visited.add(next);
                }
            }
            
            return result;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 혼자 풀이도 생각했고 코드 작성도 다 했는데 딱 2개 논리 오류를 해결하지 못해 최종 정답 처리를 받지 못한게 억울했다.
- 이런 실수를 두번은 하지 않도록 앞으로 주의해야겠다.
- 스터디에서 새로 풀었던 코드가 훨씬 간결한거 같다.