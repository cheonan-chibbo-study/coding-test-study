## 👀 제한 시간 안에 어디까지 해냈는가?

- `29분 3초`만에 Java로 혼자 문제를 해결했다.
- `12분 32`초만에 Python으로 혼자 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

key, lock 정보가 주어질 때 문제 조건에 맞춰 주어진 key로 lock을 열 수 있는지 여부를 반환하는 문제이다.

- 자세한 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

### **제한사항**

- key는 M x M(3 ≤ M ≤ 20, M은 자연수)크기 2차원 배열입니다.
- lock은 N x N(3 ≤ N ≤ 20, N은 자연수)크기 2차원 배열입니다.
- M은 항상 N 이하입니다.
- key와 lock의 원소는 0 또는 1로 이루어져 있습니다.
    - 0은 홈 부분, 1은 돌기 부분을 나타냅니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

제일먼저 떠올릴 수 있는 방법은 열쇠를 이동하거나 회전하는 모든 경우의 수를 완전 탐색하는 방법입니다. 이 문제에 완전 탐색을 수행할 경우 예상되는 시간 복잡도는 다음과 같습니다.

- `4 * ((M + N - 1)^2)` == `4M^2 + 4N^2` == `M^2 + N^2`
    - 4 → 정사각형 회전 가능 수
- 문제 제한사항에서 M과 N은 최대 값이 같으므로 M으로 통일할 경우 최종 시작 복잡도는 `M^4`

M은 최대 20이 주어지므로 이 문제를 완전 탐색으로 풀이할 경우 최대 20^4 == `160,000번`의 연산이 예상됩니다.

따라서 이 문제는 완전 탐색을 활용해 풀 수 있다는 결론을 내릴 수 있습니다.

### 결론

- 완탐 풀이로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

혼자서 무리없이 Java & Python 코드를 작성할 수 있었다. 최종 정답 코드를 참고하자.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01 - 격자 회전 로직 직접 구현

    ```python
    from copy import deepcopy
    
    def solution(key, lock):
        k_size = len(key)
        l_size = len(lock)
        
        # 메서드
        def check(row_offset, col_offset):
            rotated = deepcopy(key)
            is_continue = True
            
            for d in range(4):
                if d != 0:
                    rotated = rotate(rotated)
                is_continue = True
                
                for l_row in range(l_size):
                    for l_col in range(l_size):
                        k_row, k_col = l_row + row_offset, l_col + col_offset
                        
                        # 자물쇠가 홈 일 때
                        if lock[l_row][l_col] == 0:
                            if not is_safe(k_row, k_col) or rotated[k_row][k_col] != 1:
                                is_continue = False
                                break
                        else:  # 자물쇠가 돌기 일 때
                            if is_safe(k_row, k_col) and rotated[k_row][k_col] == 1:
                                is_continue = False
                                break
                    
                    if not is_continue:
                        break
                
                if is_continue:
                    return True
            
            return False
                        
        def rotate(original):
            rotated = [[0] * k_size for _ in range(k_size)]
            
            for row in range(k_size):
                for col in range(k_size):
                    rotated[col][k_size - row - 1] = original[row][col]
            
            return rotated
        
        def is_safe(r, c):
            return 0 <= r < k_size and 0 <= c < k_size
        
        # 메인 로직
        for row_offset in range(k_size, -(l_size + 1), -1):
            for col_offset in range(k_size, -(l_size + 1), -1):
                if (check(row_offset, col_offset)):
                    return True
        
        return False
    ```

- solution02 - 격자 회전 로직을 라이브러리를 사용해 구현

    ```python
    from copy import deepcopy
    
    def solution(key, lock):
        k_size = len(key)
        l_size = len(lock)
        
        # 메서드
        def check(row_offset, col_offset):
            rotated = deepcopy(key)
            is_continue = True
            
            for d in range(4):
                if d != 0:
                    rotated = [list(item) for item in zip(*rotated[::-1])]
                is_continue = True
                
                for l_row in range(l_size):
                    for l_col in range(l_size):
                        k_row, k_col = l_row + row_offset, l_col + col_offset
                        
                        # 자물쇠가 홈 일 때
                        if lock[l_row][l_col] == 0:
                            if not is_safe(k_row, k_col) or rotated[k_row][k_col] != 1:
                                is_continue = False
                                break
                        else:  # 자물쇠가 돌기 일 때
                            if is_safe(k_row, k_col) and rotated[k_row][k_col] == 1:
                                is_continue = False
                                break
                    
                    if not is_continue:
                        break
                
                if is_continue:
                    return True
            
            return False
                        
        def is_safe(r, c):
            return 0 <= r < k_size and 0 <= c < k_size
        
        # 메인 로직
        for row_offset in range(k_size, -(l_size + 1), -1):
            for col_offset in range(k_size, -(l_size + 1), -1):
                if (check(row_offset, col_offset)):
                    return True
        
        return False
    ```


### Java 풀이

- solution01

    ```java
    class Solution {
        
        int[][] key;
        int[][] lock;
        int k_size;
        int l_size;
        
        public boolean solution(int[][] key, int[][] lock) {
            this.key = key;
            this.lock = lock;
            this.k_size = key.length;
            this.l_size = lock.length;
            
            // 메인 로직
            for (int rowOffset = k_size; rowOffset >= -l_size; rowOffset--) {
                for (int colOffset = k_size; colOffset >= -l_size; colOffset--) {
                    if (check(rowOffset, colOffset)) {
                        return true;
                    }
                }
            }
            
            return false;
        }
        
        private boolean check(int rowOffset, int colOffset) {
            int[][] rotatedKey = new int[k_size][k_size];
            for (int i = 0; i < k_size; i++) {
                System.arraycopy(key[i], 0, rotatedKey[i], 0, key[i].length);
            }
            
            boolean isContinue;
            for (int d = 0; d < 4; d++) {
                // 키 회전
                if (d != 0) {
                    rotatedKey = rotate(rotatedKey);
                }
                isContinue = true;
                
                for (int row = 0; row < l_size; row++) {
                    for (int col = 0; col < l_size; col++) {
                        int keyRow = row + rowOffset;
                        int keyCol = col + colOffset;
                        
                        // 자물쇠 홈 부분일 때
                        if (lock[row][col] == 0) {
                            if (!isSafe(keyRow, keyCol) || rotatedKey[keyRow][keyCol] != 1) {
                                isContinue = false;
                                break;
                            }
                        } else {  // 자물쇠 돌기 부분일 때
                            if (isSafe(keyRow, keyCol) && rotatedKey[keyRow][keyCol] == 1) {
                                isContinue = false;
                                break;
                            }
                        }
                    }
                    
                    if (!isContinue) {
                        break;
                    }
                }
                
                if (isContinue) {
                    return true;
                }
            }
            
            return false;
        }
        
        private int[][] rotate(int[][] original) {
            int[][] rotated = new int[k_size][k_size];
            
            for (int row = 0; row < k_size; row++) {
                for (int col = 0; col < k_size; col++) {
                    rotated[col][k_size - row - 1] = original[row][col];
                }
            }
            
            return rotated;
        }
        
        private boolean isSafe(int r, int c) {
            return r >= 0 && r < k_size && c >= 0 && c < k_size;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 배운 내용은 다음과 같다.
    - 격자를 오른쪽으로 90도 회전시키는 방법
    - 이차원 리스트/배열 깊은 복사