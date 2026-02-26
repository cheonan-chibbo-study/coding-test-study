## 👀 제한 시간 안에 어디까지 해냈는가?

`30분`안에 문제를 풀지 못했다. 15분정도 지났을 때 문제를 어떻게 해결할지 감을 잡아 코드를 작성했는데 코드 작성 시간이 부족했다. 30분 종류 후 시간을 더 사용해 `20분 18초`만에 코드를 완성해서 제출했는데 백준 사이트에 에러가 생겨서 채점도 못하고 작성한 코드가 날아갔다…

- 사실 이 때 작성한 코드에는 논리 오류가 제법 있어서 어차피 통과하지 못했을 것이다.

결국 새로 코드를 작성해서 제출했는데 `출력 오류`가 발생했다. 알고보니 관련 함수에서 리턴을 잘못하고 있어서 수정해 재채점을 진행했더니 최종 정답 처리를 받았다. 작성한 코드는 다음과 같다.

```python
from collections import deque
from itertools import combinations

# 전역 데이터
N, M = map(int, input().split())
input_board = [list(map(int, input().split())) for _ in range(N)]

# 메서드
def get_candi():
    virus_pos = []
    for row in range(N):
        for col in range(N):
            if input_board[row][col] == 2:
                virus_pos.append((row, col))
    
    return list(combinations(virus_pos, M))

def get_new_board(candi):
    new_board = [["E" for _ in range(N)] for _ in range(N)]
    for row in range(N):
        for col in range(N):
            if input_board[row][col] == 1:
                new_board[row][col] = "-"
            elif input_board[row][col] == 2:
                if (row, col) in candi:
                    new_board[row][col] = "V"
                else:
                    new_board[row][col] = "*"
    
    return new_board

def search(board, candi):
    time = [[0 for _ in range(N)] for _ in range(N)]
    dq = deque()
    for row, col in candi:
        dq.append((row, col, 0))
    
    max_time = 0
    while dq:
        cur_r, cur_c, cur_t = dq.popleft()
        
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_r = cur_r + dr
            next_c = cur_c + dc
            next_t = cur_t + 1
            
            if not is_safe(next_r, next_c):
                continue
        
            next_obj = board[next_r][next_c]
            if next_obj == "-" or next_obj == "V":
                continue
            elif next_obj == "E":
                time[next_r][next_c] = next_t
                max_time = max(max_time, next_t)
                board[next_r][next_c] = "V"
                dq.append((next_r, next_c, next_t))
            elif next_obj == "*":
                board[next_r][next_c] = "V"
                dq.append((next_r, next_c, next_t))
    
    for row in board:
        for col in row:
            if col == "E":
                return -1
    return max_time

def is_safe(row, col):
    return row >= 0 and row < N and col >= 0 and col < N

# 메인 로직
candi_list = get_candi()
answer = -1
for candi in candi_list:
    board = get_new_board(candi)
    time = search(board, candi)
    if time != -1:
        answer = min(answer, time) if answer != -1 else time

print(answer)
```

---

## 🧑‍🔬 문제 분석

빈 공간, 벽, 바이러스가 포함된 N x N 크기의 연구실 정보와 활성화 가능한 바이러스 개수 M이 입력으로 주어졌을 때 바이러스를 처음 M개만큼 활성화 시키는 경우의 수 중 가장 바이러스 전파가 빨리 되는 시간을 반환하는 문제이다. 만약 바이러스를 어떠한 경우에도 전파할 수 없다면 -1을 반환한다.

- 참고로 이 문제는 문제 자체에는 설명이 부족하다. 만약 비활성화 바이러스를 만났을 경우 비활성 바이러스가 활성화 되었을 때 시간을 0부터 시작하는게 아니라 이전부터 진행되었던 활성 바이러스의 시간을 그대로 합산해서 가져가야한다.

  [](https://www.acmicpc.net/board/view/128517)


문제에 주어진 제약 조건은 다음과 같다.

첫째 줄에 연구소의 크기 N(4 ≤ N ≤ 50), 놓을 수 있는 바이러스의 개수 M(1 ≤ M ≤ 10)이 주어진다.

둘째 줄부터 N개의 줄에 연구소의 상태가 주어진다. 0은 빈 칸, 1은 벽, 2는 비활성 바이러스의 위치이다. 2의 개수는 M보다 크거나 같고, 10보다 작거나 같은 자연수이다.

---

## 🤔 풀이 고민

### 조합 + BFS

문제를 보자마자 가장 먼저 떠올린 방법이다. 처음 M개의 바이러스를 활성화 시키는 경우의 수를 조합으로 구한 후 각 경우마다 BFS를 사용해 바이러스 전파를 수행하고 전파가 끝난 연구실 상태를 검사하여 빈공간 여부에 따라 최종 시간 or -1을 반환한다.

그리고 위에서 반환한 값들 중 -1이 아닌 경우 중 가장 작은 시간을 반환하거나 혹은 모두 -1의 결과라면 -1을 반환하는 방식으로 문제를 해결할 수 있다.

연구실 크기는 최대 `50 x 50`이므로 매우 작은 크기이고 바이러스도 최대 10개가 주어지면서 M의 범위는 1 ~ 10이기 때문에 나올 수 있는 최대 조합 개수는 `10C5 = 252`이다.

- 참고로 조합은 항상 가운데에서 최대가 된다. 그래서 10의 가운데 값인 5가 가장 많은 조합 개수를 만드는 기준이다.
- 10C5 → 10!/5!5!

252번의 반복 안에서 다시 250번을 매번 반복한다고 가정해도 `63,000`의 작은 횟수의 연산이 수행되므로 충분히 제한 시간안에 문제를 해결할 수 있다.

### 결론

- 조합 + BFS 풀이로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드

이번 문제는 시간이 좀 걸리긴 했지만 스스로 정답 코드를 작성했다. 코드도 딱히 개선할 부분은 없을거 같다.

```python
from collections import deque
from itertools import combinations

# 전역 데이터
N, M = map(int, input().split())
input_board = [list(map(int, input().split())) for _ in range(N)]

# 메서드
def get_candi():
    virus_pos = []
    for row in range(N):
        for col in range(N):
            if input_board[row][col] == 2:
                virus_pos.append((row, col))
    
    return list(combinations(virus_pos, M))

def get_new_board(candi):
    new_board = [["E" for _ in range(N)] for _ in range(N)]
    for row in range(N):
        for col in range(N):
            if input_board[row][col] == 1:
                new_board[row][col] = "-"
            elif input_board[row][col] == 2:
                if (row, col) in candi:
                    new_board[row][col] = "V"
                else:
                    new_board[row][col] = "*"
    
    return new_board

def search(board, candi):
    time = [[0 for _ in range(N)] for _ in range(N)]
    dq = deque()
    for row, col in candi:
        dq.append((row, col, 0))
    
    max_time = 0
    while dq:
        cur_r, cur_c, cur_t = dq.popleft()
        
        for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
            next_r = cur_r + dr
            next_c = cur_c + dc
            next_t = cur_t + 1
            
            if not is_safe(next_r, next_c):
                continue
        
            next_obj = board[next_r][next_c]
            if next_obj == "-" or next_obj == "V":
                continue
            elif next_obj == "E":
                time[next_r][next_c] = next_t
                max_time = max(max_time, next_t)
                board[next_r][next_c] = "V"
                dq.append((next_r, next_c, next_t))
            elif next_obj == "*":
                board[next_r][next_c] = "V"
                dq.append((next_r, next_c, next_t))
    
    for row in board:
        for col in row:
            if col == "E":
                return -1
    return max_time

def is_safe(row, col):
    return row >= 0 and row < N and col >= 0 and col < N

# 메인 로직
candi_list = get_candi()
answer = -1
for candi in candi_list:
    board = get_new_board(candi)
    time = search(board, candi)
    if time != -1:
        answer = min(answer, time) if answer != -1 else time

print(answer)
```

---

## 🥰 배운점 & 느낀점

- 이번 문제는 조합을 구하는 코드를 라이브러리를 사용했더니 빠르게 구현할 수 있었다. 라이브러리를 사용할 수 있는 환경이라면 적극적으로 활용할 필요가 있을거 같다.
- 처음 구현했을 때 논리 실수가 많았다. 코드 작성에 실수가 없도록 꼼꼼하게 검수하면서 작성하는 버릇을 들일 필요가 있다.