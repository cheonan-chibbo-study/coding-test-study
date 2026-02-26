## 👀 제한 시간 안에 어디까지 해냈는가?

`24분 13초`만에 문제를 해결했다. BFS & DFS 연습 문제인 만큼 문제 풀이를 떠올리는게 전혀 어렵지 않았지만 예외 케이스에 실수가 발생해서 24분 넘게 시간을 사용해 코드 작성에 성공했다… 작성한 코드는 다음과 같다.

```sql
# 24분 13초 만에 풀었다.

from collections import deque

class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        # 전역 데이터
        dir = [[-1, 0], [0, 1], [0, -1], [1, 0], [-1, 1], [-1, -1], [1, 1], [1, -1]]
        n = len(grid)
        visited = [[False for _ in range(n)] for _ in range(n)]

        # 메서드
        def bfs(r, c):
            dq = deque([[r, c, 1]])
            visited[r][c] = True

            while dq:
                cur_r, cur_c, cur_m = dq.popleft()
                if cur_r == n -1 and cur_c == n - 1 and grid[cur_r][cur_c] == 0:
                    return cur_m
                
                for dr, dc in dir:
                    next_r = cur_r + dr
                    next_c = cur_c + dc
                    next_m = cur_m + 1

                    if not is_safe(next_r, next_c) or visited[next_r][next_c]:
                        continue

                    dq.append([next_r, next_c, next_m])
                    visited[next_r][next_c] = True
        
            return -1

        def is_safe(r, c):
            return r >= 0 and r < n and c >= 0 and c < n and grid[r][c] == 0

        # 메인 로직
        if grid[0][0] == 1 or grid[n - 1][n - 1] == 1:
            return -1
        
        return bfs(0, 0)
```

---

## 🧑‍🔬 문제 분석

0 혹은 1로 구성된 2차원 배열이 주어졌을 때 (0, 0) → (n -1 , n - 1)까지 갈 수 있는 최단 거리를 구하는 문제이다.

- 이동은 0만 할 수 있다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `n == grid.length`
- `n == grid[i].length`
- `1 <= n <= 100`
- `grid[i][j] is 0 or 1`

---

## 🤔 풀이 고민

### BFS

전형적인 최단 거리 연습 문제이다. 문제 제약 조건을 보면 배열의 크기도 100이하의 아주 작은 입력이기 때문에 기본적은 BFS 풀이로 문제를 쉽게 해결할 수 있다.

### 결론

- BFS로 문제를 쉽게 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드 개선

제한 시간 내 스스로 정답 코드를 작성했지만 여러 예외 케이스 작성에 실수를 많이 해서 정답 코드 작성에 25분 이상을 소요했다. 아무리 풀이를 떠올려도 이런 세세한 케이스를 실패하면 결국 오답 처리이니 조심할 필요가 있다.

코드는 전형적인 BFS 코드라 딱히 개선할 부분은 없을거 같다.

```sql
from collections import deque

class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        # 전역 데이터
        dir = [[-1, 0], [0, 1], [0, -1], [1, 0], [-1, 1], [-1, -1], [1, 1], [1, -1]]
        n = len(grid)
        visited = [[False for _ in range(n)] for _ in range(n)]

        # 메서드
        def bfs(r, c):
            dq = deque([[r, c, 1]])
            visited[r][c] = True

            while dq:
                cur_r, cur_c, cur_m = dq.popleft()
                if cur_r == n -1 and cur_c == n - 1 and grid[cur_r][cur_c] == 0:
                    return cur_m
                
                for dr, dc in dir:
                    next_r = cur_r + dr
                    next_c = cur_c + dc
                    next_m = cur_m + 1

                    if not is_safe(next_r, next_c) or visited[next_r][next_c]:
                        continue

                    dq.append([next_r, next_c, next_m])
                    visited[next_r][next_c] = True
        
            return -1

        def is_safe(r, c):
            return r >= 0 and r < n and c >= 0 and c < n and grid[r][c] == 0

        # 메인 로직
        if grid[0][0] == 1 or grid[n - 1][n - 1] == 1:
            return -1
        
        return bfs(0, 0)
```

---

## 🥰 배운점 & 느낀점

- 세부 케이스 작성에 실수를 하지 않도록 주의하자.