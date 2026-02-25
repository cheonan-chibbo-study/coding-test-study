## 👀 제한 시간 안에 어디까지 해냈는가?

`30분 + 8분 58초`를 사용해 코드를 작성했지만 결국 테스트 케이스도 통과하지 못했다. 문제를 어떻게 풀 수 있을지 풀이법은 떠올렸지만 그 풀이를 제대로 구현하지 못했다. (구현 너무 어렵다…) 마지막으로 작성한 코드는 다음과 같다.

```python
# 30분 + 8분 58초를 사용했지만 테스트 케이스도 통과하지 못했다.

from collections import deque

def solution(board):
    # 전역 데이터
    move = [[-1, 0, -1, 0], [1, 0, 1, 0], [0, 1, 0, 1], [0, -1, 0, -1]]
    rot = [[[1, 1, 0, 0, 1, 0], [-1, 1, 0, 0, -1, -0], [0, 0, -1, -1, 1, 0], [0, 0, 1, -1, -1, 0]], [[1, -1, 0, 0, 0, -1], [1, 1, 0, 0, 0, 1], [0, 0, -1, -1, 0, -1], [0, 0, -1, 1, 0, 1]]]
    N = len(board)
    
    # 메서드
    def is_safe(r, c):
        return r >= 0 and r < N and c >= 0 and c < N and board[r][c] == 0
    
    def is_arrive(r, c):
        return r == N - 1 and c == N - 1
    
    # 메인 로직
    visited = []
    dq = deque([[[0, 0], [0, 1], 0, 0]])
    visited.append([[0, 0], [0, 1]])
    
    while dq:
        cur_a, cur_b, cur_rot, cur_m = dq.popleft()
        if is_arrive(cur_a[0], cur_a[1]) or is_arrive(cur_b[0], cur_b[1]):
            return cur_m
        
        for m in move:
            next_a_r = cur_a[0] + m[0]
            next_a_c = cur_a[1] + m[1]
            next_b_r = cur_b[0] + m[2]
            next_b_c = cur_b[1] + m[3]
            
            if not is_safe(next_a_r, next_a_c) or not is_safe(next_b_r, next_b_c) or [[next_a_r, next_a_c], [next_b_r, next_b_c]] in visited:
                continue
            else:
                dq.append([[next_a_r, next_a_c], [next_b_r, next_b_c], cur_rot, cur_m + 1])
                visited.append([[next_a_r, next_a_c], [next_b_r, next_b_c]])
            
        for ro in rot[cur_rot]:
            next_a_r = cur_a[0] + ro[0]
            next_a_c = cur_a[1] + ro[1]
            next_b_r = cur_b[0] + ro[2]
            next_b_c = cur_b[1] + ro[3]
            next_rot = 0 if cur_rot == 1 else 1
            
            if not is_safe(cur_a[0] + ro[4], cur_a[1] + ro[5]) or not is_safe(cur_b[0] + ro[4], cur_b[1] + ro[5]) or [[next_a_r, next_a_c], [next_b_r, next_b_c]] in visited:
                continue
            else:
                dq.append([[next_a_r, next_a_c], [next_b_r, next_b_c], next_rot, cur_m + 1])
                visited.append([[next_a_r, next_a_c], [next_b_r, next_b_c]])
            
```

---

## 🧑‍🔬 문제 분석

N x N 크기의 격자 정보가 주어졌을 때 2 x 1크기의 로봇이 이동, 회전을 반복해 (1, 1) 위치에서 (N, N) 위치까지 두 몸통 중 하나라도 도달할 수 있는 최단 거리를 구해 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

### **제한사항**

- board의 한 변의 길이는 5 이상 100 이하입니다.
- board의 원소는 0 또는 1입니다.
- 로봇이 처음에 놓여 있는 칸 (1, 1), (1, 2)는 항상 0으로 주어집니다.
- 로봇이 항상 목적지에 도착할 수 있는 경우만 입력으로 주어집니다.

---

## 🤔 풀이 고민

### BFS

문제 풀이법은 문제를 보자마자 바로 떠올렸다. 로봇이 목적지에 도착할 수 있는 최단 거리를 구하는 문제이므로 BFS를 활용해 문제를 풀 수 있다. 로봇이 4방향으로 이동하거나 회전하는 경우의 수를 모두 탐색하면서 제일 처음 목적지에 닿았을 때 걸린 시간이 정답이다.

문제에 주어지는 격자 크기도 100 이하로 매우 작기 때문에 BFS 같은 완탐 풀이로 문제를 충분히 해결할 수 있다.

### 결론

- BFS 풀이로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 구현하지 못한 부분 구현

문제 풀이는 쉽게 떠올렸지만 그 아이디어를 코드로 구현하는게 너무 어려웠다. 결국 추가 시간을 사용했음에도 불구하고 문제를 해결하지 못했다.

풀이 코드는 위에 설명한것 처럼 로봇이 BFS를 하며 4방향 이동 + 회전의 경우의 수를 하나씩 탐색해 가장 먼저 목적지에 도달한 케이스의 횟수를 반환하는 코드를 작성하면 된다. 이 아이디어를 코드로 구현하면 다음과 같다.

```python
from collections import deque

def solution(board):
    # 전역 데이터
    N = len(board)
    new_board = [[1 for _ in range(N + 2)] for _ in range(N + 2)]
    for r in range(N):
        for c in range(N):
            new_board[r + 1][c + 1] = board[r][c]
    
    # 메서드
    def get_next_p(p):
        next_p_list = []
        r1, c1 = p[0]
        r2, c2 = p[1]
        
        dir = [[-1, 0], [0, 1], [0, -1], [1, 0]]
        for dr, dc in dir:
            next_r1 = r1 + dr
            next_c1 = c1 + dc
            next_r2 = r2 + dr
            next_c2 = c2 + dc
            
            if new_board[next_r1][next_c1] == 0 and new_board[next_r2][next_c2] == 0:
                next_p_list.append(((next_r1, next_c1), (next_r2, next_c2)))
        
        # 현재 가로 상태
        if r1 == r2:
            if new_board[r1 - 1][c1] == 0 and new_board[r2 - 1][c2] == 0:
                next_p_list.append(((r2 - 1, c2), (r2, c2)))
                next_p_list.append(((r1, c1), (r1 - 1, c1)))
            
            if new_board[r1 + 1][c1] == 0 and new_board[r2 + 1][c2] == 0:
                next_p_list.append(((r2 + 1, c2), (r2, c2)))
                next_p_list.append(((r1, c1), (r1 + 1, c1)))
        
        if c1 == c2:
            if new_board[r1][c1 - 1] == 0 and new_board[r2][c2 - 1] == 0:
                next_p_list.append(((r2, c2 - 1), (r2, c2)))
                next_p_list.append(((r1, c1), (r1, c1 - 1)))
            if new_board[r1][c1 + 1] == 0 and new_board[r2][c2 + 1] == 0:
                next_p_list.append(((r2, c2 + 1), (r2, c2)))
                next_p_list.append(((r1, c1), (r1, c1 + 1)))
        
        return next_p_list
    
    # 메인 로직
    visited = set()
    dq = deque()
    
    start_p = ((1, 1), (1, 2))
    start_t = 0
    dq.append((start_p, start_t))
    visited.add(start_p)
    
    while dq:
        cur_p, cur_t = dq.popleft()
        if (N, N) in cur_p:
            return cur_t
        
        for next_p in get_next_p(cur_p):
            if next_p in visited:
                continue
            
            dq.append((next_p, cur_t + 1))
            visited.add(next_p)
    
    return -1
```

어떻게 구현할지 궁금했던 부분이 이 코드에 다 녹아져 있으니 쓸만한 테크닉을 많이 뽑아서 흡수해야겠다.

---

## 🥰 배운점 & 느낀점

- 구현 연습은.. 진짜 연습이 살길이다. 빡세게 하자.