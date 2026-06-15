## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분` + 추가적인 도전에도 혼자서 문제를 풀지 못했다. 작성한 코드들 모두 테스트 케이스는 통과하지만 최종 채점 케이스의 절반을 만족하지 못했다.

아래는 작성한 코드들이다.

```python
from collections import defaultdict, deque
from heapq import heappush, heappop

def solution(tickets):
    # 메서드
    def bfs():
        dq = deque()
        visited = set()
        dq.append("ICN")
        visited.add("ICN")
        path = []
        
        while dq:
            cur = dq.popleft()
            path.append(cur)
            
            if len(board[cur]) == 0:
                break
            
            next = heappop(board[cur])
            dq.append(next)
        
        return path
            
    # 메인 로직
    board = defaultdict(list)
    for ticket in tickets:
        heappush(board[ticket[0]], ticket[1])
    
    return bfs()
```

```python
from collections import defaultdict

def solution(tickets):
    # 메서드
    def dfs(cur, path, visited):
        path.append(cur)
        
        for next in sorted(board[cur]):
            if next in visited:
                continue
            
            visited.add(next)
            dfs(next, path, visited)
        
    # 메인 로직
    board = defaultdict(list)
    for t in tickets:
        board[t[0]].append(t[1])
    
    path = []
    dfs("ICN", path, set())
    
    return path
```

P & J 트레이닝

- 스터디에서 Python으로 도전했지만 혼자 문제를 풀지 못해 결국 이전에 작성한 write-up을 참고했다.
- 생각보다 아이디어를 떠올리고 구현하기 어려운 문제이다. 좋은 문제니 열심히 복습하자.

---

## 🧑‍🔬 문제 분석

[출발지, 목적지] 정보를 담은 리스트 tickets가 주어질 때, “ICN”에서 출발해서 티켓을 모두 사용했을 때 순회하는 경로 리스트를 반환하는 문제이다.

- 정렬 조건도 포함되어 있는데 자세한건 문제를 참고하자.

문제에 주어지는 제약 조건은 다음과 같다.

### 제한사항

- 모든 공항은 알파벳 대문자 3글자로 이루어집니다.
- 주어진 공항 수는 3개 이상 10,000개 이하입니다.
- tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
- 주어진 항공권은 모두 사용해야 합니다.
- 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
- 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

처음에는 무작정 bfs를 사용했는데, 나중에 가서 bfs로 풀 수 없는 문제임을 깨달았다. 그래서 제한 시간을 다 사용하고 따로 DFS 코드를 구현했지만 이상하게도 문제를 풀 수 없었다.

```python
from collections import defaultdict

def solution(tickets):
    # 메서드
    def dfs(cur, path, visited):
        path.append(cur)
        
        if len(path) == len(tickets) + 1:
            return -1
        
        for next in sorted(board[cur]):
            if (cur, next) in visited:
                continue
            
            visited.add((cur, next))
            result = dfs(next, path, visited)
            if result == -1:
                return -1
            visited.discard((cur, next))
        
    # 메인 로직
    board = defaultdict(list)
    for t in tickets:
        board[t[0]].append(t[1])
    
    path = []
    dfs("ICN", path, set())
    
    return path
```

위 코드가 어떤 부분이 잘못되었는지 제미나이에게 물어봤고 다음과 같은 답변을 받을 수 있었다.

- 엉뚱한 공항이 남게 되는 문제 (path.pop() 누락)
    - path에 이동 경로를 넣는 코드가 dfs 함수 제일 위에서 실행되고 있기 때문에 백트래킹시 path에 가장 마지막에 넣은 값을 빼주는 작업을 할 수 없다.
- 중복 티켓은 무시됨.
    - set을 사용해서 방문 검색을 하고 있기 때문에 같은 출발지 → 도착지지만 엄연히 다른 티켓을 대상으로 할 때 visited에 걸려 방문하지 못하는 문제가 생긴다.

### 찾아본 풀이

따라서 위 문제를 해결하도록 코드를 수정하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 찾아본 풀이로 작성한 코드

```python
def solution(tickets):
    # 메서드
    def dfs(cur):
        if len(path) == len(tickets) + 1:
            return True
        
        for i in range(len(tickets)):
            if tickets[i][0] == cur and not visited[i]:
                next = tickets[i][1]
                visited[i] = True
                path.append(next)
                
                if dfs(next):
                    return True
                
                path.pop()
                visited[i] = False
                
        return False
        
    # 메인 로직
    tickets.sort(key=lambda x: (x[0], x[1]))
    visited = [False] * len(tickets)
    path = ["ICN"]
    dfs("ICN")
    
    return path
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    def solution(tickets):
        # 메서드
        def dfs(cur):
            if len(answer) == len(tickets) + 1:
                return True
            
            for i in range(len(tickets)):    
                if tickets[i][0] != cur or visited[i]:
                    continue
                
                next = tickets[i][1]
                answer.append(next)
                visited[i] = True
                
                if dfs(next):
                    return True
                
                answer.pop()
                visited[i] = False
        
        # 메인 로직
        tickets.sort(key = lambda x: (x[0], x[1]))
        answer = ["ICN"]
        visited = [False] * len(tickets)
        
        dfs("ICN")
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        
        List<String[]> tList;
        List<String> paths;
        boolean[] visited;
        
        public String[] solution(String[][] tickets) {
            tList = new ArrayList<>();
            for (String[] t : tickets) {
                tList.add(t);
            }
            
            Collections.sort(tList, (v1, v2) -> {
                if (!v1[0].equals(v2[0])) {
                    return v1[0].compareTo(v2[0]);
                }
                
                return v1[1].compareTo(v2[1]);
            });
            
            // 메인 로직
            paths = new ArrayList<>();
            visited = new boolean[tList.size()];
            
            paths.add("ICN");
            dfs("ICN");
            
            String[] answer = new String[paths.size()];
            for (int i = 0; i < paths.size(); i++) {
                answer[i] = paths.get(i);
            }
            
            return answer;
        }
        
        private boolean dfs(String cur) {
            if (paths.size() == tList.size() + 1) {
                return true;
            }
            
            for (int i = 0; i < tList.size(); i++) {
                if (!tList.get(i)[0].equals(cur) || visited[i]) {
                    continue;
                }
                
                String next = tList.get(i)[1];
                paths.add(next);
                visited[i] = true;
                
                if (dfs(next)) {
                    return true;
                }
                
                paths.remove(paths.size() - 1);
                visited[i] = false;
            }
            
            return false;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 보통 그래프 탐색이 나오면 BFS로 푸는 버릇 때문에 이번 문제도 대충 BFS로 풀 수 있겠다는 잘못된 판단을 내려서 문제를 해결하지 못했다.
- 특히 DFS특징인 백트래킹 후 이전 상태로 되돌리는 로직을 잘못 작성해서 문제를 풀지 못해 많은 시간을 소비했다.
- BFS가 아니라 꼭 DFS를 활용해야 문제를 풀 수 있는 케이스를 오랜만에 만났으니 복습할 필요가 있다.