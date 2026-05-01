## 👀 제한 시간 안에 어디까지 해냈는가?

- Java로 처음 도전해서 `20분 47초`만에 혼자서 문제를 해결했다.
- Python으로 2차 풀이를 진행하여 `3분 26초`만에 혼자서 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

각 노드별 값을 기록한 info 리스트와 트리의 부모-자식 관계를 보여주는 edges가 주어질 때 문제 조건에 맞춰 트리를 순회할 때 가장 많이 모을 수 있는 양의 개수를 반환하는 문제이다.

- 자세한 트리 순회 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- 2 ≤ `info`의 길이 ≤ 17
    - `info`의 원소는 0 또는 1 입니다.
    - info[i]는 i번 노드에 있는 양 또는 늑대를 나타냅니다.
    - 0은 양, 1은 늑대를 의미합니다.
    - info[0]의 값은 항상 0입니다. 즉, 0번 노드(루트 노드)에는 항상 양이 있습니다.
- `edges`의 세로(행) 길이 = `info`의 길이 - 1
    - `edges`의 가로(열) 길이 = 2
    - `edges`의 각 행은 [부모 노드 번호, 자식 노드 번호] 형태로, 서로 연결된 두 노드를 나타냅니다.
    - 동일한 간선에 대한 정보가 중복해서 주어지지 않습니다.
    - 항상 하나의 이진 트리 형태로 입력이 주어지며, 잘못된 데이터가 주어지는 경우는 없습니다.
    - 0번 노드는 항상 루트 노드입니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

처음에는 BFS & DFS 방식을 떠올렸다. 하지만 이 방식으로는 문제를 해결하지 못한다. 이미 방문한 노드도 상황에 따라서는 다시 방문을 할 수 있기 때문이고 노드를 돌면서 양과 늑대의 개수를 조건에 맞게 조절하는 동시에 가질 수 있는 최대 양의 개수를 구해야 하기 때문에 BFS/DFS 방식의 코드로는 이 문제를 풀기 어렵다.

이 문제는 백트래킹 방식으로 풀어야 한다. 도달할 수 있는 모든 케이스를 탐색하면서 가능성이 없는 케이스는 제거해 시간 초과를 피하는 방식으로 문제를 풀어야한다.

문제를 처음 풀 때 가장 떠올리기 어려웠던건 이미 방문한 노드라도 상황에 따라 왔다갔다 하는 로직을 어떻게 백트래킹으로 구현하는가 였는데 핵심은 아래와 같다.

```sql
부모 노드는 방문한 적 있는데 자식 노드는 방문한 적 없는 케이스를 모두 탐색
```

위 케이스를 백트래킹으로 탐색하면서 그 과정에 모으게된 양의 개수를 계속 갱신해가면 결국 최대 크기의 양을 구할 수 있다.

참고로 가능성이 없는 케이스는 방문하지 않는 백트래킹 방식이므로 가능성이 없는 케이스를 판별하는 기준도 필요한데 이 기준은 위 핵심에서 조금만 생각하면 떠올릴 수 있다.

```sql
부모 -> 자식 방문 케이스를 visited로 관리
```

### 결론

- BFS & DFS로 풀기 어려운 문제이다.
- 백트래킹 방식으로 문제를 해결해야한다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

혼자서 Java & Python으로 무리없이 풀이 코드를 작성했다. 최종 정답 코드를 참고하자.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    def solution(info, edges):
        visited = [False] * len(info)
        answer = 0
        
        # 메서드
        def back_tracking(yang, wolf):
            nonlocal answer
            answer = max(answer, yang)
            
            for edge in edges:
                if not visited[edge[0]] or visited[edge[1]]:
                    continue
                
                nextY = yang
                nextW = wolf
                
                if info[edge[1]] == 0:
                    nextY += 1
                else:
                    nextW += 1
                
                if (nextY <= nextW):
                    continue
                
                visited[edge[1]] = True
                back_tracking(nextY, nextW)
                
                visited[edge[1]] = False
        
        # 메인 로직
        visited[0] = True
        back_tracking(1, 0)
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    class Solution {
        
        int[] info;
        int[][] edges;
        boolean[] visited;
        
        int answer = 0;
        
        public int solution(int[] info, int[][] edges) {
            this.info = info;
            this.edges = edges;
            this.visited = new boolean[info.length];
            
            // 메인 로직
            visited[0] = true;
            backTracking(1, 0);
            
            return answer;
        }
        
        private void backTracking(int yang, int wolf) {
            answer = Math.max(answer, yang);
            
            for (int[] edge : edges) {
                if (!visited[edge[0]] || visited[edge[1]]) {
                    continue;
                }
                
                int nextY = yang;
                int nextW = wolf;
                
                if (info[edge[1]] == 0) {
                    nextY += 1;
                } else {
                    nextW += 1;
                }
                
                if (nextY <= nextW) {
                    continue;
                }
                
                visited[edge[1]] = true;
                backTracking(nextY, nextW);
                
                visited[edge[1]] = false;
            }
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- BFS/DFS를 바로 떠올리게 하는 문제지만 사실 백트래킹 문제였다는게 이 문제의 재밌는 점인거 같다.
- 기존 노드 방문 체크와 살짝 다른 방식을 보여주는 재밌는 문제였다.