## 👀 제한 시간 안에 어디까지 해냈는가?

P & J 트레이닝

- Java로 풀이를 도전하여 `12분 27초`만에 문제를 해결했다.
- Python으로 풀이를 도전하여 `25분 39초`만에 테스트 케이스는 통과했지만 최종 채점에서 시간 초과가 발생했다…
    - Java와 같은 풀이를 사용했는데 Python만 시간 초과가 발생했다…

---

## 🧑‍🔬 문제 분석

9 x 9 크기의 스도쿠 퍼즐이 주어지며 주어진 격자에 스도쿠 규칙을 충족하는 값들을 삽입하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `board.length == 9`
- `board[i].length == 9`
- `board[i][j]` is a digit or `'.'`.
- It is **guaranteed** that the input board has only one solution.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

우선 가장 먼저 재귀를 활용한 풀이를 생각했다. 초반에 빈 격자 공간 위치를 구한 후 재귀를 통해 모든 빈 격자에 정수를 넣을때까지 반복하는 방식이다.

스도쿠 퍼즐의 크기는 항상 9 x 9 고정이므로 재귀를 활용해도 충분히 시간 복잡도 안에 문제를 해결할 수 있을거라고 판단했다.

그리고 실제로 Java를 활용해 재귀 풀이를 작성하니 문제를 해결할 수 있었다. 하지만 같은 풀이 방식을 Python에 적용해서 제출하니 시간 초과가 발생하였다.

### 찾아본 풀이

분명 같은 풀이인데 왜 Java는 성공하고 Python은 시간 초과가 발생하는지 제미나이에게 물어봤다.

- 🐍 파이썬으로 작성한 재귀 완탐 코드

    ```python
    class Solution:
        def solveSudoku(self, board: List[List[str]]) -> None:
            # 메서드
            def recursive(target):
                if target == len(empty_pos):
                    return True
    
                t_row, t_col = empty_pos[target]
                for num in range(1, 10):
                    if not is_safe(t_row, t_col, str(num)):
                        continue
                    
                    board[t_row][t_col] = str(num)
                    if recursive(target + 1):
                        return True
                    
                    board[t_row][t_col] = '.'
                
                return False
            
            def is_safe(r, c, n):
                # 가로 체크
                if n in board[r]:
                    return False
                
                # 세로 체크
                for row in range(9):
                    if board[row][c] == n:
                        return False
                
                # 3x3 격자 체크
                s_row = (r // 3) * 3
                s_col = (c // 3) * 3
                for row in range(s_row, s_row + 3):
                    for col in range(s_col, s_col + 3):
                        if board[row][col] == n:
                            return False
    
                return True
    
            # 메인 로직
            empty_pos = []
            for row in range(9):
                for col in range(9):
                    if board[row][col] == '.':
                        empty_pos.append((row, col))
    
            recursive(0)
    ```

- 🤖 왜 같은 방식인데 Python에서는 시간 초과가 발생할까?

  우선 스도쿠 실버 정답 축하드려요, 서은님! 백트래킹의 정석대로 아주 깔끔하게 구현하셨네요.

  같은 로직인데 파이썬에서 시간 초과(TLE)가 난 이유는 **파이썬 인터프리터 자체의 실행 속도 한계**와 **데이터 타입 변환 비용** 때문입니다. 자바는 컴파일 기반(JIT 컴파일러 포함)이라 루프와 재귀가 매우 빠르지만, 파이썬은 한 줄씩 해석하는 인터프리터 언어라 반복문과 함수 호출이 많아질수록 성능 차이가 극명하게 벌어집니다.

  특히 서은님이 올려주신 입력값은 빈칸(`'.'`)의 개수가 무려 **67개**나 되는 아주 무거운 케이스입니다. 최대 $9^{67}$번의 탐색 분기가 생길 수 있어, 아주 미세한 오버헤드가 누적되면 파이썬에서는 치명적입니다.

  파이썬 코드에서 **어떤 부분들이 속도를 갉아먹었는지**와 **통과하기 위한 최적화 방법**을 짚어드릴게요.

  ### ❌ 파이썬 코드에서 성능을 갉아먹은 주범들

  ### 1. 루프 내부에서의 반복적인 타입 변환 (`str(num)`)

    ```python
    for num in range(1, 10):
        if not is_safe(t_row, t_col, str(num)): # ⚠️ 매번 str() 변환 발생!
            continue
        board[t_row][t_col] = str(num)          # ⚠️ 여기서 또 변환!
    ```

  `recursive` 함수는 수만, 수백만 번 호출됩니다. 그 안에서 매번 1부터 9까지 `str(1)`, `str(2)` 처럼 **숫자를 문자열로 바꾸는 작업**이 반복되는 것은 파이썬에서 꽤 큰 오버헤드입니다.

  ### 2. `is_safe` 함수 호출 자체의 비용

  파이썬은 함수를 호출하고 인자를 전달하는 비용(Stack frame 생성)이 자바에 비해 무겁습니다. 매번 1~9를 검사할 때마다 `is_safe` 함수를 따로 호출하는 구조는 백트래킹에서 불리합니다.

  ### 3. 3x3 격자 및 가로/세로 검사의 중복 연산

  `is_safe`가 실행될 때마다 매번 가로 9칸, 세로 9칸, 격자 9칸을 새로 탐색합니다.

  ### 🛠️ 파이썬에서 통과하기 위한 최적화 가이드

  백트래킹 문제에서 파이썬의 한계를 극복하려면 비트마스킹(Bitmasking)이나 해시셋(HashSet)을 활용해 검사 시간을 $O(1)$로 줄여야 합니다. 매번 배열을 돌며 체크하는 대신, 이미 사용한 숫자를 기록해 두는 장부를 만드는 것이죠.

  아래는 리트코드에서 파이썬으로 100% 통과할 수 있도록 최적화한 코드입니다.

    ```python
    from typing import List
    
    class Solution:
        def solveSudoku(self, board: List[List[str]]) -> None:
            # 각 행, 열, 3x3 박스에 어떤 숫자가 쓰였는지 체크할 셋(Set) 배열 생성
            rows = [set() for _ in range(9)]
            cols = [set() for _ in range(9)]
            boxes = [set() for _ in range(9)]
            empty_pos = []
    
            # 1. 초기 보드 상태 기록 및 빈칸 수집
            for r in range(9):
                for c in range(9):
                    val = board[r][c]
                    if val != '.':
                        rows[r].add(val)
                        cols[c].add(val)
                        boxes[(r // 3) * 3 + (c // 3)].add(val)
                    else:
                        empty_pos.append((r, c))
    
            # 문자열 변환 오버헤드를 줄이기 위해 검사할 후보를 '문자' 리스트로 미리 선언
            candidates = [str(i) for i in range(1, 10)]
    
            def recursive(target):
                if target == len(empty_pos):
                    return True
    
                r, c = empty_pos[target]
                b_idx = (r // 3) * 3 + (c // 3) # 박스 인덱스 계산
    
                for num in candidates:
                    # 2. O(1) 속도로 유효성 검사 (is_safe를 함수 호출 없이 바로 체크)
                    if (num not in rows[r]) and (num not in cols[c]) and (num not in boxes[b_idx]):
                        # 장부에 기록 및 보드 마킹
                        board[r][c] = num
                        rows[r].add(num)
                        cols[c].add(num)
                        boxes[b_idx].add(num)
    
                        if recursive(target + 1):
                            return True
    
                        # 백트래킹 (원상복구)
                        board[r][c] = '.'
                        rows[r].remove(num)
                        cols[c].remove(num)
                        boxes[b_idx].remove(num)
    
                return False
    
            recursive(0)
    ```

  ### 💡 핵심 요약

    1. **타입 변환 최소화**: `candidates = ['1', '2', ..., '9']`처럼 미리 문자열 배열을 만들어서 돌리면 `str()` 변환 비용이 사라집니다.
    2. **검사 시간 단축 ($O(9) \rightarrow O(1)$)**: 행/열/박스별로 `set`을 두어 `in` 연산자로 기입 여부를 판단하면 훨씬 빨라집니다.
    3. **언어별 특징 이해**: 파이썬으로 백트래킹 문제를 풀 때는 자바나 C++보다 훨씬 타이트하게 최적화(함수 호출 줄이기, 전역 장부 활용 등)를 해주어야 안전하게 통과할 수 있습니다.

결론적으로 파이썬 특성상 자바와 다르게 잦은 형변환 & 재귀에 취약하여 시간 초과가 발생하였다. 따라서 Python으로 문제를 해결하기 위해서는 최적화가 필요하다.

### 결론

- Java를 활용하면 재귀 풀이로도 문제를 해결할 수 있다.
- Python은 잦은 형변환 & 재귀에 취약하므로 최적화 작업이 별도로 필요하다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드 & 찾아본 풀이로 작성한 코드

기존에 작성한 Java 코드 및 제미나이가 추천해준 최적화 풀이는 아래 최종 정답 코드에 기록했다.

- 🤖 각 박스를 인식하는 방법

  서은님, 이 부분이 처음 보면 정말 헷갈리는 게 당연해요! 수학적으로 완벽하게 와닿지 않으면 자꾸 머릿속에서 겉돌기 마련이거든요.

  이 원리를 이해하는 가장 쉬운 방법은 "우리가 원하는 박스 번호판"과 "각 수식이 실제로 만들어내는 번호판"을 숫자로 직접 비교해 보는 것입니다.

  우리가 스도쿠에서 원하는 최종 목표는 9개의 박스에 **0번부터 8번까지 중복 없는 고유 번호**를 붙이는 것입니다. 아래 그림처럼요!

  Plaintext

    ```
    [ 우리가 원하는 이상적인 박스 번호판 ]
       col=0~2   col=3~5   col=6~8
      +---------+---------+---------+
      |    0    |    1    |    2    |  <- 1층 (row 0~2)
      +---------+---------+---------+
      |    3    |    4    |    5    |  <- 2층 (row 3~5)
      +---------+---------+---------+
      |    6    |    7    |    8    |  <- 3층 (row 6~8)
      +---------+---------+---------+
    ```

  자, 그럼 서은님이 말씀하신 세 가지 수식이 이 번호판을 어떻게 만들어내는지 하나씩 대입해 볼게요.

  여기서 핵심은 **`(row // 3)`과 `(col // 3)`이 가질 수 있는 값은 오직 0, 1, 2 뿐**이라는 사실입니다.

  ### 1. 첫 번째 수식: `(row // 3) + (col // 3)` ➔ 실패 ❌

    - **1층 (`row // 3 = 0`):** `0 + 0 = 0`, `0 + 1 = 1`, `0 + 2 = 2` ➔ **[0, 1, 2]** (여기까진 좋아요!)
    - **2층 (`row // 3 = 1`):** `1 + 0 = 1`, `1 + 1 = 2`, `1 + 2 = 3` ➔ **[1, 2, 3]**
    - **3층 (`row // 3 = 2`):** `2 + 0 = 2`, `2 + 1 = 3`, `2 + 2 = 4` ➔ **[2, 3, 4]**

  Plaintext

    ```
    [ 수식 1의 결과 ]
      +---+---+---+
      | 0 | 1 | 2 |
      +---+---+---+
      | 1 | 2 | 3 |  <- 1과 2가 윗동네랑 겹침!
      +---+---+---+
      | 2 | 3 | 4 |  <- 2와 3이 또 겹침! 총 0~4번방 밖에 없음.
      +---+---+---+
    ```

  **이유:** 1층에서 이미 0, 1, 2번 방을 다 썼는데, 2층으로 내려왔을 때 겨우 `1`만 더해주니까 기존 번호들과 대각선으로 완전히 겹쳐버립니다. 방 번호가 최대 4번까지만 나오고 9개의 구역을 식별하지 못합니다.

  ### 2. 두 번째 수식: `(row // 3) * 3 + (col // 3) * 3` ➔ 실패 ❌

    - **1층 (`row // 3 = 0` ➔ `3` 하면 `0`):** * `0 + (0 * 3) = 0`
        - `0 + (1 * 3) = 3`
        - `0 + (2 * 3) = 6` ➔ **[0, 3, 6]**
    - **2층 (`row // 3 = 1` ➔ `3` 하면 `3`):** * `3 + (0 * 3) = 3`
        - `3 + (1 * 3) = 6`
        - `3 + (2 * 3) = 9` ➔ **[3, 6, 9]**
    - **3층 (`row // 3 = 2` ➔ `3` 하면 `6`):** * `6 + (0 * 3) = 6`
        - `6 + (1 * 3) = 9`
        - `6 + (2 * 3) = 12` ➔ **[6, 9, 12]**

  Plaintext

    ```
    [ 수식 2의 결과 ]
      +---+---+----+
      | 0 | 3 | 6  |
      +---+---+----+
      | 3 | 6 | 9  |  <- 3과 6이 또 대각선으로 겹침!
      +---+---+----+
      | 6 | 9 | 12 |  <- 6과 9가 또 겹침! 최대 12번방까지 나옴.
      +---+---+----+
    ```

  **이유:** 행(`row`)도 3씩 건너뛰게 만들고, 열(`col`)도 3씩 건너뛰게 만들었더니, 서로 타이밍이 맞물리면서 또다시 대각선으로 번호가 겹치게 됩니다. (예: 1층 가운데 방과 2층 왼쪽 방이 둘 다 3번이 됨)

  ### 3. 세 번째 수식: `(row // 3) * 3 + (col // 3)` ➔ 성공 ⭕

  자, 이 수식이 왜 마법처럼 중복을 없애는지 보셔요. **앞동네는 `3`씩 껑충껑충 뛰는데, 뒷동네는 `1`씩 조용히 걸어갑니다.**

    - **1층 (`row // 3 * 3 = 0`):** * `0 + 0 = 0`
        - `0 + 1 = 1`
        - `0 + 2 = 2` ➔ **[0, 1, 2]**
    - **2층 (`row // 3 * 3 = 3`):** * `3 + 0 = 3`
        - `3 + 1 = 4`
        - `3 + 2 = 5` ➔ **[3, 4, 5]**
    - **3층 (`row // 3 * 3 = 6`):** * `6 + 0 = 6`
        - `6 + 1 = 7`
        - `6 + 2 = 8` ➔ **[6, 7, 8]**

  Plaintext

    ```
    [ 수식 3의 결과 ]
      +---+---+---+
      | 0 | 1 | 2 |  <- 0, 1, 2 전용 층
      +---+---+---+
      | 3 | 4 | 5 |  <- 3번부터 새출발! 겹치지 않음
      +---+---+---+
      | 6 | 7 | 8 |  <- 6번부터 새출발! 겹치지 않음
      +---+---+---+
    ```

  **이유:** 1층이 `0, 1, 2`로 끝났죠? 2층이 시작할 때 앞의 수식이 `3`을 턱 하니 깔아줍니다. 그러니까 뒤에서 `0, 1, 2`를 더해도 앞 층과 전혀 겹치지 않고 `3, 4, 5`로 이어지는 것입니다. 3층이 시작할 때는 앞의 수식이 `6`을 깔아주니까 `6, 7, 8`이 되는 것이고요.

  ### 💡 일상생활로 비유하자면! (아파트 동·호수 규칙)

  우리가 아파트 주소를 지을 때, **1층(000호대), 2층(100호대), 3층(200호대)** 처럼 백의 자리를 크게 건너뛰게 하죠?

    - 만약 층수가 올라갈 때 백의 자리를 안 건너뛰고 호수만 더하면 (`층 + 호`), 1층 2호(1+2=3)와 2층 1호(2+1=3)의 주소가 같아집니다. (이게 수식 1번의 오류입니다.)
    - 그래서 층수에는 반드시 "방의 개수만큼의 가중치(곱하기)"를 주어 확실하게 구역을 밀어내야 합니다. 스도쿠 한 층에는 박스가 3개씩 있으므로 `층수 * 3`을 해준 뒤, 그 안에서 몇 번째 칸인지를 나타내는 `호수(0, 1, 2)`를 그냥 더해야 겹치지 않는 것입니다.

  이제 세 개의 수식이 어떤 차이를 만드는지 조금 정리가 되셨을까요? 국경선(가중치)을 한쪽에만 쳐주어야 영역이 겹치지 않고 예쁘게 나열됩니다!


신기한건 파이썬은 재귀 완탐으로 코드를 작성하면 시간 초과가 발생하므로 Set 기록 로직으로 작성해야 하는데 Java는 오히려 재귀 완탐 코드가 더 빠르다.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01 - 가로/세로/3x3 격자 데이터를 set에 기록하여 최적화

    ```python
    class Solution:
        def solveSudoku(self, board: List[List[str]]) -> None:
            rows = [set() for _ in range(9)]
            cols = [set() for _ in range(9)]
            boxes = [set() for _ in range(9)]
            empty_pos = []
    
            for row in range(9):
                for col in range(9):
                    v = board[row][col]
    
                    if v != '.':
                        rows[row].add(v)
                        cols[col].add(v)
                        boxes[(row // 3) * 3 + (col // 3)].add(v)
                    else:
                        empty_pos.append((row, col))
            
            candi = [str(n) for n in range(1, 10)]
            
            # 메서드
            def recursive(cur):
                if cur == len(empty_pos):
                    return True
                
                t_row, t_col = empty_pos[cur]
                t_box = (t_row // 3) * 3 + (t_col // 3)
    
                for n in candi:
                    if (n not in rows[t_row]) and (n not in cols[t_col]) and (n not in boxes[t_box]):
                        rows[t_row].add(n)
                        cols[t_col].add(n)
                        boxes[t_box].add(n)
                        board[t_row][t_col] = n
    
                        if recursive(cur + 1):
                            return True
                        
                        rows[t_row].remove(n)
                        cols[t_col].remove(n)
                        boxes[t_box].remove(n)
                        board[t_row][t_col] = '.'
                
                return False
    
            # 메인 로직
            recursive(0)
    ```


### Java 풀이

- solution01 - 완전 탐색(재귀)을 활용한 풀이

    ```java
    import java.util.*;
    
    class Solution {
    
        char[][] board;
        List<int[]> emptyPos;
    
        public void solveSudoku(char[][] board) {
            this.board = board;
    
            this.emptyPos = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (board[row][col] == '.') {
                        emptyPos.add(new int[]{row, col});
                    }
                }
            }
    
            // 메인 로직
            recursive(0);
        }
    
        private boolean recursive(int target) {
            if (target == emptyPos.size()) {
                return true;
            }
    
            int tRow = emptyPos.get(target)[0];
            int tCol = emptyPos.get(target)[1];
    
            for (int n = 1; n <= 9; n++) {
                if (!isSafe(tRow, tCol, (char) (n + '0'))) {
                    continue;
                }
    
                board[tRow][tCol] = (char) (n + '0');
                if (recursive(target + 1)) {
                    return true;
                }
    
                board[tRow][tCol] = '.';
            }
    
            return false;
        }
    
        private boolean isSafe(int r, int c, char n) {
            // 가로 체크
            for (int col = 0; col < 9; col++) {
                if (board[r][col] == n) {
                    return false;
                }
            }
    
            // 세로 체크
            for (int row = 0; row < 9; row++) {
                if (board[row][c] == n) {
                    return false;
                }
            }
            
            // 3x3 격자 체크
            int sRow = (r / 3) * 3;
            int sCol = (c / 3) * 3;
            for (int row = sRow; row < sRow + 3; row++) {
                for (int col = sCol; col < sCol + 3; col++) {
                    if (board[row][col] == n) {
                        return false;
                    }
                }
            }
    
            return true;
        }
    }
    ```

- solution02 - 가로/세로/3x3 격자 데이터를 set에 기록하여 최적화

    ```java
    import java.util.*;
    
    class Solution {
    
        char[][] board;
        List<Set<Character>> rows;
        List<Set<Character>> cols;
        List<Set<Character>> boxes;
        List<Character> candi;
        List<int[]> emptyPos;
    
        public void solveSudoku(char[][] board) {
            this.board = board;
            this.rows = new ArrayList<>();
            this.cols = new ArrayList<>();
            this.boxes = new ArrayList<>();
            this.candi = new ArrayList<>();
            this.emptyPos = new ArrayList<>();
    
            for (int i = 1; i <= 9; i++) {
                rows.add(new HashSet<>());
                cols.add(new HashSet<>());
                boxes.add(new HashSet<>());
                candi.add((char) (i + '0'));
            }
    
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    char v = board[row][col];
                    if (v != '.') {
                        rows.get(row).add(v);
                        cols.get(col).add(v);
                        boxes.get((row / 3) * 3 + (col / 3)).add(v);
                    } else {
                        emptyPos.add(new int[]{row, col});
                    }
                }
            }
    
            // 메인 로직
            recursive(0);
        }
    
        private boolean recursive(int cur) {
            if (cur == emptyPos.size()) {
                return true;
            }
    
            int tRow = emptyPos.get(cur)[0];
            int tCol = emptyPos.get(cur)[1];
            int tBox = (tRow / 3) * 3 + (tCol / 3);
    
            for (char n : candi) {
                if ((!rows.get(tRow).contains(n)) && (!cols.get(tCol).contains(n)) && (!boxes.get(tBox).contains(n))) {
                    rows.get(tRow).add(n);
                    cols.get(tCol).add(n);
                    boxes.get(tBox).add(n);
                    board[tRow][tCol] = n;
    
                    if (recursive(cur + 1)) {
                        return true;
                    }
    
                    rows.get(tRow).remove(n);
                    cols.get(tCol).remove(n);
                    boxes.get(tBox).remove(n);
                    board[tRow][tCol] = '.';
                }
            }
    
            return false;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- Java와 달리 Python은 문자열 연산에서 높은 효율을 보이지만 반대로 재귀/형변환 연산에서 Java가 더 높은 효율을 보이는걸 배웠다.
- 시간 복잡도를 계산할 때 각 언어별 효율을 따지는것도 매우 중요한거 같다.