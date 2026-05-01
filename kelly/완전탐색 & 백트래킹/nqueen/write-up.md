## 👀 제한 시간 안에 어디까지 해냈는가?

- Java로 처음 도전하여 `29분 21초`만에 혼자서 문제를 해결함.
- Python으로 2차 풀이를 진행하여 `7분27초`만에 혼자서 문제를 해결함.

---

## 🧑‍🔬 문제 분석

격자의 가로, 세로 길이 n이 주어질 때 n x n 크기 격자에 퀸을 n개 놓을 수 있는 격자 상태를 모두 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= n <= 9`

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

주어지는 n의 최대 값이 작은 수이기 때문에 완전 탐색 방식을 먼저 떠올렸습니다. n의 최대 값인 9라고 가정하면 9 * 9 크기의 체스판에 9개의 퀸을 모두 놓을 수 있는 경우의 수를 격자 하나하나에 퀸을 두는 방식으로 문제를 푸는 방법을 생각해 볼 수 있습니다.

이 방식을 사용할 경우 예상 시간 복잡도를 계산해보겠습니다. 우선 단순하게 생각하면 퀸은 한 줄에 하나만 올 수 있기 때문에 한 줄에 퀸을 하나만 둔다고 가정하면 n = 3이라고 가정했을 때

- 첫 번째 줄에 첫 번째 퀸이 올 수 있는 경우의 수 → 3
- 두 번째 줄에 두 번째 퀸이 올 수 있는 경우의 수 → 3
- 세 번째 줄에 세 번째 퀸이 올 수 있는 경우의 수 → 3

n = 3일 경우 각 줄마다 퀸을 배치할 수 있는 모든 경우의 수를 따졌을 때 `3^3(27)`번의 연산이 필요함을 알 수 있습니다. 즉, 이 문제를 완전 탐색으로 풀려고 시도하면 최대 `n^n` → `9^9(387,420,489)`번의 연산이 소요됩니다.

대략 `10^8` 번의 연산이 필요하므로 완전 탐색, 특히 재귀로 작성하는 코드로는 이 문제를 풀 수 없을거 같습니다. 하지만 이 경우는 퀸이 한 줄에 하나만 올 수 있다는 전제 조건만 따졌을 때 발생하는 연산 횟수이기 때문에 퀸의 다른 조건들을 따지면서 아예 조건에 맞지 않는 경우의 수를 백트래킹으로 제외한다면 연산의 수를 대폭 줄일 수 있습니다.

물론 백트래킹 특성상 정확한 연산 횟수를 측정하기는 어렵지만 퀸의 배치 조건이 많이 까다롭기 때문에 백트래킹으로 처음부터 불가능한 케이스를 건너뛰며 연산하면 충분히 재귀 코드로도 해결할 수 있을거 같습니다.

### 결론

- 백트래킹 코드를 작성하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

혼자서 Java & Python으로 쉽게 코드 구현을 성공했다. 최종 정답 코드를 참고하자.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    class Solution:
        def solveNQueens(self, n: int) -> List[List[str]]:
            answer = []
    
            # 메서드
            def back_tracking(board, count, s_row):
                if count == n:
                    answer.append(["".join(s) for s in board])
                    return
    
                for row in range(s_row, n):
                    for col in range(n):
                        if not is_safe(board, row, col):
                            continue
                        
                        board[row][col] = "Q"
                        back_tracking(board, count + 1, row + 1)
                        board[row][col] = "."
            
            def is_safe(board, r, c):
                dir = [[-1, 0], [1, 0], [-1, -1], [-1, 1], [1, -1], [1, 1]]
    
                for dr, dc in dir:
                    n_row, n_col = r + dr, c + dc
    
                    while (0 <= n_row < n and 0 <= n_col < n):
                        if board[n_row][n_col] == "Q":
                            return False
                        
                        n_row, n_col = n_row + dr, n_col + dc
                
                return True
    
            # 메인 로직
            board = [["."] * n for _ in range(n)]
            back_tracking(board, 0, 0)
    
            return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
    
        int n;
        List<List<String>> answer = new ArrayList<>();
    
        public List<List<String>> solveNQueens(int n) {
            this.n = n;
    
            // 메인 로직
            String[][] board = new String[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = ".";
                }
            }
    
            backTracking(0, board, 0);
            return answer;
        }
    
        private void backTracking(int count, String[][] board, int rowStart) {
            if (count == n) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    StringBuilder sb = new StringBuilder();
    
                    for (int j = 0; j < n; j++) {
                        sb.append(board[i][j]);
                    }
    
                    list.add(sb.toString());
                }
    
                answer.add(list);
                return;
            }
    
            for (int row = rowStart; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (!isSafe(board, row, col)) {
                        continue;
                    }
    
                    board[row][col] = "Q";
                    backTracking(count + 1, board, row + 1);
                    board[row][col] = ".";
                }
            }
        }
    
        private boolean isSafe(String[][] board, int r, int c) {
            int[][] dir = {{-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    
            for (int d = 0; d < dir.length; d++) {
                int nextR = r + dir[d][0];
                int nextC = c + dir[d][1];
    
                while (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n) {
                    if (board[nextR][nextC] == "Q") {
                        return false;
                    }
    
                    nextR += dir[d][0];
                    nextC += dir[d][1];
                }
            }
    
            return true;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 새롭게 배운 내용은 따로 없다.
- 위에 작성한 풀이와 코드는 아슬아슬하게 시간 초과를 피하는 비효율적인 풀이와 코드이다.
    - AI에게 물어보니 모든 방향을 탐색하는 풀이 대신 수학 공식을 활용해 행/열/대각선 방향에 대해 방문 처리를 통해 다른 퀸 위치를 확인하는 방식을 추천해줬다. 이 방식을 사용하면 `O(1)` 의 시간 복잡도로 문제를 해결할 수 있다고 한다.
    - 다만 당장 새로운 풀이를 배우지는 않겠다. 이 문제의 목적은 완탐/백트래킹 코드 작성 연습이므로 최적화된 풀이는 나중에 `재시도`를 통해 살펴보겠다.