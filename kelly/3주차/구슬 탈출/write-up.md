## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분` + 추가 시간 `22분`을 사용했지만 문제를 풀지 못했다. 문제 풀이는 금방 떠올리고 코드도 작성했지만 여러 Error가 발생했고, Error를 해결했지만 최종 오답 처리를 받았다.

풀이 자체는 올바른 방향으로 떠올리고 코드를 작성했지만 코드에 논리 오류가 있어 실패했다. 마지막으로 작성한 코드는 다음과 같다.

```python
from collections import deque

# 전역 데이터
N, M = map(int, input().split())
board = [list(input.split()) for _ in range(N)]
dir = [[-1, 0], [0, 1], [0, -1], [1, 0]]

# 메서드
def get_pos(target):
    for row in range(N):
        for col in range(M):
            if board[row][col] == target:
                return (row, col)

def move(d, start_pos, other_pos):
    cur_r, cur_c = start_pos
    
    while True:
        next_r, next_c = cur_r + d[0], cur_c + d[1]
        if board[next_r][next_c] != "." or (next_r, next_c) == other_pos:
            return (cur_r, cur_c)
        else:
            cur_r, cur_c = next_r, next_c

# 메인 로직
blue_start_pos = get_pos("B")
red_start_pos = get_pos("R")
hole_pos = get_pos("O")
dq = deque([(red_start_pos, blue_start_pos, 0)])

while dq:
    cur_red, cur_blue, cur_move = dq.popleft()
    
    if cur_blue == hole_pos:
        print(0)
        break
    elif cur_red == hole_pos:
        priint(1)
    
    if cur_move == 10:
        continue
    
    for d in range(dir):
        next_red, next_blue = None, None
        # 위로 이동
        if d == 0:
            if cur_red[0] < cur_blue[0]:
                next_red = move(dir[d], cur_red, cur_blue)
                next_blue = move(dir[d], cur_blue, next_red)
            else:
                next_blue = move(dir[d], cur_blue, cur_red)
                next_red = move(dir[d], cur_red, next_blue)
        elif d == 1:
            if cur_red[1] > cur_blue[1]:
                next_red = move(dir[d], cur_red, cur_blue)
                next_blue = move(dir[d], cur_blue, next_red)
            else:
                next_blue = move(dir[d], cur_blue, cur_red)
                next_red = move(dir[d], cur_red, next_blue)
        elif d == 2:
            if cur_red[1] < cur_blue[1]:
                next_red = move(dir[d], cur_red, cur_blue)
                next_blue = move(dir[d], cur_blue, next_red)
            else:
                next_blue = move(dir[d], cur_blue, cur_red)
                next_red = move(dir[d], cur_red, next_blue)
        else:
            if cur_red[0] > cur_blue[0]:
                next_red = move(dir[d], cur_red, cur_blue)
                next_blue = move(dir[d], cur_blue, next_red)
            else:
                next_blue = move(dir[d], cur_blue, cur_red)
                next_red = move(dir[d], cur_red, next_blue)
        
        dq.append((next_red, next_blue, cur_move + 1))
                    
print(0)

```

---

## 🧑‍🔬 문제 분석

보드의 크기 N, M, 보드 정보가 입력되면 보드를 오른쪽/왼쪽/위/아래로 움직일 때 총 10번 이하의 움직임으로 빨간 구슬만 구멍에 넣을 수 있는지 여부를 반환하는 문제이다.

- 자세한 문제 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

**입력**

첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다. 다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다. 이 문자열은 '`.`', '`#`', '`O`', '`R`', '`B`' 로 이루어져 있다. '`.`'은 빈 칸을 의미하고, '`#`'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, '`O`'는 구멍의 위치를 의미한다. '`R`'은 빨간 구슬의 위치, '`B`'는 파란 구슬의 위치이다.

입력되는 모든 보드의 가장자리에는 모두 '`#`'이 있다. 구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.

---

## 🤔 풀이 고민

### BFS

문제를 보자마자 제일 먼저 BFS를 떠올렸다. 총 10번의 움직임 이하로 구슬을 상/하/좌/우로 이동시키면서 빨간 구슬만 구멍에 빠지는 경우가 있는지를 탐색하는 방법이다. 물론 이 문제는 일반적인 BFS 탐색과 다르게 구슬 2개를 어떻게 이동시킬지, 빨간 구슬만 빠지는 경우를 어떻게 찾을지 아이디어도 떠올려야 하는 문제이다.

시간 복잡도상으로 N, M은 10이하로 매우 작은 크기의 격자이기 때문에 BFS 탐색으로 충분히 문제를 해결할 수 있다는 결론을 내렸다.

### 결론

- BFS + 구슬을 이동시키는 아이디어로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 틀린 부분 정정

코드를 혼자 작성해봤지만 Error + 논리 오류가 있었다. 이 부분을 우선 정정해보겠다.

```python
from collections import deque

# 전역 데이터
N, M = map(int, input().split())
board = [list(input.split()) for _ in range(N)]
dir = [[-1, 0], [0, 1], [0, -1], [1, 0]]

# 메서드
def get_pos(target):
    for row in range(N):
        for col in range(M):
            if board[row][col] == target:
                return (row, col)

def move(d, start_pos, other_pos):
    cur_r, cur_c = start_pos
    
    while True:
        next_r, next_c = cur_r + d[0], cur_c + d[1]
        if board[next_r][next_c] != "." or (next_r, next_c) == other_pos:
            return (cur_r, cur_c)
        else:
            cur_r, cur_c = next_r, next_c

# 메인 로직
blue_start_pos = get_pos("B")
red_start_pos = get_pos("R")
hole_pos = get_pos("O")
dq = deque([(red_start_pos, blue_start_pos, 0)])

while dq:
    cur_red, cur_blue, cur_move = dq.popleft()
    
    if cur_blue == hole_pos:
        print(0)
        break
    elif cur_red == hole_pos:
        priint(1)
    
    if cur_move == 10:
        continue
    
    for d in range(dir):
        next_red, next_blue = None, None
        # 위로 이동
        if d == 0:
            if cur_red[0] < cur_blue[0]:
                next_red = move(dir[d], cur_red, cur_blue)
                next_blue = move(dir[d], cur_blue, next_red)
            else:
                next_blue = move(dir[d], cur_blue, cur_red)
                next_red = move(dir[d], cur_red, next_blue)
        elif d == 1:
            if cur_red[1] > cur_blue[1]:
                next_red = move(dir[d], cur_red, cur_blue)
                next_blue = move(dir[d], cur_blue, next_red)
            else:
                next_blue = move(dir[d], cur_blue, cur_red)
                next_red = move(dir[d], cur_red, next_blue)
        elif d == 2:
            if cur_red[1] < cur_blue[1]:
                next_red = move(dir[d], cur_red, cur_blue)
                next_blue = move(dir[d], cur_blue, next_red)
            else:
                next_blue = move(dir[d], cur_blue, cur_red)
                next_red = move(dir[d], cur_red, next_blue)
        else:
            if cur_red[0] > cur_blue[0]:
                next_red = move(dir[d], cur_red, cur_blue)
                next_blue = move(dir[d], cur_blue, next_red)
            else:
                next_blue = move(dir[d], cur_blue, cur_red)
                next_red = move(dir[d], cur_red, next_blue)
        
        dq.append((next_red, next_blue, cur_move + 1))
                    
print(0)

```

- Attribute Error가 발생했는데 이 부분이 문제였다. `board = [list(input.split()) for _ in range(N)]`
- 위 코드는 파싱 자체가 잘못되었다. 현재 격자 정보는 공백없이 문자열 단위로 row가 구성되어 들어오는데 공백으로 구분되어 있지 않은 값을 .split()으로 구분하려고 하니 잘못된 격자 정보가 저장되어서 문제가 발생한다. 따라서 이 코드는 아래와 같이 수정해야한다.
    - `board = [list(input()) for _ in range(N)]`
- move() 함수에서 구멍에 도착했을 때 경우를 전혀 고려하고 있지 않다. `if board[next_r][next_c] != "." or (next_r, next_c) == other_pos:` 이 코드는 구멍에 도달하면 구멍으로 이동하지 않고 그대로 구멍 직전 위치를 반환하기 때문에 이 코드로 인해 평생 구멍에 도달할 수 없는 코드가 나온다. 따라서 구멍에 도달했을 경우 따로 처리할 로직을 작성해줘야한다.
- `elif cur_red == hole_pos: priint(1)` print에 오타가 발생했다.
- `if cur_blue == hole_pos: print(0) break` 특정 케이스에서 파란공이 구멍에 들어간걸 확인하면 그 부분 탐색은 멈추고 다음 탐색을 진행해야하는데 break로 아예 while문을 빠져나가기 때문에 다른 케이스 검사가 되지 않는 문제가 있다.
- `elif cur_red == hole_pos: priint(1)` 이 부분은 빨간 공이 구멍에 들어갔을 때 1을 출력하고 프로그램을 종료하는게 아니라 다시 반복문을 수행하기 때문에 출력 오류가 발생한다.

틀린 부분을 모두 정정해 아래와 같이 코드를 변경해서 제출하니 최종 정답 처리를 받을 수 있었다.

```python
from collections import deque

# 전역 데이터
N, M = map(int, input().split())
board = [list(input()) for _ in range(N)]
DIR = [[-1, 0], [0, 1], [0, -1], [1, 0]]

# 메서드
def get_pos(target):
    for row in range(N):
        for col in range(M):
            if board[row][col] == target:
                return (row, col)

def move(d, start_pos, other_pos):
    cur_r, cur_c = start_pos
    
    while True:
        next_r, next_c = cur_r + d[0], cur_c + d[1]
        if board[next_r][next_c] == "#" or (next_r, next_c) == other_pos:
            return (cur_r, cur_c)
        elif board[next_r][next_c] == "O":
            return (-1, -1)
        else:
            cur_r, cur_c = next_r, next_c

# 메인 로직
blue_start_pos = get_pos("B")
red_start_pos = get_pos("R")
hole_pos = (-1, -1)
dq = deque([(red_start_pos, blue_start_pos, 0)])

answer = 0
while dq:
    cur_red, cur_blue, cur_move = dq.popleft()
    
    if cur_blue == hole_pos:
        continue
    elif cur_red == hole_pos:
        answer = 1
        break
    
    if cur_move == 10:
        continue
    
    for d in range(len(DIR)):
        next_red, next_blue = None, None
        # 위로 이동
        if d == 0:
            if cur_red[0] < cur_blue[0]:
                next_red = move(DIR[d], cur_red, cur_blue)
                next_blue = move(DIR[d], cur_blue, next_red)
            else:
                next_blue = move(DIR[d], cur_blue, cur_red)
                next_red = move(DIR[d], cur_red, next_blue)
        elif d == 1:
            if cur_red[1] > cur_blue[1]:
                next_red = move(DIR[d], cur_red, cur_blue)
                next_blue = move(DIR[d], cur_blue, next_red)
            else:
                next_blue = move(DIR[d], cur_blue, cur_red)
                next_red = move(DIR[d], cur_red, next_blue)
        elif d == 2:
            if cur_red[1] < cur_blue[1]:
                next_red = move(DIR[d], cur_red, cur_blue)
                next_blue = move(DIR[d], cur_blue, next_red)
            else:
                next_blue = move(DIR[d], cur_blue, cur_red)
                next_red = move(DIR[d], cur_red, next_blue)
        else:
            if cur_red[0] > cur_blue[0]:
                next_red = move(DIR[d], cur_red, cur_blue)
                next_blue = move(DIR[d], cur_blue, next_red)
            else:
                next_blue = move(DIR[d], cur_blue, cur_red)
                next_red = move(DIR[d], cur_red, next_blue)
        
        dq.append((next_red, next_blue, cur_move + 1))
                    
print(answer)
```

### 최종 정답 코드 개선

위에 제출한 코드는 정답 처리를 받기는 하지만 코드가 너무 길고 무엇보다 실행 시간이 매우 길게 나온다. 찾아보니 실행 시간을 더 단축시킨 풀이 코드가 있어 참고하여 새롭게 코드를 작성해보았다.

```python
from collections import deque

# 전역 데이터
N, M = map(int, input().split())
board = [list(input()) for _ in range(N)]
DIR = [[-1, 0], [0, 1], [0, -1], [1, 0]]

# 메서드
def move(row, col, dr, dc):
    count = 0
    while board[row + dr][col + dc] != "#" and board[row][col] != "O":
        row += dr
        col += dc
        count += 1
    
    return row, col, count

# 메인 로직
answer = 0
dq = deque()
visited = set()

# 구슬 위치 확인
for row in range(N):
    for col in range(M):
        if board[row][col] == "R":
            rr, rc = row, col
        elif board[row][col] == "B":
            br, bc = row, col

dq.append([rr, rc, br, bc, 1])
visited.add((rr, rc, br, bc))

# 탐색 시작
while dq:
    cur_rr, cur_rc, cur_br, cur_bc, cur_move = dq.popleft()
    if cur_move > 10:
        break
    
    for dr, dc in [[-1,0],[1,0],[0,-1],[0,1]]:
        next_rr, next_rc, r_count = move(cur_rr, cur_rc, dr, dc)
        next_br, next_bc, b_count = move(cur_br, cur_bc, dr, dc)

        # 파란 구슬이 구멍에 들어간 경우
        if board[next_br][next_bc] == "O":
            continue

        # 중복 제거
        if (next_rr, next_rc, next_br, next_bc) in visited:
            continue

        # 빨간 구슬이 들어간 경우 (성공)
        if board[next_rr][next_rc] == "O":
            answer = 1
            break
            
        # 두 구슬의 위치가 같은 경우 더 멀리서 이동된 구슬을 이전 칸으로 이동시켜서 수정해야함
        if (next_rr, next_rc) == (next_br, next_bc):
            if r_count > b_count:
                next_rr -= dr
                next_rc -= dc
            else:
                next_br -= dr
                next_bc -= dc
        
        dq.append([next_rr, next_rc, next_br, next_bc, cur_move + 1])
        visited.add((next_rr, next_rc, next_br, next_bc))
    else:
        continue
    break

print(answer)
```

- 파이썬에서 for, if, while 블록은 **새로운 지역 스코프를 만들지 않는다. for문 “안에서만” 존재하는 게 아니라 현재 함수(또는 전역) 스코프에 그대로 남는다.**
    - 그래서 for문 안에 처음 등장한 rr, rc 등을 for문이 끝나고도 dq에 삽입하는게 가능하다. (처음 알았다.)
- for: …, else: … 문법이 사용되었는데 파이썬은 for문 내부에서 break로 인한 중간 탈출 없이 for문이 모든 루프를 실행하고 종료되었다면 else을 무조건 실행한다고 한다. 반대로 for문 내부에서 break로 인해 중간 탈출이 되었다면 else문은 실행되지 않는다고 한다.
    - 만약 빨간 구술이 들어갔다면 break로 인해 for문이 종료되는데 문제는 그 위 while문 아직 살아있다. 따라서 break로 for문을 탈출 후 else문 실행 없이 바로 break를 실행해 while문도 탈출한다.
    - 만약 파란 구슬이 들어갔다면 for문은 계속 break 없이 무사히 종료되므로 else을 실행하고, else 내부 continue 때문에 while문은 다시 반복된다.
- 기존 내가 직접 작성한 코드는 실행에 `3188ms`나 걸리지만, 새로 작성한 코드는 `72ms`밖에 걸리지 않는다.
    - 기존 작성한 코드는 visited를 사용해 기존 경험한 상태를 다시 탐색하지 않는 로직이 빠져있어서 이미 방문한 상태도 다시 방문하기 때문에 탐색횟수가 굉장히 많아진다.
    - 하지만 새로 작성한 코드는 이미 방문한 로직은 다시 방문하지 않도록 visited 처리를 하였기 때문에 탐색 횟수가 줄어 더 빨리 실행된다.
- 내 기존 코드에 visited 로직을 추가하니 기존 `3,464ms` 에서 `60ms`로 매우 크게 개선되었다! 다만 위에 새로짠 코드의 로직이 훨씬 간단하므로 새로 작성한 코드를 중심으로 학습해야겠다.

---

## 🥰 배운점 & 느낀점

- 이제 어느정도 풀이도 떠올리고 코드 구현도 되는거 같은데 계속 Error + 논리 실수로 원트가 안된다… 이건 연습만이 살길이니 지금까지 작성한 write-up을 복기하며서 실수한 부분을 다시금 복습해야겠다.
- 지금까지 유용한 파이썬 문법을 제법 많이 찾았으니 한 번 정리해서 흩어보고 연습하는 시간을 가질 필요가 있을거 같다.