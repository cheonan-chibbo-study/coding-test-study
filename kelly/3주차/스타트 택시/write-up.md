## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분` + 추가 시간 30분을 더 사용했는데 코드를 완성하지 못했고, 겨우 작성한 코드도 제출하자마자 에러가 발생했다.

이번 문제는 풀이를 금방 떠올렸지만 그 풀이를 구현하는 과정이 너무 오래걸렸고 중간에 문법을 몰라 실수하고 틀린 부분이 많았다. 결국 GPT에게 부탁해 기존 코드에서 잘못된 부분이 몇개 고치고 나서야 최종 정답 처리를 받을 수 있었다.

혼자서 작성한 코드는 다음과 같다.

```python
from collections import deque

# 전역 데이터
N, M, oil = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
cur_t_row, cur_t_col = map(int, input().split())
client = [turple(map(int, input().split())) for _ in range(M)]

# 메서드
def get_next_client():
    candi = []
    candi
    minimum_dis = -1
    visited = [[False for _ in range(N)] for _ in range(N)]
    dq = deque()
    dq.append((cur_t_row, cur_t_col, 0))
    visited[cur_t_row][cur_t_col] = True
    
    while dq:
        cur_row, cur_col, cur_dis = dq.popleft()
        for c in range(client):
            if [cur_row, cur_col] in c:
                if minimum_dis == -1:
                    candi.append(c)
                    minimum_dis = cur_dis
                elif minimum_dis > cur_dis:
                    candi = [c]
                    minimum_dis = cur_dis
                elif minimum_dis == cur_dis:
                    candi.append(c)
        
        if minimum_dis != -1:
            continue
        
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_row = cur_row + dr
            next_col = cur_col + dc
            next_dis = cur_dis + 1
            
            if not is_safe(next_row, next_col) or board[next_row][next_col] == 1 or visited[next_row][next_col] or next_dis > oil:
                continue
            
            dq.append((next_row, next_col, next_dis))
            visited[next_row][next_col] = True

    if not candi:
        return [None, None]
    
    candi.sort(key = lambda x: (x[0], x[1]))
    return [candi[0], minimum_dis]

def move_client_des(s_row, s_col, d_row, d_col):
    visited = [[False for _ in range(N)] for _ in range(N)]
    dq = deque()
    dq.append((s_row, s_col, 0))
    visited[s_row][s_col] = True
    
    while dq:
        cur_row, cur_col, cur_dis = dq.popleft()
        
        if cur_row == d_row and cur_col == d_col:
            return [True, cur_dis]
        
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_row = cur_row + dr
            next_col = cur_col + dc
            next_dis = cur_dis + 1
            
            if not is_safe(next_row, next_col) or board[next_row][next_col] == 1 or visited[next_row][next_col] or oil < next_dis:
                continue
            
            dq.append((next_row, next_col, next_dis))
            visited[next_row][next_col] = True

    return [False, -1]

def is_safe(r, c):
    return r >= 0 and r <= N and c >= 0 and c < N

# 메인 로직
while client:
    next_client, distance = get_next_client()
    if next_client is None:
        oil = -1
        break
    else:
        oil -= distance
        cur_t_row = next_client[0]
        cur_t_col = next_client[1]
    
    is_success, distance = move_client_des(*next_client)
    if not is_success:
        oil = -1
        break
    else:
        oil -= distance
        cur_t_row = next_client[2]
        cur_t_col = next_client[3]
        client.pop(next_client)

print(oil)
```

틀린 부분은 아래 `코드 작성 과정`에서 다루겠다.

---

## 🧑‍🔬 문제 분석

격자 크기 N, 태울 손님 인원 수 M, 현재 연로 상태, 격자 정보, 현재 택시 위치, 현재 승객들의 위치와 목적지 정보가 주어졌을 때 택시가 최단 거리로 손님을 태우고, 목적지에 도착하는 과정을 모두 마쳤을 때 연료 상태를 반환하는 문제이다.

- 만약 연로 부족 혹은 다른 이유로 모든 손님을 목적지에 보내지 못한다면 -1을 반환한다.
- 자세한 문제 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

첫 줄에 N, M, 그리고 초기 연료의 양이 주어진다. (2 ≤ N ≤ 20, 1 ≤ M ≤ N^2, 1 ≤ 초기 연료 ≤ 500,000) 연료는 무한히 많이 담을 수 있기 때문에, 초기 연료의 양을 넘어서 충전될 수도 있다.

다음 줄부터 N개의 줄에 걸쳐 백준이 활동할 영역의 지도가 주어진다. 0은 빈칸, 1은 벽을 나타낸다.

다음 줄에는 백준이 운전을 시작하는 칸의 행 번호와 열 번호가 주어진다. 행과 열 번호는 1 이상 N 이하의 자연수이고, 운전을 시작하는 칸은 빈칸이다.

그다음 줄부터 M개의 줄에 걸쳐 각 승객의 출발지의 행과 열 번호, 그리고 목적지의 행과 열 번호가 주어진다. 모든 출발지와 목적지는 빈칸이고, 모든 출발지는 서로 다르며, 각 손님의 출발지와 목적지는 다르다.

---

## 🤔 풀이 고민

### BFS

문제를 어떻게 풀 수 있을지는 문제를 보자마자 떠올릴 수 있었다. 현재 택시 위치에서 가장 최단 거리로 갈 수 있는 손님에게 가서 그 손님을 최단 거리로 목적지에 보내주는 작업을 반복하면서 중간중간 택시의 연료 상태를 파악하여 만약 한번이라도 연료가 부족해지는 순간이 오면 -1, 모든 손님을 목적지에 데려다 주었다면 현재 연료 상태를 반환하는 방식으로 코드를 작성하면 된다. 즉 이 과정을 나누어 각각 BFS를 활용해 풀면 된다.

문제에 까다로운 부분은 최단 거리가 같은 손님들이 여러명 있을 경우 row가 가장 작거나, 아니면 col이 가장 작은 손님을 선택하는 부분인데 이 부부은 우선 같은 최단 거리의 손님 후보를 모으고 이 후보를 정렬해서 첫 번째 요소를 취하는 방식으로 해결하면 되겠다고 생각했다.

문제에 주어지는 격자의 크기도 최대 20이므로 격자의 공간은 최대 400이고, 손님도 최대 400명이므로 시간복잡도를 대충 계산해보면

- 400 x (400 + 400) = `320,000`

연산 횟수가 그렇게 크지 않아 각 부분을 BFS로 구현해도 충분히 문제를 해결할 수 있겠다는 생각이 들었다.

### 결론

- BFS + 조합 방식으로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 틀린 부분 정정

혼자 작성한 코드에는 틀린 부분이 굉장히 많다. 아래 코드를 참고해서 틀린 부분을 정정해보겠다.

```python
from collections import deque

# 전역 데이터
N, M, oil = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
cur_t_row, cur_t_col = map(int, input().split())
client = [turple(map(int, input().split())) for _ in range(M)]

# 메서드
def get_next_client():
    candi = []
    candi
    minimum_dis = -1
    visited = [[False for _ in range(N)] for _ in range(N)]
    dq = deque()
    dq.append((cur_t_row, cur_t_col, 0))
    visited[cur_t_row][cur_t_col] = True
    
    while dq:
        cur_row, cur_col, cur_dis = dq.popleft()
        for c in range(client):
            if [cur_row, cur_col] in c:
                if minimum_dis == -1:
                    candi.append(c)
                    minimum_dis = cur_dis
                elif minimum_dis > cur_dis:
                    candi = [c]
                    minimum_dis = cur_dis
                elif minimum_dis == cur_dis:
                    candi.append(c)
        
        if minimum_dis != -1:
            continue
        
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_row = cur_row + dr
            next_col = cur_col + dc
            next_dis = cur_dis + 1
            
            if not is_safe(next_row, next_col) or board[next_row][next_col] == 1 or visited[next_row][next_col] or next_dis > oil:
                continue
            
            dq.append((next_row, next_col, next_dis))
            visited[next_row][next_col] = True

    if not candi:
        return [None, None]
    
    candi.sort(key = lambda x: (x[0], x[1]))
    return [candi[0], minimum_dis]

def move_client_des(s_row, s_col, d_row, d_col):
    visited = [[False for _ in range(N)] for _ in range(N)]
    dq = deque()
    dq.append((s_row, s_col, 0))
    visited[s_row][s_col] = True
    
    while dq:
        cur_row, cur_col, cur_dis = dq.popleft()
        
        if cur_row == d_row and cur_col == d_col:
            return [True, cur_dis]
        
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_row = cur_row + dr
            next_col = cur_col + dc
            next_dis = cur_dis + 1
            
            if not is_safe(next_row, next_col) or board[next_row][next_col] == 1 or visited[next_row][next_col] or oil < next_dis:
                continue
            
            dq.append((next_row, next_col, next_dis))
            visited[next_row][next_col] = True

    return [False, -1]

def is_safe(r, c):
    return r >= 0 and r <= N and c >= 0 and c < N

# 메인 로직
while client:
    next_client, distance = get_next_client()
    if next_client is None:
        oil = -1
        break
    else:
        oil -= distance
        cur_t_row = next_client[0]
        cur_t_col = next_client[1]
    
    is_success, distance = move_client_des(*next_client)
    if not is_success:
        oil = -1
        break
    else:
        oil -= distance
        cur_t_row = next_client[2]
        cur_t_col = next_client[3]
        client.pop(next_client)

print(oil)
```

- `client = [turple(map(int, input().split())) for _ in range(M)]`은 잘못된 문법이다.
    - 그래서 난 그냥 `list()`로 수정했다.
    - 올바른 코드는 `client = [tuple(map(int, input().split())) for _ in range(M)]` → tuple이다!
- 우선 같은 최단 거리의 손님중 row/col이 가장 작은 손님을 찾기 위해 정렬을 하고 싶었는데 정렬 문법을 떠올리지 못해 결국 GPT한테 물어봤따.. 정렬은 다음과 같이 할 수 있다.
    - `candi.sort(key = lambda x: (x[0], x[1]))`
    - arr = [[2, 2, 5, 6], [5, 4, 1, 6], [4, 2, 3, 5]] 이렇게 있을 경우 arr 각 요소의 [0]과 [1]을 기준으로 오름차순한다.
    - 만약 내림 차순으로 하고 싶다면 `candi.sort(key=lambda x: (x[0], x[1]), reverse=True)`
- `move_client_des(next_client)` 이렇게 메서드 호출에 컬렉션을 줬을 때 메서드 내부에서는 값을 `def move_client_des(s_row, s_col, d_row, d_col):` 분리해서 받길 원한다면
    - `move_client_des(*next_client)` 이렇게 *을 붙여 언패킹 할 수 있다.
- Type Error가 발생했다. 원인은 리스트 요소를 순회하고 싶었는데 `range()`를 사용하고 있다.
    - `for c in range(client):`
- Index Error가 발생하고 있다. 이유는 is_safe()의…
    - `return r >= 0 and r <= N and c >= 0 and c < N`  ≤ N…
- 승객 위치 판별 로직이 잘못되었다. 문제를 자세히 살펴보면 입력되는 모든 택시, 승객의 위치 정보는 1부터 시작한다. 문제는 내 코드는 0부터 시작하는 인덱스를 기준으로 택시와 손님의 위치 정보를 다룰려고 해서 실패했다.
    - 진짜 악질이다 이건…
- 그리고 승객 위치 판별에 `if [cur_row, cur_col] in c:` 이 코드는 사용할 수 없다고 한다.
    - 그래서 `if cur_row == c[0] and cur_col == c[1]:` 이렇게 변경했다.
- client에서 특정 요소를 제거하고 싶은데 `pop()`을 사용해서 error가 발생한다. pop()은 인덱스만 받는다. 만약 특정 요소를 제거하고 싶다면.
    - `client.remove(next_client)`
- 연료 처리 로직이 틀렸다. 손님을 목적지에 데려다 주는데 성공했다면 사용한 연료의 두배를 충전해야한다. 지금 내 코드는 승객을 목적지에 데려다 줬을 경우 `oil -= distance` 이렇게 연료만 빼고 있다.
    - 연료 2배 충전을 위해 이렇게 코드를 수정해야한다. `oil += distance`

틀린 부분을 모두 반영해 작성한 최종 코드는 다음과 같다. 이 코드를 최종 정답 처리를 받았다.

```python
from collections import deque

# 전역 데이터
N, M, oil = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
cur_t_row, cur_t_col = map(int, input().split())
client = [list(map(int, input().split())) for _ in range(M)]
cur_t_row -= 1
cur_t_col -= 1

for c in client:
    c[0] -= 1
    c[1] -= 1
    c[2] -= 1
    c[3] -= 1

# 메서드
def get_next_client():
    candi = []
    minimum_dis = -1
    visited = [[False for _ in range(N)] for _ in range(N)]
    dq = deque()
    dq.append((cur_t_row, cur_t_col, 0))
    visited[cur_t_row][cur_t_col] = True
    
    while dq:
        cur_row, cur_col, cur_dis = dq.popleft()
        for c in client:
            if cur_row == c[0] and cur_col == c[1]:
                if minimum_dis == -1:
                    candi.append(c)
                    minimum_dis = cur_dis
                elif minimum_dis > cur_dis:
                    candi = [c]
                    minimum_dis = cur_dis
                elif minimum_dis == cur_dis:
                    candi.append(c)
        
        if minimum_dis != -1:
            continue
        
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_row = cur_row + dr
            next_col = cur_col + dc
            next_dis = cur_dis + 1
            
            if not is_safe(next_row, next_col) or board[next_row][next_col] == 1 or visited[next_row][next_col] or next_dis > oil:
                continue
            
            dq.append((next_row, next_col, next_dis))
            visited[next_row][next_col] = True

    if not candi:
        return [None, None]
    
    candi.sort(key = lambda x: (x[0], x[1]))
    return [candi[0], minimum_dis]

def move_client_des(s_row, s_col, d_row, d_col):
    visited = [[False for _ in range(N)] for _ in range(N)]
    dq = deque()
    dq.append((s_row, s_col, 0))
    visited[s_row][s_col] = True
    
    while dq:
        cur_row, cur_col, cur_dis = dq.popleft()
        
        if cur_row == d_row and cur_col == d_col:
            return [True, cur_dis]
        
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_row = cur_row + dr
            next_col = cur_col + dc
            next_dis = cur_dis + 1
            
            if not is_safe(next_row, next_col) or board[next_row][next_col] == 1 or visited[next_row][next_col] or oil < next_dis:
                continue
            
            dq.append((next_row, next_col, next_dis))
            visited[next_row][next_col] = True

    return [False, -1]

def is_safe(r, c):
    return r >= 0 and r < N and c >= 0 and c < N

# 메인 로직
while client:
    next_client, distance = get_next_client()
    if next_client is None:
        oil = -1
        break
    else:
        oil -= distance
        cur_t_row = next_client[0]
        cur_t_col = next_client[1]
    
    is_success, distance = move_client_des(*next_client)
    if not is_success:
        oil = -1
        break
    else:
        oil += distance
        cur_t_row = next_client[2]
        cur_t_col = next_client[3]
        client.remove(next_client)

print(oil)
```

### 최종 정답 코드 개선

GPT한테 부탁한 코드가 계속 오답을 받아서 나중에 내가 직접 코드를 개선봐야 할 거 같다.

---

## 🥰 배운점 & 느낀점

- 구현은… 진짜 연습만이 살길이고 늘 강조하지만 실수를 줄이는게 핵심이다.
- 위에 코드 틀린부분을 적어놓은 부분을 복습하면서 유용한 코드 지식도 복습하자.