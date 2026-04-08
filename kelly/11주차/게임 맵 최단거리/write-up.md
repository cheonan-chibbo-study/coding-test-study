## 👀 제한 시간 안에 어디까지 해냈는가?

`13분`만에 혼자 문제를 해결할 수 있었다.

---

## 🧑‍🔬 문제 분석

격자 정보가 주어질 때 (1, 1)에서 사용자가 출발할 경우 격자의 끝인 (n, m)까지 도달할 수 있는 최단 거리를 구해 반환하는 문제이다. 만약 도달할 수 없다면 -1을 반환한다.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- maps는 n x m 크기의 게임 맵의 상태가 들어있는 2차원 배열로, n과 m은 각각 1 이상 100 이하의 자연수입니다.
    - n과 m은 서로 같을 수도, 다를 수도 있지만, n과 m이 모두 1인 경우는 입력으로 주어지지 않습니다.
- maps는 0과 1로만 이루어져 있으며, 0은 벽이 있는 자리, 1은 벽이 없는 자리를 나타냅니다.
- 처음에 캐릭터는 게임 맵의 좌측 상단인 (1, 1) 위치에 있으며, 상대방 진영은 게임 맵의 우측 하단인 (n, m) 위치에 있습니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

전형적인 BFS 연습 문제이다. 간단한 BFS 로직을 작성해서 도착 여부에 따라 이동한 거리 혹은 -1을 반환하도록 로직을 작성하면 쉽게 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

```python
from collections import deque

def solution(maps):
    # 전역 데이터
    W = 0
    E = 1
    n = len(maps)
    m = len(maps[0])
    
    # 메서드
    def bfs():
        dq = deque()
        visited = [[False] * m for _ in range(n)]
        dq.append((0, 0, 1))
        visited[0][0] = True
        min_step = -1
        
        while dq:
            cur_r, cur_c, cur_s = dq.popleft()
            
            if cur_r == n - 1 and cur_c == m - 1:
                min_step = cur_s
                break
            
            for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
                next_r, next_c = cur_r + dr, cur_c + dc
                if not is_safe(next_r, next_c, visited):
                    continue
                
                dq.append((next_r, next_c, cur_s + 1))
                visited[next_r][next_c] = True
        
        return min_step
    
    def is_safe(r, c, visited):
        return 0 <= r < n and 0 <= c < m and not visited[r][c] and maps[r][c] == E
    
    # 메인 로직
    return bfs()
```

---

## 🥰 배운점 & 느낀점

- 쉬운 BFS 문제였다.
- 중간에 시간 초과가 한 번 발생했는데 알고보니 visited 여부를 검사하지 않고 있었다. 이런 잔 실수 때문에 시간을 잡아먹는건 너무 아까우니 주의하자.
