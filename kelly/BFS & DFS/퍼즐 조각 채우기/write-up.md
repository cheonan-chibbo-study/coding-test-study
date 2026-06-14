## 👀 제한 시간 안에 어디까지 해냈는가?

문제 풀이는 떠올릴 수 있었는데 구현이 너무 괴랄해서 따로 시간 측정을 하지 않고 문제를 풀었다. 결과적으로 몇몇 구현 부분이 막혀 어느정도 서치를 진행했지만 생각했던 풀이를 구현하니 문제를 해결할 수 있었다.

P & J 트레이닝

- 거의 대부분은 혼자서 구현했지만 몇몇 실수와 핵심 아이디어 실패로 결국 시간 측정을 멈추고 잼미니와 같이 문제를 풀었다.
- 몇몇 실수를 교정하고 잘못된 문제 이해만 바로 잡으면 다음에는 충분히 혼자서 해결할 수 있을거 같다면 구현 속도는 좀 느릴 수 밖에 없는거 같다.
    - Java는… 진짜 지옥임.

---

## 🧑‍🔬 문제 분석

같은 크기의 격자 game_board, table이 주어질 때 table에 있는 블록들을 4방향으로 회전하면서 game_board의 빈공간에 맞춰볼 때 가장 많은 빈공간을 채웠을 때 채운 수를 반환하는 문제이다.

- 자세한 문제 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- 3 ≤ `game_board`의 행 길이 ≤ 50
- `game_board`의 각 열 길이 = `game_board`의 행 길이
    - 즉, 게임 보드는 정사각 격자 모양입니다.
    - `game_board`의 모든 원소는 0 또는 1입니다.
    - 0은 빈칸, 1은 이미 채워진 칸을 나타냅니다.
    - 퍼즐 조각이 놓일 빈칸은 1 x 1 크기 정사각형이 최소 1개에서 최대 6개까지 연결된 형태로만 주어집니다.
- `table`의 행 길이 = `game_board`의 행 길이
- `table`의 각 열 길이 = `table`의 행 길이
    - 즉, 테이블은 `game_board`와 같은 크기의 정사각 격자 모양입니다.
    - `table`의 모든 원소는 0 또는 1입니다.
    - 0은 빈칸, 1은 조각이 놓인 칸을 나타냅니다.
    - 퍼즐 조각은 1 x 1 크기 정사각형이 최소 1개에서 최대 6개까지 연결된 형태로만 주어집니다.
- `game_board`에는 반드시 하나 이상의 빈칸이 있습니다.
- `table`에는 반드시 하나 이상의 블록이 놓여 있습니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

우선 문제에 주어지는 입력의 크기가 작은편이기 때문에 어느정도 완탐이 가능할거라 생각했다. 따라서 다음과 같은 풀이를 생각했다.

1. 각 격자에서 빈공간, 블록 아이템을 파싱한다.
2. 1에서 파싱한 빈공간과 블록을 매칭시키면서 가장 많이 매칭시켰을 때 채운 공간 최대 값을 갱신한다.
3. 최종적으로 갱신된 채운 공간수를 반환한다.

결과적으로 내가 생각한 풀이는 올바른 풀이였다. 이 풀이를 구현해 제출하면 문제가 해결된다.

---

## 🏃 코드 작성 과정

### 내가 직접 작성 + AI 도움을 받은 코드

각 격자에서 빈공간, 블록 아이템을 파싱하는 부분은 혼자서 작성할 수 있었다. 하지만 파싱한 아이템들을 매칭해서 결과를 찾는 코드를 작성하는데 어려움을 겪어 AI의 도움을 받아 마져 작성할 수 있었다.

- 처음 생각했던 코드는 재귀를 활용해 모든 빈공간/블록 조합을 구하면서 정답을 갱신하는 코드였다.
- 하지만 AI의 답변을 보고 어차피 블록/빈공간은 서로 같은 모양만 맞출 수 있으므로 굳이 재귀를 하지 않고 `그리디`를 활용해서 당장 맞출 수 있는 블록/빈공간을 찾으면서 정답을 누적시키면 재귀보다 훨씬 빠른 시간으로 정답을 구할 수 있다.

최종적으로 작성된 코드는 다음과 같다.

```python
from collections import deque
from copy import deepcopy

def solution(game_board, table):
    # 메서드
    def parse_items(board, mark):
        item_pos_list = []
        for row in range(len(board)):
            for col in range(len(board[row])):
                if board[row][col] == mark:
                    item_pos_list.append(bfs(board, row, col, mark))
        
        return [make_item(pos) for pos in item_pos_list]
    
    def bfs(board, start_row, start_col, mark):
        result = []
        dq = deque()
        dq.append((start_row, start_col))
        result.append((start_row, start_col))
        board[start_row][start_col] = -1
        
        while dq:
            cur_r, cur_c = dq.popleft()
            
            for dr, dc in [(-1, 0), (0, 1), (0, -1), (1, 0)]:
                next_r, next_c = cur_r + dr, cur_c + dc
                
                if not 0<=next_r<len(board) or not 0<=next_c<len(board[next_r]) or board[next_r][next_c] != mark:
                    continue
                
                dq.append((next_r, next_c))
                result.append((next_r, next_c))
                board[next_r][next_c] = -1
        
        return result
    
    def make_item(pos_list):
        row_list = [pos[0] for pos in pos_list]
        col_list = [pos[1] for pos in pos_list]
        min_row = min(row_list)
        max_row = max(row_list)
        min_col = min(col_list)
        max_col = max(col_list)
        
        item_row_size = max_row - min_row + 1
        item_col_size = max_col - min_col + 1
        item = [[0] * item_col_size for _ in range(item_row_size)]
        for r, c in pos_list:
            item[r - min_row][c - min_col] = 1
        
        return (item, len(pos_list))
        
    # 메인 로직
    empty_list = parse_items(deepcopy(game_board), 0)
    block_list = parse_items(deepcopy(table), 1)
    
    answer = 0
    used = [False] * len(block_list)
    for empty, e_size in empty_list:
        founded = False
        for j in range(len(block_list)):
            if used[j]:
                continue
            
            block, b_size = block_list[j]
            if e_size != b_size:
                continue
            
            cur_block = block
            for _ in range(4):
                if empty == cur_block:
                    answer += e_size
                    used[j] = True
                    founded = True
                    break
                
                cur_block = [list(rotatted) for rotatted in zip(*cur_block[::-1])]
            
            if founded:
                break
    
    return answer
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from copy import deepcopy
    
    def solution(game_board, table):
        # 메서드
        def get_parts(board, obj):
            result = []
            visited = [[False] * len(board[0]) for _ in range(len(board))]
            for row in range(len(board)):
                for col in range(len(board[row])):
                    if board[row][col] == obj and not visited[row][col]:
                        result.append(dfs(board, obj, visited, row, col))
            
            return result
        
        def dfs(board, obj, visited, start_r, start_c):
            pos_list = [(start_r, start_c)]
            stack = [(start_r, start_c)]
            visited[start_r][start_c] = True
            
            while stack:
                cur_r, cur_c = stack.pop()
                
                for dr, dc in ((-1, 0), (0, -1), (0, 1), (1, 0)):
                    next_r, next_c = cur_r + dr, cur_c + dc
                    
                    if not is_safe(board, visited, obj, next_r, next_c):
                        continue
                    
                    stack.append((next_r, next_c))
                    visited[next_r][next_c] = True
                    pos_list.append((next_r, next_c))
            
            return make_part(pos_list)
        
        def is_safe(board, visited, obj, row, col):
            return 0 <= row < len(board) and 0 <= col < len(board[row]) and board[row][col] == obj and not visited[row][col]
        
        def make_part(pos_list):
            row_list = [row for row, col in pos_list]
            col_list = [col for row, col in pos_list]
            
            min_row, min_col = min(row_list), min(col_list)
            max_row, max_col = max(row_list), max(col_list)
            
            part_row_size = max_row - min_row + 1
            part_col_size = max_col - min_col + 1
            node_count = 0
            part = [[0] * part_col_size for _ in range(part_row_size)]
            
            for row, col in pos_list:
                part[row - min_row][col - min_col] = 1
                node_count += 1
            
            return (part, node_count)
        
        def rotate(ori):
            rotatted = [[0] * len(ori) for _ in range(len(ori[0]))]
            for row in range(len(ori)):
                for col in range(len(ori[row])):
                    rotatted[col][len(ori) - row - 1] = ori[row][col]
            
            return rotatted
                        
        # 메인 로직
        gb_parts = get_parts(game_board, 0)
        table_parts = get_parts(table, 1)
        gbp_used = [False] * len(gb_parts)
        
        answer = 0
        
        for tp_idx in range(len(table_parts)):
            tp, tp_size = table_parts[tp_idx]
            rotatted = deepcopy(tp)
            stop = False
            
            for rot in range(4):
                if rot > 0:
                    rotatted = rotate(rotatted)
                
                for gbp_idx in range(len(gb_parts)):
                    if gbp_used[gbp_idx]:
                        continue
                    
                    gbp, gbp_size = gb_parts[gbp_idx]
                    if rotatted == gbp:
                        answer += tp_size
                        gbp_used[gbp_idx] = True
                        stop = True
                        break
            
                if stop:
                    break
                    
        return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        
        int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        
        public int solution(int[][] game_board, int[][] table) {
            // 메인 로직
            List<Part> gbParts = getParts(game_board, 0);
            List<Part> tParts = getParts(table, 1);
            boolean[] gbpUsed = new boolean[gbParts.size()];
            
            int answer = 0;
            
            for (Part tPart : tParts) {
                int[][] rotatted = tPart.getValue();
                
                for (int rot = 0; rot < 4; rot++) {
                    if (rot > 0) {
                        rotatted = rotate(rotatted);
                    }
                    
                    boolean founded = false;
                    
                    for (int gbIdx = 0; gbIdx < gbParts.size(); gbIdx++) {
                        if (gbpUsed[gbIdx]) {
                            continue;
                        }
                        
                        if (gbParts.get(gbIdx).match(new Part(rotatted, tPart.size))) {
                            answer += tPart.size;
                            gbpUsed[gbIdx] = true;
                            founded = true;
                            break;
                        }
                    }
                    
                    if (founded) {
                        break;
                    }
                }
            }
            
            return answer;
        }
        
        private List<Part> getParts(int[][] board, int obj) {
            List<Part> result = new ArrayList<>();
            boolean[][] visited = new boolean[board.length][board[0].length];
            
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col] == obj && !visited[row][col]) {
                        result.add(bfs(board, obj, visited, row, col));    
                    }
                }
            }
            
            return result;
        }
        
        private Part bfs(int[][] board, int obj, boolean[][] visited, int startR, int startC) {
            List<int[]> posList = new ArrayList<>();
            Deque<int[]> dq = new ArrayDeque<>();
            
            dq.offer(new int[]{startR, startC});
            visited[startR][startC] = true;
            posList.add(new int[]{startR, startC});
            
            while (!dq.isEmpty()) {
                int[] cur = dq.poll();
                
                for (int[] d : dir) {
                    int nextR = cur[0] + d[0];
                    int nextC = cur[1] + d[1];
                    
                    if (!isSafe(board, obj, visited, nextR, nextC)) {
                        continue;
                    }
                    
                    dq.offer(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                    posList.add(new int[]{nextR, nextC});
                }
            }
            
            return makePart(posList);
        }
        
        private boolean isSafe(int[][] board, int obj, boolean[][] visited, int row, int col) {
            return row >= 0 && row < board.length && col >= 0 && col < board[row].length
                && board[row][col] == obj && !visited[row][col];
        }
        
        private Part makePart(List<int[]> posList) {
            int minRow = Integer.MAX_VALUE;
            int minCol = Integer.MAX_VALUE;
            int maxRow = Integer.MIN_VALUE;
            int maxCol = Integer.MIN_VALUE;
            
            for (int[] pos : posList) {
                minRow = Math.min(minRow, pos[0]);
                minCol = Math.min(minCol, pos[1]);
                maxRow = Math.max(maxRow, pos[0]);
                maxCol = Math.max(maxCol, pos[1]);
            }
            
            int[][] part = new int[maxRow - minRow + 1][maxCol - minCol + 1];
            int partSize = 0;
            for (int[] pos : posList) {
                part[pos[0] - minRow][pos[1] - minCol] = 1;
                partSize += 1;
            }
            
            return new Part(part, partSize);
        }
    
        private int[][] rotate(int[][] ori) {
            int[][] rotatted = new int[ori[0].length][ori.length];
            
            for (int row = 0; row < ori.length; row++) {
                for (int col = 0; col < ori[row].length; col++) {
                    rotatted[col][ori.length - row - 1] = ori[row][col];
                }
            }
            
            return rotatted;
        }
        
        class Part {
            int[][] value;
            int size;
            
            public Part(int[][] value, int size) {
                this.value = value;
                this.size = size;
            }
            
            public int[][] getValue() {
                int[][] newValue = new int[value.length][value[0].length];
                for (int row = 0; row < value.length; row++) {
                    for (int col = 0; col < value[0].length; col++) {
                        newValue[row][col] = value[row][col];
                    }
                }
                
                return newValue;
            }
            
            public boolean match(Part o) {
                if (o.size != size || o.value.length != value.length || o.value[0].length != value[0].length) {
                    return false;
                }
                
                for (int row = 0; row < value.length; row++) {
                    for (int col = 0; col < value[row].length; col++) {
                        if (value[row][col] != o.value[row][col]) {
                            return false;
                        }
                    }
                }
                
                return true;
            }
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- BFS/DFS 문제라고 예상했는데 실제로 BFS/DFS 부분은 진짜 아주 조금 나왔다… 사실상 깡구현 문제이다.