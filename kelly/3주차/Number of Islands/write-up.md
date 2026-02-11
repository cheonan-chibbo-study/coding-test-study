## 👀 제한 시간 안에 어디까지 해냈는가?

`16분 44초`만에 문제를 해결했다. BFS & DFS 연습 문제인 만큼 문제 풀이를 떠올리는게 전혀 어렵지 않았지만 코드 작성에 실수가 좀 있어 시간이 16분 넘게 걸렸다. 작성한 코드는 다음과 같다.

```sql
# 16분 44초만에 정답

from collections import deque

class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        # 전역 데이터
        dir = [[-1, 0], [0, 1], [0, -1], [1, 0]]
        m = len(grid)
        n = len(grid[0])
        visited = [[False for _ in range(n)] for _ in range(m)]

        # 메서드
        def bfs(row, col):
            dq = deque([[row, col]])
            visited[row][col] = True

            while dq:
                cur_r, cur_c = dq.popleft()
                for dr, dc in dir:
                    next_r = cur_r + dr
                    next_c = cur_c + dc
                    if not is_safe(next_r, next_c) or visited[next_r][next_c] or grid[next_r][next_c] == '0':
                        continue
                    
                    dq.append([next_r, next_c])
                    visited[next_r][next_c] = True
        
        def is_safe(r, c):
            return r >= 0 and r < m and c >= 0 and c < n

        # 메인 로직
        answer = 0
        for row in range(m):
            for col in range(n):
                if grid[row][col] == "1" and not visited[row][col]:
                    bfs(row, col)
                    answer += 1
        
        return answer
```

---

## 🧑‍🔬 문제 분석

1 혹은 0으로 구성된 2차원 배열이 주어졌을 때 독립된 섬의 개수가 몇개인지 반환하는 문제이다.

- 1은 땅, 0은 물

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 300`
- `grid[i][j]` is `'0'` or `'1'`.

---

## 🤔 풀이 고민

### BFS & DFS

문제를 보자마자 전형적인 BFS & DFS 연습 문제임을 깨달았다. 제약 조건을 보면 주어지는 배열 크기도 300이하로 매우 작기 때문에 기본적인 BFS & DFS 로직 작성으로 문제를 충분히 해결할 수 있다.

### 결론

- BFS & DFS 방식으로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드 개선

이 문제는 제한 시간안에 정답 코드를 작성하는데 성공했다. 코드 자체도 전형적인 BFS 코드라 딱히 변경할 부분은 없다.

```sql
from collections import deque

class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        # 전역 데이터
        dir = [[-1, 0], [0, 1], [0, -1], [1, 0]]
        m = len(grid)
        n = len(grid[0])
        visited = [[False for _ in range(n)] for _ in range(m)]

        # 메서드
        def bfs(row, col):
            dq = deque([[row, col]])
            visited[row][col] = True

            while dq:
                cur_r, cur_c = dq.popleft()
                for dr, dc in dir:
                    next_r = cur_r + dr
                    next_c = cur_c + dc
                    if not is_safe(next_r, next_c) or visited[next_r][next_c] or grid[next_r][next_c] == '0':
                        continue
                    
                    dq.append([next_r, next_c])
                    visited[next_r][next_c] = True
        
        def is_safe(r, c):
            return r >= 0 and r < m and c >= 0 and c < n

        # 메인 로직
        answer = 0
        for row in range(m):
            for col in range(n):
                if grid[row][col] == "1" and not visited[row][col]:
                    bfs(row, col)
                    answer += 1
        
        return answer
```

---

## 🥰 배운점 & 느낀점

- 코드 작성에 실수가 좀 있었다. BFS & DFS는 풀이는 떠올려도 코드 작성에 시간이 걸리는 만큼 코드 구현 연습도 많이 해야겠다.