## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분`안에 문제를 해결할 수 있었다. 처음 7분동안 지뢰찾기 규칙을 몰라서 해맸다. 그래서 결국 시간을 멈추고 지뢰 찾기 규칙을 찾아본 후 다시 시간 측정을 하며 문제를 풀었다. 작성한 코드는 다음과 같다.

```python
# 23분 24초 남았을 때 지뢰 찾기 게임 규칙을 이해하고 풀이 코드 작성 시작함
from collections import deque
from copy import deepcopy

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        # 전역 데이터
        M = len(board)
        N = len(board[0])
        DIR = [[-1, 0],[0, 1], [0, -1], [1, 0], [-1, 1], [-1, -1], [1, 1], [1, -1]]

        # 메서드
        def get_mines():
            mines = []
            for row in range(M):
                for col in range(N):
                    if board[row][col] == "M":
                        mines.append((row, col))
            
            return mines
        
        def get_near_mine_count(row, col):
            count = 0
            for dr, dc in DIR:
                next_r, next_c = row + dr, col + dc
                if is_safe(next_r, next_c) and board[next_r][next_c] == "M":
                    count += 1
            
            return count
        
        def is_safe(row, col):
            return row >= 0 and row < M and col >= 0 and col < N

        # 메인 로직
        new_board = deepcopy(board)
        mines = get_mines()
        if (click[0], click[1]) in mines:
            new_board[click[0]][click[1]] = "X"
            return new_board
        
        dq = deque([(click[0], click[1])])
        visited = [[False] * N for _ in range(M)]
        visited[click[0]][click[1]] = True

        while dq:
            cur_r, cur_c = dq.popleft()
            mine_count = get_near_mine_count(cur_r, cur_c)

            if mine_count > 0:
                new_board[cur_r][cur_c] = str(mine_count)
                continue
            else:
                new_board[cur_r][cur_c] = "B"
            
            for dr, dc in DIR:
                next_r, next_c = cur_r + dr, cur_c + dc
                if not is_safe(next_r, next_c) or visited[next_r][next_c]:
                    continue

                dq.append((next_r, next_c))
                visited[next_r][next_c] = True

        return new_board
```

P & J 트레이닝

- Java로 1차 풀이를 시도하여 제한 시간 `30분` + 추가 시간을 사용해 문제를 해결했다. 다만 혼자서 해내지 못한 부분이 몇몇 있었다.
    - 문제에 제시된 지뢰 찾기 게임 규칙을 이해하지 못해서 따로 인터넷 검색을 진행했다.
    - int → char 변환 방법을 몰라서 인터넷에 서치했다. (이 방법은 코테 Tip 페이지에 기록하겠다.)
    - dir 2차원 배열에 잘못된 값을 넣어 오답이 발생했는데 이 논리 오류를 혼자 찾지 못해서 잼미니의 도움을 받았다.
- Python으로 2차 풀이를 시도하여 제한 시간 `6분 18초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

지뢰 찾기 게임에서 특정 칸을 선택했을 때 게임 규칙에 따라 발생하는 격자 상태를 만들어 반환하는 문제이다.

- 자세한건 문제와 지뢰 찾기 규칙을 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `m == board.length`
- `n == board[i].length`
- `1 <= m, n <= 50`
- `board[i][j]` is either `'M'`, `'E'`, `'B'`, or a digit from `'1'` to `'8'`.
- `click.length == 2`
- `0 <= clickr < m`
- `0 <= clickc < n`
- `board[clickr][clickc]` is either `'M'` or `'E'`.

---

## 🤔 풀이 고민

문제 풀이법은 지뢰 찾기 규칙을 이해하자 마자 떠올릴 수 있었다. `BFS` & `DFS` 모두 사용할 수 있을거 같아 난 BFS를 선택했다.

이 문제는 중간중간 탐색하면서 도달한 위치 주변에 지뢰가 몇개 존재하는지 탐색하는 추가 로직이 필요하다. 그렇게 어렵지 않은 로직이라 쉽게 구현할 수 있었다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드 개선

처음 작성한 코드에 비효율적인 문법이 있는데, 리스트를 튜플로 변환하는 방법을 몰라서 어거지로 작성한 코드가 있다. 이 부분만 다음과 같이 개선했다.

```python
from collections import deque
from copy import deepcopy

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        # 전역 데이터
        M = len(board)
        N = len(board[0])
        DIR = [[-1, 0],[0, 1], [0, -1], [1, 0], [-1, 1], [-1, -1], [1, 1], [1, -1]]
        click = tuple(click)

        # 메서드
        def get_mines():
            mines = []
            for row in range(M):
                for col in range(N):
                    if board[row][col] == "M":
                        mines.append((row, col))
            
            return mines
        
        def get_near_mine_count(row, col):
            count = 0
            for dr, dc in DIR:
                next_r, next_c = row + dr, col + dc
                if is_safe(next_r, next_c) and board[next_r][next_c] == "M":
                    count += 1
            
            return count
        
        def is_safe(row, col):
            return row >= 0 and row < M and col >= 0 and col < N

        # 메인 로직
        new_board = deepcopy(board)
        mines = get_mines()
        if click in mines:
            new_board[click[0]][click[1]] = "X"
            return new_board
        
        dq = deque([click])
        visited = [[False] * N for _ in range(M)]
        visited[click[0]][click[1]] = True

        while dq:
            cur_r, cur_c = dq.popleft()
            mine_count = get_near_mine_count(cur_r, cur_c)

            if mine_count > 0:
                new_board[cur_r][cur_c] = str(mine_count)
                continue
            else:
                new_board[cur_r][cur_c] = "B"
            
            for dr, dc in DIR:
                next_r, next_c = cur_r + dr, cur_c + dc
                if not is_safe(next_r, next_c) or visited[next_r][next_c]:
                    continue

                dq.append((next_r, next_c))
                visited[next_r][next_c] = True

        return new_board
```

- `tuple(list_a)`

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from collections import deque
    
    class Solution:
        def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
            dir = [[-1, 0], [0, -1], [0, 1], [1, 0], [-1, -1], [-1, 1], [1, -1], [1, 1]]
    
            # 메서드
            def is_safe(row, col):
                return 0 <= row < len(board) and 0 <= col < len(board[row])
    
            # 메인 로직
            dq = deque([click])
            visited = [[False] * len(board[0]) for _ in range(len(board))]
    
            while dq:
                c_row, c_col = dq.popleft()
    
                if board[c_row][c_col] == "M":
                    board[c_row][c_col] = "X"
                    break
                
                mine_count = 0
                for dr, dc in dir:
                    n_row, n_col = c_row + dr, c_col + dc
    
                    if not is_safe(n_row, n_col) or visited[n_row][n_col]:
                        continue
    
                    if board[n_row][n_col] == "M":
                        mine_count += 1
                
                if mine_count == 0:
                    board[c_row][c_col] = "B"
                else:
                    board[c_row][c_col] = str(mine_count)
                    continue
                
                for dr, dc in dir:
                    n_row, n_col = c_row + dr, c_col + dc
    
                    if not is_safe(n_row, n_col) or visited[n_row][n_col] or board[n_row][n_col] == "M":
                        continue
                    
                    dq.append([n_row, n_col])
                    visited[n_row][n_col] = True
    
            return board
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
    
        int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    
        char[][] board;
        boolean[][] visited;
    
        public char[][] updateBoard(char[][] board, int[] click) {
            this.board = board;
            this.visited = new boolean[board.length][board[0].length];
    
            Deque<int[]> dq = new ArrayDeque<>();
            dq.offer(click);
            visited[click[0]][click[1]] = true;
    
            while (!dq.isEmpty()) {
                int[] cur = dq.poll();
                char curObj = board[cur[0]][cur[1]];
    
                if (curObj == 'M') {
                    board[cur[0]][cur[1]] = 'X';
                    break;
                }
    
                int mineCount = 0;
                for (int[] d : dir) {
                    int nRow = cur[0] + d[0];
                    int nCol = cur[1] + d[1];
    
                    if (!isSafe(nRow, nCol) || visited[nRow][nCol]) {
                        continue;
                    }
    
                    char nextObj = board[nRow][nCol];
                    if (nextObj == 'M') {
                        mineCount += 1;
                    }
                }
    
                if (mineCount == 0) {
                    board[cur[0]][cur[1]] = 'B';
                } else {
                    board[cur[0]][cur[1]] = Character.forDigit(mineCount, 10);
                    continue;
                }
    
                for (int[] d : dir) {
                    int nRow = cur[0] + d[0];
                    int nCol = cur[1] + d[1];
    
                    if (!isSafe(nRow, nCol) || visited[nRow][nCol] || board[nRow][nCol] == 'M') {
                        continue;
                    }
    
                    dq.offer(new int[]{nRow, nCol});
                    visited[nRow][nCol] = true;
                }
            }
    
            return board;
        }
    
        private boolean isSafe(int row, int col) {
            return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 이제 구현은 어느정도 손에 익은거 같아서 뿌듯하다.
- P & J를 하니까 미숙한 문법과 실수가 보인다… 앞으로는 주의하자.