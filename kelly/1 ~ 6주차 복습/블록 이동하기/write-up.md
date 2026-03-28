## 👻 재시도 기록

### 재시도 1회

제한 시간 `30분` + 추가 시간 `9분 25초`만에 혼자서 문제를 푸는데 성공했다.

사실 최초 제출 때 테스트 케이스는 바로 통과했는데 최종 제출에서 시간 초과가 발생하는 케이스들이 있어 살펴보니 다음 경로로 이동할 때 visited 처리 하는 로직이 누락되어 있었다…

- 다행이 이 로직만 추가해주니 바로 정답 처리가 되었다.

코드 자체는 이전에 기록한 코드가 더 효율적인거 같다. 그래도 혼자서 문제를 해결할 수 있어 기분이 좋다.

아래는 재시도 1회 때 실제 작성한 코드이다.

```python
from collections import deque

def solution(board):
    # 전역 데이터
    DIR = [[-1, 0], [0, 1], [0, -1], [1, 0]]
    
    # 메서드
    def is_safe(r, c):
        return 0 <= r < N and 0 <= c < N and board[r][c] == 0
    
    def get_rotated(p1, p2):
        rotated = []
        
        if p1[0] == p2[0]:
            if is_safe(p1[0] - 1, p1[1]) and is_safe(p2[0] - 1, p2[1]):
                rotated.append(((p2[0] - 1, p2[1]), p2))
                rotated.append((p1, (p1[0] - 1, p1[1])))
            
            if is_safe(p1[0] + 1, p1[1]) and is_safe(p2[0] + 1, p2[1]):
                rotated.append(((p2[0] + 1, p2[1]), p2))
                rotated.append((p1, (p1[0] + 1, p1[1])))
        
        if p1[1] == p2[1]:
            if is_safe(p1[0], p1[1] + 1) and is_safe(p2[0], p2[1] + 1):
                rotated.append((p1, (p1[0], p1[1] + 1)))
                rotated.append(((p2[0], p2[1] + 1), p2))
            
            if is_safe(p1[0], p1[1] - 1) and is_safe(p2[0], p2[1] - 1):
                rotated.append((p1, (p1[0], p1[1] - 1)))
                rotated.append(((p2[0], p2[1] - 1), p2))
        
        return rotated
    
    # 메인 로직
    N = len(board)
    desti = (N - 1, N - 1)
    
    visited = {((0, 0), (0, 1))}
    dq = deque([((0, 0), (0, 1), 0)])
    
    while dq:
        cur_p1, cur_p2, cur_m = dq.popleft()
        if desti == cur_p1 or desti == cur_p2:
            return cur_m
        
        for dx, dy in DIR:
            next_p1_r, next_p1_c = cur_p1[0] + dx, cur_p1[1] + dy
            next_p2_r, next_p2_c = cur_p2[0] + dx, cur_p2[1] + dy
            
            if not is_safe(next_p1_r, next_p1_c) or not is_safe(next_p2_r, next_p2_c):
                continue
            
            next_p1 = (next_p1_r, next_p1_c)
            next_p2 = (next_p2_r, next_p2_c)
            if tuple(sorted((next_p1, next_p2))) in visited:
                continue
            
            dq.append((next_p1, next_p2, cur_m + 1))
            visited.add((next_p1, next_p2))
        
        for next_p1, next_p2 in get_rotated(cur_p1, cur_p2):
            if not is_safe(*next_p1) or not is_safe(*next_p2):
                continue
            
            if tuple(sorted((next_p1, next_p2))) in visited:
                continue
            
            dq.append((next_p1, next_p2, cur_m + 1))
            visited.add((next_p1, next_p2))
    
    return -1
```

추가로 GPT와 시작 지점으로 되돌아올 수 있는 가능성에 대해 설쟁을 벌였는데, 결국 내 말이 맞았다. 기존 코드도 이 부분에 관련해 비효율적인 부분이 있어서 기존 코드를 수정해 다음과 같이 새로 작성해봤다.

```python
from collections import deque

def solution(board):
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

            if is_safe(next_r1, next_c1) and is_safe(next_r2, next_c2):
                next_p_list.append(((next_r1, next_c1), (next_r2, next_c2)))

        # 현재 가로 상태
        if r1 == r2:
            if is_safe(r1 - 1, c1) and is_safe(r2 - 1, c2):
                next_p_list.append(((r2 - 1, c2), (r2, c2)))
                next_p_list.append(((r1, c1), (r1 - 1, c1)))

            if is_safe(r1 + 1, c1) and is_safe(r2 + 1, c2):
                next_p_list.append(((r2 + 1, c2), (r2, c2)))
                next_p_list.append(((r1, c1), (r1 + 1, c1)))

        if c1 == c2:
            if is_safe(r1, c1 - 1) and is_safe(r2, c2 - 1):
                next_p_list.append(((r2, c2 - 1), (r2, c2)))
                next_p_list.append(((r1, c1), (r1, c1 - 1)))
                
            if is_safe(r1, c1 + 1) and is_safe(r2, c2 + 1):
                next_p_list.append(((r2, c2 + 1), (r2, c2)))
                next_p_list.append(((r1, c1), (r1, c1 + 1)))

        return next_p_list
    
    def is_safe(r, c):
        return 0 <= r < N and 0 <= c < N and board[r][c] == 0

    # 메인 로직
    N = len(board)
    desti = (N - 1, N - 1)
    
    visited = set()
    dq = deque()

    start_p = ((0, 0), (0, 1))
    start_m = 0
    dq.append((start_p, start_m))
    visited.add(start_p)

    while dq:
        cur_p, cur_m = dq.popleft()
        if desti in cur_p:
            return cur_m

        for next_p in get_next_p(cur_p):
            sorted = next_p if next_p[0] <= next_p[1] else (next_p[1], next_p[0])
            if sorted in visited:
                continue

            dq.append((next_p, cur_m + 1))
            visited.add(next_p)

    return -1
```