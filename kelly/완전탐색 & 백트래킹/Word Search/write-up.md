## 👀 제한 시간 안에 어디까지 해냈는가?

`25분`안에 혼자서 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

board, word가 주어질 때 board에서 타일을 이어서 이동할경우 word 문자열을 완성할 수 있는지 여부를 찾아 반환하는 문제이다.

문제에 주어지는 제약 조건은 다음과 같다.

**Constraints:**

- `m == board.length`
- `n = board[i].length`
- `1 <= m, n <= 6`
- `1 <= word.length <= 15`
- `board` and `word` consists of only lowercase and uppercase English letters.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

주어진 입력의 크기가 크지 않기 때문에 완탐 풀이를 생각했다. bfs 혹은 dfs로도 풀 수 있을거 같은데 지금은 재귀를 학습하고 있으므로 재귀를 활용한 dfs 풀이를 사용해 4방향을 탐색하는 코드를 작성하면 문제를 해결할 수 있다.

### 결론

- 내가 처음 생각한 풀이를 코드로 구현하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

혼자서 풀이 코드를 작성할 수 있었다. 아래 최종 정답 코드를 참고하자.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    class Solution:
        def exist(self, board: List[List[str]], word: str) -> bool:
            row_size = len(board)
            col_size = len(board[0])
    
            # 메서드
            def search(s_row, s_col, visited, step):
                if step == len(word):
                    return True
    
                for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
                    n_row, n_col = s_row + dr, s_col + dc
    
                    if not is_safe(n_row, n_col, visited) or board[n_row][n_col] != word[step]:
                        continue
    
                    visited[n_row][n_col] = True
                    if search(n_row, n_col, visited, step + 1):
                        return True
    
                    visited[n_row][n_col] = False
    
                return False
    
            def is_safe(r, c, visited):
                return 0 <= r < row_size and 0 <= c < col_size and not visited[r][c]
    
            # 메인 로직
            for row in range(row_size):
                for col in range(col_size):
                    if board[row][col] != word[0]:
                        continue
    
                    visited = [[False] * col_size for _ in range(row_size)]
                    visited[row][col] = True
                    if search(row, col, visited, 1):
                        return True
    
            return False
    ```


### Java 풀이

- solution01

    ```java
    class Solution {
    
        int[][] dir = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
        char[][] board;
        String word;
        int rowSize;
        int colSize;
    
        public boolean exist(char[][] board, String word) {
            this.board = board;
            this.word = word;
            this.rowSize = board.length;
            this.colSize = board[0].length;
    
            // 메인 로직
            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    if (board[row][col] != word.charAt(0)) {
                        continue;
                    }
    
                    boolean[][] visited = new boolean[rowSize][colSize];
                    visited[row][col] = true;
                    if (search(row, col, visited, 1)) {
                        return true;
                    }
                }
            }
    
            return false;
        }
    
        private boolean search(int sRow, int sCol, boolean[][] visited, int step) {
            if (step == word.length()) {
                return true;
            }
    
            for (int d = 0; d < 4; d++) {
                int nRow = sRow + dir[d][0];
                int nCol = sCol + dir[d][1];
    
                if (!isSafe(nRow, nCol, visited) || board[nRow][nCol] != word.charAt(step)) {
                    continue;
                }
    
                visited[nRow][nCol] = true;
                if (search(nRow, nCol, visited, step + 1)) {
                    return true;
                }
    
                visited[nRow][nCol] = false;
            }
    
            return false;
        }
    
        private boolean isSafe(int r, int c, boolean[][] visited) {
            return r >= 0 && r < rowSize && c >= 0 && c < colSize && !visited[r][c];
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- Java는 장황하다… 그래도 연습하자…
- 코드 구현시 은근 실수가 많이 발생한다. 실수를 하지 않도록 주의하자.