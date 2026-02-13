## 👀 제한 시간 안에 어디까지 해냈는가?

`30분`안에 문제를 푸는데 실패했다. 문제를 해결할 아이디어를 떠올리고 25분만에 풀이 코드도 작성해서 제출했지만 Runtime error가 발생해서 5분동안 문제를 해결하지 못하고 제한 시간이 종료되었다.

마지막으로 작성한 코드는 다음과 같다.

```python
from collections import deque

# 전역 데이터
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

# 메서드
def get_wall_combi():
    zero_p = []
    for r in range(N):
        for c in range(M):
            if board[r][c] == 0:
                zero_p.append((r, c))
    
    combi = []
    get_combi(combi, zero_p, [], 0)
    
    return combi

def get_combi(combi, zero_p, tmp, start):
    if len(tmp) == 3:
        combi.append(tmp[::])
        return 
    
    for i in range(start, len(zero_p)):
        tmp.append(zero_p[i])
        get_combi(combi, zero_p, tmp, i + 1)
        tmp.pop()

def get_new_board(combi):
    new_board = board[::]
    for c in combi:
        row, col = c
        new_board[row][col] = 1
    
    return new_board

def get_safe_area(board):
    visited = [[False for _ in M] for _ in N]
    dq = deque()
    for r in range(N):
        for c in range(M):
            if board[r][c] == 2:
                dq.append((r, c))
                visited[r][c] = True
    
    while dq:
        cur_r, cur_c = dq.popleft()
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_r = cur_r + dr
            next_c = cur_c + dc
            next_o = board[next_r][next_c]
            
            if not is_safe(next_r, next_c) or next_o == 1 or next_o == 2 or visited[next_r][next_c]:
                continue
            else:
                board[next_r][next_c] = 2
                visited[next_r][next_c] = True
                dq.append((next_r, next_c))
    
    safe_area_count = 0
    for r in board:
        for c in r:
            if c == 0:
                safe_area_count += 1
    
    return safe_area_count

def is_safe(r, c):
    return r >= 0 and r < N and c >= 0 and c < M

# 메인 로직
answer = -1
wall_combi = get_wall_combi()
for combi in wall_combi:
    answer = max(answer, get_safe_area(get_new_board(combi)))

print(answer)
```

---

## 🧑‍🔬 문제 분석

격자 형태의 연구소 정보가 주어졌을 때, 빈 공간에 3개의 벽을 세우는 모든 경우의 수 중 바이러스가 퍼졌을 때 안전 구역이 가장 많이 남는 개수를 구해 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

- 첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)
- 둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.
- 빈 칸의 개수는 3개 이상이다.

---

## 🤔 풀이 고민

### 조합 + BFS/DFS

이 문제는 조합과 BFS/DFS를 활용해 풀 수 있다. 우선 벽을 세울 수 있는 모든 경우의 수를 구한 다음 각 경우마다 BFS/DFS를 수행하여 바이러스가 모두 퍼졌을 때 남은 안전 구역 개수를 계산해 이 중 가장 큰 안전 구역 개수를 찾으면 해결할 수 있다.

문제에 주어지는 배열의 크기가 최대 8 x 8로 매우 작기 때문에 매번 배열을 깊은 복사하거나 BFS/DFS를 수행해도 크게 문제가 없음을 직감했다.

### 결론

- 조합 + BFS/DFS 풀이로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 틀린 부분 정정

제한 시간내 마지막으로 작성한 코드는 다음과 같은데 문법이나 Index적으로 틀린 부분이 몇개 있었다.

```python
from collections import deque

# 전역 데이터
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

# 메서드
def get_wall_combi():
    zero_p = []
    for r in range(N):
        for c in range(M):
            if board[r][c] == 0:
                zero_p.append((r, c))
    
    combi = []
    get_combi(combi, zero_p, [], 0)
    
    return combi

def get_combi(combi, zero_p, tmp, start):
    if len(tmp) == 3:
        combi.append(tmp[::])
        return 
    
    for i in range(start, len(zero_p)):
        tmp.append(zero_p[i])
        get_combi(combi, zero_p, tmp, i + 1)
        tmp.pop()

def get_new_board(combi):
    new_board = board[::]
    for c in combi:
        row, col = c
        new_board[row][col] = 1
    
    return new_board

def get_safe_area(board):
    visited = [[False for _ in M] for _ in N]
    dq = deque()
    for r in range(N):
        for c in range(M):
            if board[r][c] == 2:
                dq.append((r, c))
                visited[r][c] = True
    
    while dq:
        cur_r, cur_c = dq.popleft()
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_r = cur_r + dr
            next_c = cur_c + dc
            next_o = board[next_r][next_c]
            
            if not is_safe(next_r, next_c) or next_o == 1 or next_o == 2 or visited[next_r][next_c]:
                continue
            else:
                board[next_r][next_c] = 2
                visited[next_r][next_c] = True
                dq.append((next_r, next_c))
    
    safe_area_count = 0
    for r in board:
        for c in r:
            if c == 0:
                safe_area_count += 1
    
    return safe_area_count

def is_safe(r, c):
    return r >= 0 and r < N and c >= 0 and c < M

# 메인 로직
answer = -1
wall_combi = get_wall_combi()
for combi in wall_combi:
    answer = max(answer, get_safe_area(get_new_board(combi)))

print(answer)
```

- `visited = [[False for _ in M] for _ in N]` 를 `visited = [[False for _ in range(M)] for _ in range(N)]` 로 수정해야한다.
- `next_o = board[next_r][next_c]` 로직이 `is_safe()` 로직보다 먼저 실행되고 있어 Index Error가 발생할 수 있다. 순서를 변경해야한다.
- `new_board = board[::]` 이건 얕은 복사라서 2차원 리스트의 얕은 복사는 내부 리스트까지 복사되지 않는다. 따라서 깊은 복사 로직으로 변경해야한다. → `new_board = [row[:] for row in board]`

최종적으로 수정한 코드는 다음과 같다. 이 코드는 최종 정답 처리를 받는다.

```python
from collections import deque

# 전역 데이터
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

# 메서드
def get_wall_combi():
    zero_p = []
    for r in range(N):
        for c in range(M):
            if board[r][c] == 0:
                zero_p.append((r, c))
    
    combi = []
    get_combi(combi, zero_p, [], 0)
    
    return combi

def get_combi(combi, zero_p, tmp, start):
    if len(tmp) == 3:
        combi.append(tmp[::])
        return 
    
    for i in range(start, len(zero_p)):
        tmp.append(zero_p[i])
        get_combi(combi, zero_p, tmp, i + 1)
        tmp.pop()

def get_new_board(combi):
    new_board = [row[::] for row in board]
    for c in combi:
        row, col = c
        new_board[row][col] = 1
    
    return new_board

def get_safe_area(board):
    visited = [[False for _ in range(M)] for _ in range(N)]
    dq = deque()
    for r in range(N):
        for c in range(M):
            if board[r][c] == 2:
                dq.append((r, c))
                visited[r][c] = True
    
    while dq:
        cur_r, cur_c = dq.popleft()
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_r = cur_r + dr
            next_c = cur_c + dc
            
            if not is_safe(next_r, next_c):
                continue
                
            next_o = board[next_r][next_c]
            if next_o == 1 or next_o == 2 or visited[next_r][next_c]:
                continue
            else:
                board[next_r][next_c] = 2
                visited[next_r][next_c] = True
                dq.append((next_r, next_c))
    
    safe_area_count = 0
    for r in board:
        for c in r:
            if c == 0:
                safe_area_count += 1
    
    return safe_area_count

def is_safe(r, c):
    return r >= 0 and r < N and c >= 0 and c < M

# 메인 로직
answer = -1
wall_combi = get_wall_combi()
for combi in wall_combi:
    answer = max(answer, get_safe_area(get_new_board(combi)))

print(answer)
```

### 최종 정답 코드 개선

위 로직에서 벽을 세울 조합을 구하는 코드와 깊은 복사 코드는 만약 파이썬 라이브러리를 사용할 수 있다면 다음과 같이 개선할 수 있다.

```python
from itertools import combinations
from copy import deepcopy
from collections import deque

# 전역 데이터
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

# 메서드
def get_wall_combi():
    zero_p = []
    for r in range(N):
        for c in range(M):
            if board[r][c] == 0:
                zero_p.append((r, c))
    
    return list(combinations(zero_p, 3))

def get_new_board(combi):
    new_board = deepcopy(board)
    for c in combi:
        row, col = c
        new_board[row][col] = 1
    
    return new_board

def get_safe_area(board):
    visited = [[False for _ in range(M)] for _ in range(N)]
    dq = deque()
    for r in range(N):
        for c in range(M):
            if board[r][c] == 2:
                dq.append((r, c))
                visited[r][c] = True
    
    while dq:
        cur_r, cur_c = dq.popleft()
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_r = cur_r + dr
            next_c = cur_c + dc
            
            if not is_safe(next_r, next_c):
                continue
                
            next_o = board[next_r][next_c]
            if next_o == 1 or next_o == 2 or visited[next_r][next_c]:
                continue
            else:
                board[next_r][next_c] = 2
                visited[next_r][next_c] = True
                dq.append((next_r, next_c))
    
    safe_area_count = 0
    for r in board:
        for c in r:
            if c == 0:
                safe_area_count += 1
    
    return safe_area_count

def is_safe(r, c):
    return r >= 0 and r < N and c >= 0 and c < M

# 메인 로직
answer = -1
wall_combi = get_wall_combi()
for combi in wall_combi:
    answer = max(answer, get_safe_area(get_new_board(combi)))

print(answer)
```

---

## 🥰 배운점 & 느낀점

- 이번 문제는 30분안에 올바른 풀이법도 빠르게 떠올리고 코드 작성도 했는데 문법 오류와 로직 실수로 실패한게 너무 뼈아프다. 이런 실수를 하지 않도록 연습을 많이 해야겠다.
- 조합을 구하는 로직을 직접 작성하면 시간이 제법 소요되므로 일단 관련 라이브러리를 먼저 사용해보고 만약 라이브러리 사용이 안되면 직접 작성하는 방식으로 접근해서 아낄 수 있는 시간은 아끼는게 중요한거 같다.