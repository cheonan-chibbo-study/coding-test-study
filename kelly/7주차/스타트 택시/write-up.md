## 👻 재시도 기록

### 재시도 1회

제한 시간 `30분` + 추가 시간 `20분`을 더 사용했지만 정답 코드 작성에 실패했다.

풀이법은 떠올려서 코드를 작성하는건 어렵지 않았지만 다음과 같은 이유로 정답 코드를 작성하지 못했다.

- 변수명 작성 과정에서 문법 오류가 발생했다.
- 몇몇 문제의 조건을 누락하고 코드를 작성했다.
    - 택시가 손님을 픽업/목적지까지 이동 케이스마다 택시의 현재 위치를 갱신해주지 않았다.
    - 이미 태운 승객을 배열에서 삭제하지 않았다.
    - 연료가 충분해도 격자내 벽같은 변수로 인해 손님을 태우지 못하거나, 목적지까지 갈 수 없는 경우에 대한 처리를 작성하지 않았다.

위 실수들을 수정하니 바로 정답 처리를 받았다. 이 문제는 이제 풀이법을 떠올리는건 잘 할 수 있지만 워낙 조건이 많고 코드도 길어져서 실수를 많이 하는거 같다.

다음에 또 풀 때는 이런 실수를 하지 않도록 주의해야겠다. 아래는 최종 작성한 코드이다.

```python
from collections import deque

# 전역 데이터
N, M, oil = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
start_t_r, start_t_c = map(lambda x: int(x) - 1, input().split())
client_start = []
client_end = []
for _ in range(M):
    r1, c1, r2, c2 = map(lambda x: int(x) - 1, input().split())
    client_start.append((r1, c1))
    client_end.append((r2, c2))

DIR = [[-1, 0], [0, 1], [0, -1], [1, 0]]

# 메서드
def search(start_r, start_c, has_client, client_idx):
    dq = deque()
    visited = [[False] * N for _ in range(N)]
    dq.append((start_r, start_c, 0))
    visited[start_r][start_c] = True
    
    minimum_path = float('inf')
    candi = []
    while dq:
        cur_r, cur_c, cur_m = dq.popleft()
        if cur_m > minimum_path:
            break
        
        if not has_client:
            if (cur_r, cur_c) in client_start:
                candi.append((cur_r, cur_c, cur_m))
                minimum_path = cur_m
                continue
        else:
            if (cur_r, cur_c) == client_end[client_idx]:
                return (cur_r, cur_c, cur_m)
        
        for dr, dc in DIR:
            next_r, next_c = cur_r + dr, cur_c + dc
            
            if not 0 <= next_r < N or not 0 <= next_c < N or board[next_r][next_c] == 1 or visited[next_r][next_c]:
                continue
            
            dq.append((next_r, next_c, cur_m + 1))
            visited[next_r][next_c] = True
    
    if not candi:
        return None
    
    candi.sort()
    return candi[0]
        
# 메인 로직
is_fail = False
for i in range(M):
    search_result = search(start_t_r, start_t_c, False, -1)
    if not search_result:
        is_fail = True
        break
    
    next_c_r, next_c_c, next_c_p = search_result
    if oil <= next_c_p:
        is_fail = True
        break
    
    start_t_r, start_t_c = next_c_r, next_c_c
    oil -= next_c_p
    client_idx = client_start.index((start_t_r, start_t_c))
    search_result = search(next_c_r, next_c_c, True, client_idx)
    if not search_result:
        is_fail = True
        break
        
    next_d_r, next_d_c, next_d_p = search_result
    if oil < next_d_p:
        is_fail = True
        break
    
    start_t_r, start_t_c = next_d_r, next_d_c
    oil += next_d_p
    client_start.pop(client_idx)
    client_end.pop(client_idx)

if not is_fail:
    print(oil)
else:
    print(-1)
```