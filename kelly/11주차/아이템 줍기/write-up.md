## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분` + `추가 시간 15분`을 사용했지만 문제를 해결하지 못했다.

- 풀이 자체는 올바른 풀이법을 금방 떠올릴 수 있었다.
- 하지만 코드 작성 과정에서 실수 + 예상하지 못한 논리 오류로 인해 테스트 케이스 1개만 통과했다.

---

## 🧑‍🔬 문제 분석

각 직사각형의 좌측 하단 & 우측 하단 좌표 정보 리스트와 시작 위치, 도착 위치가 주어질 때 도착 위치에 도달할 수 있는 최단 거리를 구해 반환하는 문제이다.

- 이 때 이동할 수 있는 좌표는 주어진 직사각형들을 모두 겹쳤을 때 테두리 부분만 이동할 수 있다.
- 자세한 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- 각 단어는 알파벳 소문자로만 이루어져 있습니다.
- 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
- words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
- begin과 target은 같지 않습니다.
- 변환할 수 없는 경우에는 0를 return 합니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

우선 풀이 자체는 혼자서 먹히는 풀이를 금방 설계할 수 있었다.

1. 주어진 직사각형을 모두 겹쳤을 때 이동 가능한 좌표 정보를 가진 board 2차원 리스트를 생성
2. 위에서 생성한 2차원 리스트에서 bfs를 진행 후 목표지까지의 최단 거리를 구해 반환

주어지는 입력이 작은편이기 때문에 위 풀이로도 문제를 풀 수 있을거라고 판단했다. 하지만 간단한 이론과 달리 코드 구현이 제법 어려웠다.

### 결론

- 내가 작성한 풀이로 `올바른 코드`를 작성하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

내가 처음 작성한 코드는 다음과 같다. 이 코드는 1개의 테스트 케이스만 맞고 나머지는 잘못된 결과나 시간 초과가 발생했다.

```python
from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    # 메서드
    def get_board():
        rectangle.sort()
        x_list = []
        y_list = []
        for rec in rectangle:
            x_list.append(rec[0])
            x_list.append(rec[2])
            y_list.append(rec[1])
            y_list.append(rec[3])
        
        board_row_size = max(y_list) - min(y_list) + 1
        board_col_size = max(x_list) - min(x_list) + 1
        
        x_offset = min(x_list)
        y_offset = min(y_list)
        board = [[False] * board_col_size for _ in range(board_row_size)]
        for rec in rectangle:
            for col in range(rec[0], rec[2] + 1):
                board[rec[1] - y_offset][col - x_offset] = True
                board[rec[3] - y_offset][col - x_offset] = True
            
            for row in range(rec[1], rec[3] + 1):
                board[row - y_offset][rec[0] - x_offset] = True
                board[row - y_offset][rec[2] - x_offset] = True
            
            for row in range(rec[1] + 1, rec[3]):
                for col in range(rec[0] + 1, rec[2]):
                    board[row - y_offset][col - x_offset] = False
            
            return (board, y_offset, x_offset)
    
    def bfs():
        dq = deque()
        visited = [[False] * len(board[0]) for _ in range(len(board))]
        start_r, start_c, target_r, target_c = characterY - row_offset, characterX - col_offset, itemY - row_offset, itemX - col_offset
        
        dq.append((start_r, start_c, 0))
        visited[start_r][start_c] = True
        
        while dq:
            cur_r, cur_c, cur_s = dq.popleft()
            
            if cur_r == target_r and cur_c == target_c:
                return cur_s
            
            for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
                next_r, next_c = cur_r + dr, cur_c + dc
                
                if not is_safe(next_r, next_c, visited):
                    continue
                
                dq.append((next_r, next_c, cur_s + 1))
                visited[next_r][next_c] = True
        
        return -1
    
    def is_safe(r, c, visited):
        if not 0 <= r < len(board) or not 0 <= c < len(board[r]):
            return False
        
        if not board[r][c] or visited[r][c]:
            return False
        
        return True
            
    # 메인 로직
    board, row_offset, col_offset = get_board()
    return bfs()
```

위 코드의 잘못된 부분을 나열하면 다음과 같다.

1. `get_board()` 메서드의 최종 return문이 한 단계 더 들여쓰기 되어 있다.
2. board 생성 시 다른 사각형 내부 공간에 겹쳐지는 테두리도 이동 가능한 경로로 표시되는 문제가 있다.
3. `ㄷ` 경로 이동 시 무단 횡단 문제가 있다.

    ```python
    (1, 1) -> (1, 2)
    (2, 1) <- (2, 2)
    ```

   위 케이스에 경우 정상적인 이동에서는 옆으로 뒤집어진 ㄷ자 경로로 이동해야 정상이다.

    - 하지만 실제 BFS 로직에서는 (1, 1)에서 (2,1)로 인간 관점에서는 이동하지 말아야 하지만,
    - 컴퓨터 관점에서는 (2,1) 자체도 이동 가능한 좌표로 설정되어 있기 때문에 `(1,1) → (2,1)` 이동이 발생하여 문제 의도와는 다른 탐색이 발생한다.
4. bfs 탐색에서 visited 처리를 하는 코드가 누락되어 있다.

### 내가 작성한 코드에서 잘못된 부분 정정

내가 처음 작성한 코드에서 잘못된 부분을 모두 정정하면 다음과 같다. 이 코드는 제출 시 정답 처리를 받는다.

```python
from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    # 메서드
    def get_board():
        # 1. 스케일 업: 시작하기 전에 모든 사각형의 좌표를 2배로 늘려줌!
        scaled_rects = []
        for rec in rectangle:
            scaled_rects.append([rec[0] * 2, rec[1] * 2, rec[2] * 2, rec[3] * 2])
        
        # 기존의 rectangle 대신 2배로 늘어난 scaled_rects를 사용
        x_list = []
        y_list = []
        for rec in scaled_rects: 
            x_list.append(rec[0])
            x_list.append(rec[2])
            y_list.append(rec[1])
            y_list.append(rec[3])
        
        board_row_size = max(y_list) - min(y_list) + 1
        board_col_size = max(x_list) - min(x_list) + 1
        
        x_offset = min(x_list)
        y_offset = min(y_list)
        board = [[-1] * board_col_size for _ in range(board_row_size)]
        
        # 기존의 rectangle 대신 2배로 늘어난 scaled_rects를 사용
        for rec in scaled_rects:
            for col in range(rec[0], rec[2] + 1):
                if board[rec[1] - y_offset][col - x_offset] == -1:
                    board[rec[1] - y_offset][col - x_offset] = 1
                if board[rec[3] - y_offset][col - x_offset] == -1:
                    board[rec[3] - y_offset][col - x_offset] = 1
            
            for row in range(rec[1], rec[3] + 1):
                if board[row - y_offset][rec[0] - x_offset] == -1:
                    board[row - y_offset][rec[0] - x_offset] = 1
                if board[row - y_offset][rec[2] - x_offset] == -1:
                    board[row - y_offset][rec[2] - x_offset] = 1
            
            for row in range(rec[1] + 1, rec[3]):
                for col in range(rec[0] + 1, rec[2]):
                    board[row - y_offset][col - x_offset] = 0
            
        return (board, y_offset, x_offset)
    
    def bfs():
        dq = deque()
        visited = [[False] * len(board[0]) for _ in range(len(board))]
        
        # 2. 스케일 업: 시작 좌표와 타겟 좌표도 2배로 늘린 후 오프셋 적용!
        start_r = characterY * 2 - row_offset
        start_c = characterX * 2 - col_offset
        target_r = itemY * 2 - row_offset
        target_c = itemX * 2 - col_offset
        
        dq.append((start_r, start_c, 0))
        visited[start_r][start_c] = True
        
        while dq:
            cur_r, cur_c, cur_s = dq.popleft()
            
            if cur_r == target_r and cur_c == target_c:
                # 3. 스케일 다운: 이동 거리가 2배로 측정되었으니 정답은 2로 나눠서 반환!
                return cur_s // 2
            
            for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
                next_r, next_c = cur_r + dr, cur_c + dc
                
                if not is_safe(next_r, next_c, visited):
                    continue
                
                dq.append((next_r, next_c, cur_s + 1))
                visited[next_r][next_c] = True
        
        return -1
    
    def is_safe(r, c, visited):
        if not 0 <= r < len(board) or not 0 <= c < len(board[r]):
            return False
        
        if not board[r][c] == 1 or visited[r][c]:
            return False
        
        return True
            
    # 메인 로직
    board, row_offset, col_offset = get_board()
    return bfs()
```

1. 잘못된 return 들여쓰기 깊이를 정정한다.
2. 새로 생성하는 격자 board의 구성을 True, False로만 하면 다른 직사각형 내부에 겹치는 테두리를 체크할 방법이 없다.
    1. 따라서 빈 공간은 -1, 이동 불가한 좌표는 0, 이동 가능한 좌표를 1로 표시하도록 코드를 수정하면 의도대로 board 2차원 리스트를 생성할 수 있다.
3. 이 문제는 제미나이의 도움을 받았는데, 문제에서 주어지는 각 직사각형 좌표 정보의 크기를 2배로 늘려서 board를 생성하고, bfs 탐색시 2배로 커진 좌표의 크기를 원래 크기로 조율하는 방식을 알려줬다.

    ```python
    **(2, 2, old)** -> (2, 2, new) -> **(2, 4, old)**
    (3, 2, new)    (3, 3, new)    (3, 4, new)
    **(4, 2, old)** <- (4, 3, new) <- (4, 4, new)
    
    ```

    - 이 방식을 사용하면 ㄷ자 이동이 사람의 의도대로 가능하다고 한다.
    - 시작 위치, 도착 위치도 2배 스케일 업을 해줘야 한다.
    - 최단 거리 반환시에는 2배만큼 거리가 늘어난 board에서 탐색했기 때문에 2로 나눈 값을 반환해야한다.
4. 방문 처리 로직을 추가해줬다.

### 풀이 코드 최적화

위에 완성한 코드는 중복도 많고 길어서 실수를 유발하기 좋다. 따라서 제미나이에게 코드 최적화를 요청했다.

```python
from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    # 메서드
    def get_board():
        # 1. 넉넉한 고정 크기 배열 선언 (최대 50 * 2 = 100이므로 102면 충분)
        board = [[-1] * 102 for _ in range(102)]

        # 2. 오프셋 없이 바로 2배율 적용하며 그리기 (하나의 루프로 압축!)
        for x1, y1, x2, y2 in rectangle:
            x1, y1, x2, y2 = x1 * 2, y1 * 2, x2 * 2, y2 * 2

            for row in range(y1, y2 + 1):
                for col in range(x1, x2 + 1):
                    # x1, x2, y1, y2 경계선 안쪽이면 무조건 내부(0)
                    if y1 < row < y2 and x1 < col < x2:
                        board[row][col] = 0
                    # 내부에 해당하지 않는 모서리(테두리)이면서, 다른 사각형의 내부가 아닐 때만 1
                    elif board[row][col] != 0:
                        board[row][col] = 1
        
        return board
    
    # 3. BFS 탐색
    def bfs():
        dq = deque()
        visited = [[False] * 102 for _ in range(102)]
        
        start_r, start_c = characterY * 2, characterX * 2
        target_r, target_c = itemY * 2, itemX * 2
        
        dq.append((start_r, start_c, 0))
        visited[start_r][start_c] = True
        
        while dq:
            cur_r, cur_c, cur_s = dq.popleft()
            
            if cur_r == target_r and cur_c == target_c:
                return cur_s // 2
            
            for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
                next_r, next_c = cur_r + dr, cur_c + dc
                
                # 고정 배열이므로 범위 체크가 매우 단순해짐
                if 0 <= next_r < 102 and 0 <= next_c < 102:
                    if board[next_r][next_c] == 1 and not visited[next_r][next_c]:
                        dq.append((next_r, next_c, cur_s + 1))
                        visited[next_r][next_c] = True
        return -1
    
    # 메인 로직
    board = get_board()
    return bfs()
```

- board의 크기를 102로 잡은 이유

  서은아, 코드에서 갑자기 튀어나온 `102`라는 숫자 때문에 헷갈렸구나! 알고리즘을 풀 때 이렇게 뜬금없이 등장하는 '매직 넘버(Magic Number)'의 이유를 정확히 짚고 넘어가는 건 정말 훌륭한 습관이야.

  결론부터 말하자면, `102`는 **문제의 최댓값(50)을 2배로 늘린 뒤(100), 인덱스 에러를 막기 위해 안전 여백(+2)을 추가한 크기**야.

  왜 이 크기가 필요한지 3단계로 아주 쉽게 쪼개서 설명해 줄게!

  ### 1. 제한 사항과 2배 스케일 업 (최댓값 100)

  프로그래머스 문제 설명을 보면, 주어지는 좌표 `x, y`의 최댓값은 **50**이야.

  그런데 우리는 ㄷ자 골목의 무단횡단을 막기 위해 모든 좌표에 `* 2`를 해서 스케일 업을 했지?

  따라서 우리가 배열(보드)에 그려야 할 좌표의 실질적인 최댓값은 50 * 2 = **100**이 돼.

  ### 2. 인덱스는 0부터 시작한다 (최소 101 필요)

  파이썬의 리스트 인덱스는 1이 아니라 0부터 시작하잖아?

  만약 배열 크기를 100으로 만들면, 인덱스는 `0`부터 `99`까지만 존재하게 돼.

  우리는 좌표가 `100`인 곳에 접근해서 값을 바꿔야 하니까(`board[100][100]`), 이를 담으려면 배열의 크기가 최소한 **101**은 되어야 해.

  ### 3. BFS를 위한 안전 여백 (그래서 102!)

  "그럼 101로 만들면 되지, 왜 102로 만들었을까?" 하는 의문이 들지? **101로 만들어도 정답 처리는 완벽하게 돼!**

  하지만 실전 코딩 테스트에서 `102`로 한 칸을 더 넉넉하게 잡는 이유는 바로 **안전 여백(Padding)** 때문이야.

  우리가 만든 BFS 로직은 상하좌우를 살필 때 이런 코드를 써

    ```python
    if 0 <= next_r < 102 and 0 <= next_c < 102:
    ```

    - 만약 캐릭터가 보드의 맨 끝인 `100` 좌표에 서 있다고 상상해 보자. 여기서 오른쪽(`+1`)을 확인하려고 하면 `101` 좌표를 쳐다보게 되겠지?
    - 배열 크기를 101로 타이트하게 만들면 인덱스가 100까지만 있으므로, 모서리에서 범위를 벗어나 **IndexError**가 터질 위험이 조금이라도 높아져.
    - 그래서 배열 크기를 **102**로 만들어버리면, 마지막 인덱스가 `101`이 되면서 보드 전체를 삥 둘러싸는 한 칸 두께의 '투명한 벽(여백)'이 생기는 효과가 나.

  BFS가 맨 끝 가장자리에서 탐색하더라도 절대 배열 범위를 벗어나지 않고 튕겨 나오기 때문에, 훨씬 안전하고 마음 편하게 코드를 짤 수 있어!
    
  ---

  **🎙️ 면접 답변 형식**

  **Q. 2차원 배열을 활용할 때, 문제에서 주어지는 좌표의 최댓값보다 배열의 크기를 약간 더 크게(예: +2) 선언하는 이유는 무엇인가요?**

  **A.** 메모리 낭비를 최소화하는 선에서 인덱스 접근 오류(Index Out of Bounds)를 예방하고, 탐색 알고리즘의 안정성을 높이기 위한 **방어적 프로그래밍(Defensive Programming)** 기법입니다.

  First, 배열의 인덱스는 0부터 시작하므로 최댓값 좌표인 $N$에 접근하기 위해서는 배열의 크기가 최소 $N+1$이어야 합니다.

  Second, 여기에 크기를 1 더 추가하여 $N+2$로 선언하면, 사용되는 유효 좌표 공간 바깥으로 1칸 두께의 패딩(Padding) 영역이 형성됩니다. 이는 BFS나 DFS 같은 그래프 탐색 알고리즘이 맵의 가장자리 끝에서 인접한 좌표를 탐색할 때 배열의 경계를 벗어나는 치명적인 에러를 구조적으로 방지해 주어 구현의 복잡도를 낮추는 장점이 있습니다.

---

## 🥰 배운점 & 느낀점

- 풀이 설계 자체는 쉬웠는데 구현이 좀 어려웠다. 이런 방식의 좌표 이동 탐색은 처음인거 같다.
- 이번 문제에서 제법 많은걸 배울 수 있었다. 잘 복습해서 비슷한 문제가 나와도 당황하지 말고 풀자.
